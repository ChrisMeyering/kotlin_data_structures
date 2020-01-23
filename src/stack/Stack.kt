package stack

class Stack(private val maxSize: Int) {
    private val s = Array<Any>(maxSize) { 0 }
    private var top = 0

    init {
        assert(maxSize > 0)
    }

    fun isEmpty(): Boolean = top == 0

    fun isFull(): Boolean = top == maxSize

    fun push(element: Any): Boolean {
        if (isFull()) {
            return false
        }
        s[top++] = element
        return true
    }

    fun pop(): Any? {
        if (isEmpty()) {
            return null
        }
        return s[--top]
    }

    fun peek(): Any? {
        if (isEmpty()) {
            return null
        }
        return s[top - 1]
    }

    fun check() {
        assert(top > 0)
        assert(top <= maxSize)
    }


}