package patterns.singletone

class MyInstance private constructor() {
    companion object {
        @Volatile
        private var instance: MyInstance? = null

        fun getInstance(): MyInstance =
            instance ?: synchronized(this) {
                instance ?: MyInstance().also { instance = it }
            }
    }
}

fun main() {
    val instance1 = MyInstance.getInstance()
    val instance2 = MyInstance.getInstance()
    println(instance1 === instance2) // true
}
