package patterns.prototype

abstract class Shape(
    val x: Int,
    val y: Int,
    val color: String,
) {
    abstract fun clone(): Shape
}

data class Circle(
    val radius: Int,
) : Shape(0, 0, "red") {
    override fun clone(): Shape = this.copy()
}

data class Rectangle(
    val width: Int,
    val height: Int,
) : Shape(0, 0, "blue") {
    override fun clone(): Shape = this.copy()
}

fun main() {
    val circle1 = Circle(radius = 5)
    val circle2 = circle1.clone() as Circle
    println(circle1) // Circle(radius=5)
    println(circle2) // Circle(radius=5)
    println(circle1 === circle2) // false

    val rectangle1 = Rectangle(width = 10, height = 20)
    val rectangle2 = rectangle1.clone() as Rectangle
    println(rectangle1) // Rectangle(width=10, height=20)
    println(rectangle2) // Rectangle(width=10, height=20)
    println(rectangle1 === rectangle2) // false
}
