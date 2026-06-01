package patterns.builder

interface Builder {
    fun buildWheel()

    fun buildEngine()

    fun buildBody()

    fun getResult(): Car
}

class SedanBuilder : Builder {
    private val car = Car(carType = "Sedan")

    override fun buildWheel() {
        println("build Sedan wheel")
    }

    override fun buildEngine() {
        println("build Sedan engine")
    }

    override fun buildBody() {
        println("build Sedan body")
    }

    override fun getResult(): Car = car
}

class SUVBuilder : Builder {
    private val car = Car(carType = "SUV")

    override fun buildWheel() {
        println("build SUV wheel")
    }

    override fun buildEngine() {
        println("build SUV engine")
    }

    override fun buildBody() {
        println("build SUV body")
    }

    override fun getResult(): Car = car
}

class Car(
    val carType: String,
) {
    override fun toString(): String = "Car(carType='$carType')"
}

class Director {
    companion object {
        fun build(builder: Builder) {
            builder.buildBody()
            builder.buildWheel()
            builder.buildEngine()
            builder.getResult()
        }
    }
}

fun main() {
    val sedanBuilder = SedanBuilder()
    Director.build(sedanBuilder)
    println(sedanBuilder.getResult())

    val suvBuilder = SUVBuilder()
    Director.build(suvBuilder)
    println(suvBuilder.getResult())
}
