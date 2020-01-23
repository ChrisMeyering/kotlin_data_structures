package stack

import kotlin.math.pow
import kotlin.random.Random


fun stackRandomTest() {
    val N = 10000
    var add = 0
    var addFull = 0
    var rm = 0
    var rmEmpty = 0
    var peek = 0
    var peekEmpty = 0

    val stack = Stack(N)
    val list = mutableListOf<Int>()
    repeat(31) {
        val bias =
            if (it%2 == 0) {
                0.1
            } else {
                -0.1
            }
        repeat(1_000_000) {
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
                val element = Random.nextInt(2.0.pow(32).toInt())
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
    stackRandomTest()
}