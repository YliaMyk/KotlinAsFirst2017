@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

import javafx.beans.binding.StringBinding

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0) {
        throw IllegalArgumentException()
    }
    val res = MatrixImpl(height, width, e)
    for (row in 0 until height) {
        for (column in 0 until width) {
            res[row, column] = e
        }
    }
    return res
}
/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, val e: E) : Matrix<E> {

    private val content = MutableList(height) { MutableList(width) { e } }

    override fun get(row: Int, column: Int): E = content[row][column]

    override fun get(cell: Cell): E {
        return get(cell.row, cell.column)
    }

    override fun set(row: Int, column: Int, value: E) {
        content[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if (!(other is Matrix<*> &&
                height == other.height &&
                width == other.width)) return false
        for (row in 0 until this.height) {
            for (col in 0 until this.width) {
                if (this[row, col] != other[row, col])
                    return false
            }
        }
        return true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (row in 0 until height) {
            for (column in 0 until width) {
                sb.append(this[row, column])
                if (column != width - 1) sb.append(" ")
            }
            if (row != height - 1) sb.append("\n")
        }
        return "$sb"
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + (e?.hashCode() ?: 0)
        result = 31 * result + content.hashCode()
        return result
    }
}

