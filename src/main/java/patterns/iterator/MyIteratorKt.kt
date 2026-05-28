package patterns.iterator

class MyIteratorKt (
    val collection : MyIterableCollectionKt,
)  : IteratorKt {
    override fun hasNext(): Boolean {
        return !collection.isEnd()
    }

    override fun next(): Any? {
        return collection.getNext()
    }
}