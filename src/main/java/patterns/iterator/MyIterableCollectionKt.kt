package patterns.iterator

class MyIterableCollectionKt: IterableCollectionKt {

    val list = listOf("One", "Two", "Three")
    private var index = 0

    override fun getIterator(): IteratorKt {
        return MyIteratorKt(this)
    }

    fun isEnd(): Boolean {
        return index == list.size
    }

    fun getNext(): String? {
        if (index < list.size) {
            return list.get(index++)
        }
        return null
    }

}