package me.y9san9.aoc24.day12

import me.y9san9.aoc24.grid.Grid2D
import me.y9san9.aoc24.grid.MutableGrid2D
import me.y9san9.aoc24.grid.Pointed2D
import me.y9san9.aoc24.grid.map
import me.y9san9.aoc24.grid.plusNeighbours
import me.y9san9.aoc24.grid.pointed
import me.y9san9.aoc24.grid.points
import me.y9san9.aoc24.grid.pretty
import me.y9san9.aoc24.grid.toMutableGrid
import me.y9san9.aoc24.input.readCharGrid
import me.y9san9.aoc24.program
import me.y9san9.aoc24.vector.Vector2D

data class GardenPlant(val type: Char) {
    override fun toString(): String = type.toString()
}

data class GardenPlot(val grid: Grid2D<GardenPlant>) {
    fun regions(): Sequence<GardenRegion> {
        val exceptVisited = grid.toMutableGrid()

        return generateSequence {
            if (exceptVisited.points.isEmpty()) {
                null
            } else {
                val startPoint = exceptVisited.points.first()
                val plant = grid.pointed()[startPoint]
                val region = fillRegion(exceptVisited, plant.element.type, mutableListOf(plant))
                region
            }
        }
    }

    private tailrec fun fillRegion(
        grid: MutableGrid2D<GardenPlant>,
        type: Char,
        neighbours: MutableList<Pointed2D<GardenPlant>>,
        acc: MutableList<Pointed2D<GardenPlant>> = mutableListOf()
    ): GardenRegion {
        if (neighbours.isEmpty()) return GardenRegion(acc.toList())

        val neighbour = neighbours.removeFirst()

        if (neighbour.point in grid && neighbour.element.type == type) {
            acc.add(neighbour)
            neighbours += grid.pointed().plusNeighbours(neighbour.point)
            grid.remove(neighbour.point)
        }

        return fillRegion(grid, type, neighbours, acc)
    }

    override fun toString(): String = grid.pretty()
}

data class GardenRegion(val plants: List<Pointed2D<GardenPlant>>) {
    val type: Char get() = plants.first().element.type

    init {
        require(plants.isNotEmpty())
        require(plants.all { plant -> plant.element.type == plants.first().element.type })
    }

    fun perimeter(): Int {
        val coordinates = plants.points()

        return plants.sumOf { plant ->
            4 - plant.point.plusNeighbours().count { neighbour -> neighbour in coordinates }
        }
    }

    fun angles(): Int {
        val coordinates = plants.points()

        val angles = listOf(
            Vector2D.TopLeft, Vector2D.Top, Vector2D.Left,
            Vector2D.TopRight, Vector2D.Top, Vector2D.Right,
            Vector2D.BottomRight, Vector2D.Bottom, Vector2D.Right,
            Vector2D.BottomLeft, Vector2D.Bottom, Vector2D.Left,
        )

        return coordinates.sumOf { coordinate ->
            angles.chunked(3).count { (corner, firstSide, secondSide) ->
                if (coordinate + firstSide !in coordinates && coordinate + secondSide !in coordinates) return@count true

                if (coordinate + firstSide in coordinates && coordinate + secondSide in coordinates) {
                    return@count coordinate + corner !in coordinates
                }

                false
            }
        }
    }

    fun sides(): Int {
        return angles()
    }

    fun area(): Int {
        return plants.size
    }
}

fun main() = program<GardenPlot>(day = 12) {
    input { file ->
        val grid = file.readCharGrid()
        GardenPlot(grid.map(::GardenPlant))
    }

    first {
        example = 1930

        compute { input ->
            input.regions().sumOf { region ->
                region.perimeter() * region.area()
            }
        }
    }

    second {
        example = 1206

        compute { input ->
            input.regions().sumOf { region ->
                region.sides() * region.area()
            }
        }
    }
}
