@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {

    when {
        age % 100 in 11..14 -> return "$age лет"
        age % 10 == 1 -> return "$age год"
        age % 10 in 2..4 -> return "$age года"
        else -> return "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val haifDistance = (t1 * v1 + t2 * v2 + t3 * v3)/2.0
    val firstDistance = t1 * v1
    val secondDistance = t2 * v2

    when {
        (haifDistance) < (firstDistance) -> return haifDistance / v1
        (haifDistance) in firstDistance..(firstDistance + secondDistance) ->
            return t1 + (haifDistance - firstDistance) / v2
        else -> return t1 + t2 + (haifDistance - firstDistance - secondDistance) / v3
    }

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int = when {
                           (kingX == rookX1 || kingX == rookX2) && (kingY == rookY2 || kingY == rookY1) -> 3
                           kingX == rookX1 || kingY == rookY1 -> 1
                           kingX == rookX2 || kingY == rookY2 -> 2
                           else -> 0
                       }

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val modulusDishopX = Math.abs(kingX - bishopX)
    val modulusDishopY = Math.abs(kingY - bishopY)
    when {
        (kingX == rookX || kingY == rookY) && (modulusDishopX == modulusDishopY) -> return 3
        else -> return if (kingX == rookX || kingY == rookY) 1
        else if (modulusDishopX == modulusDishopY) 2
        else 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val squareA = sqr(a)
    val squareB = sqr(b)
    val squareC = sqr(c)
    return when {
        a > b + c || b > a + c || c > b + a -> -1
        squareA == squareB + squareC || squareB == squareA + squareC || squareC == squareB + squareA -> 1
        (squareA < squareB + squareC) && (squareB < squareA + squareC) && (squareC < squareA + squareB) -> 0
        else -> 2

    }
 }

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.0
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    c in a..b && b <= d -> b - c
    a in c..d && b >= d -> d - a
    a >= c && b <= d -> b - a
    a <= c && b >= d -> d - c
    else -> -1
}
