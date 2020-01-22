package queue

import kotlin.math.pow
import kotlin.random.Random

fun random_test() {
    val N = 10000
    var add = 0
    var rm = 0
    var addFull = 0
    var rmEmpty = 0

    val q = Queue(N)
    val list = mutableListOf<Int>()
    q.check()

    repeat(21) {
        val bias =
            if (it%2 == 0) {
                0.1
            } else {
                -0.1
            }
        repeat(1000000) {
            if (Random.nextDouble() < 0.5 + bias) {
                val element = Random.nextInt(2.0.pow(32).toInt())
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
                    assert(list.size == 0)
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
    while(!q.isEmpty()) {
        val element = q.dequeue()
        q.check()
        val expectedElement = list.removeAt(0)
        assert(expectedElement == element)
        rm++
    }
    println("adds: $add")
    println("adds to full queue: $addFull")
    println("removes: $rm")
    println("removes to empty queue: $rmEmpty")
}

fun main(args: Array<String>) {
    random_test()
}