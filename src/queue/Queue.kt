package queue

class Queue(maxSize: Int) {
    private val q = Array<Any>(maxSize + 1) { 0 }
    private var head = 0
    private var tail = 0

    init {
        assert(maxSize > 0)
    }

    fun size(): Int {
        if (head > tail) {
            return q.size - head + tail
        } else {
            return tail - head
        }
    }

    fun isEmpty(): Boolean {
        return size() == 0
    }

    fun isFull(): Boolean {
        return size() == q.size - 1
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
        val element = q[head]
        head++
        head %= q.size
        return element
    }

    /*
    fun peek():  Any? {
        if (isEmpty()) {
            println("Peek failed: queue is empty.")
            return null
        }
        return q[head]
    }

    fun info(): String {
        val info = "head = $head | tail = $tail | size = ${size()} "
        var i = head
        var queue = "["
        while (i != tail) {
            queue += q[i]
            if (i++ != tail - 1) {
                queue += ", "
            } else {
                queue += "]"
            }
            i %= q.size
        }
        return info + "\n" + queue
    }
    */
    fun check() {
        assert(tail >= 0)
        assert(tail < q.size)
        assert(head >= 0)
        assert(tail < q.size)
        assert(size() < q.size)
        if (isEmpty()) {
            assert(head == tail)
        }
    }
}

