package patterns.iterator

fun main() {
    val collection = MyIterableCollectionKt()
    val iterator = collection.getIterator()
    while (iterator.hasNext()) {
        println(iterator.next().toString())
    }
}
