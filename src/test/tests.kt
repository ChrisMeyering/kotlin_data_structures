package test

import queue.Queue
import stack.Stack
import kotlin.math.pow
import kotlin.random.Random

const val N = 10_000
const val BIAS_ITER = 41
const val INNER_ITER = 1_000_000
const val MAX = Int.MAX_VALUE

fun queueRandomTest() {
    var add = 0
    var rm = 0
    var addFull = 0
    var rmEmpty = 0
    var peek = 0
    var peekEmpty = 0

    val q = Queue(N)
    val list = mutableListOf<Int>()
    q.check()

    repeat(BIAS_ITER) {
        val bias =
            if (it % 2 == 0) {
                0.1
            } else {
                -0.1
            }
        repeat(INNER_ITER) {
            if (Random.nextDouble() < 0.2) {
                val element = q.peek()
                q.check()
                if (element == null) {
                    assert(list.isEmpty())
                    assert(q.isEmpty())
                    peekEmpty++
                } else {
                    val expectedElement = list.first()
                    assert(expectedElement == element)
                    peek++
                }
            }
            if (Random.nextDouble() < 0.5 + bias) {
                val element = Random.nextInt(MAX)
                val result = q.enqueue(element)
                q.check()
                if (result) {
                    list.add(element)
                    add++
                } else {
                    assert(q.isFull())
                    assert(list.size == N)
                    addFull++
                }
            } else {
                val element = q.dequeue()
                q.check()
                if (element == null) {
                    assert(list.isEmpty())
                    assert(q.isEmpty())
                    rmEmpty++
                } else {
                    val expectedElement = list.removeAt(0)
                    assert(expectedElement == element)
                    rm++
                }
            }
        }
    }
    while (list.isNotEmpty()) {
        assert(!q.isEmpty())
        val element = q.dequeue()
        q.check()
        val expectedElement = list.removeAt(0)
        assert(expectedElement == element)
        rm++
    }
    assert(q.isEmpty())
    println("adds: $add")
    println("adds to full queue: $addFull")
    println("removes: $rm")
    println("removes to empty queue: $rmEmpty")
    println("peeks: $peek")
    println("peeks to empty queue: $peekEmpty")
}

fun stackRandomTest() {
    var add = 0
    var addFull = 0
    var rm = 0
    var rmEmpty = 0
    var peek = 0
    var peekEmpty = 0

    val stack = Stack(N)
    val list = mutableListOf<Int>()
    repeat(BIAS_ITER) {
        val bias =
            if (it%2 == 0) {
                0.1
            } else {
                -0.1
            }
        repeat(INNER_ITER) {
            if (Random.nextDouble() < 0.2) {
                val element = stack.peek()
                stack.check()
                if (element == null) {
                    assert(list.isEmpty())
                    assert(stack.isEmpty())
                    peekEmpty++
                } else {
                    val expectedElement = list.last()
                    assert(expectedElement == element)
                    peek++
                }
            }
            if (Random.nextDouble() < 0.5 + bias) {
                val element = Random.nextInt(MAX)
                val result = stack.push(element)
                stack.check()
                if (result) {
                    list.add(element)
                    add++
                } else {
                    assert(stack.isFull())
                    assert(list.size == N)
                    addFull++
                }
            } else {
                val element = stack.pop()
                stack.check()
                if (element == null) {
                    assert(list.isEmpty())
                    assert(stack.isEmpty())
                    rmEmpty++
                } else {
                    val expectedElement = list.removeAt(list.size - 1)
                    assert(expectedElement == element)
                    rm++
                }
            }
        }
    }
    while(list.isNotEmpty()) {
        assert(!stack.isEmpty())
        val element = stack.pop()
        val expectedElement = list.removeAt(list.size - 1)
        assert(expectedElement == element)
        rm++
    }
    assert(stack.isEmpty())
    println("adds: $add")
    println("adds to full queue: $addFull")
    println("removes: $rm")
    println("removes to empty queue: $rmEmpty")
    println("peeks: $peek")
    println("peeks to empty queue: $peekEmpty")
}

fun main() {
    println("Testing stack...")
    stackRandomTest()

    println("Testing queue...")
    queueRandomTest()


}
