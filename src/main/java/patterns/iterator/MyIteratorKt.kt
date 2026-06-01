package patterns.iterator

class MyIteratorKt(
    val collection: MyIterableCollectionKt,
) : IteratorKt {
    override fun hasNext(): Boolean = !collection.isEnd()

    override fun next(): Any? = collection.getNext()
}
