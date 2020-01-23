package queue

class Queue(private val maxSize: Int) {
    private val q = Array<Any>(maxSize + 1) { 0 }
    private var head = 0
    private var tail = 0


    init {
        assert(maxSize > 0)
    }

    private fun size(): Int {
        return if (head > tail) {
            q.size - head + tail
        } else {
            tail - head
        }
    }

    fun isEmpty(): Boolean {
        return head == tail
    }

    fun isFull(): Boolean {
        return size() == maxSize
    }

    fun enqueue(element: Any): Boolean {
        if (isFull()) {
            //println("Enqueue failed: queue is full.")
            return false
        }
        q[tail++] = element
        tail %= q.size
        return true
    }

    fun dequeue(): Any? {
        if (isEmpty()) {
            //println("Dequeue failed: queue is empty.")
            return null
        }
        val element = q[head++]
        head %= q.size
        return element
    }

    fun peek(): Any? {
        if (isEmpty()) {
            //println("Peek failed: queue is empty.")
            return null
        }
        return q[head]
    }

    fun check() {
        assert(tail >= 0)
        assert(tail < q.size)
        assert(head >= 0)
        assert(tail < q.size)
        assert(size() < q.size)
        if (isEmpty()) {
            assert(size() == 0)
        }
    }
}

