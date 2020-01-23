package linkedlist

class LinkedList {
    var head: Node? = null
    val tail: Node?
        get() {
            var last = head
            while(last?.next != null) {
                last = last.next
            }
            return last
        }

    //insert element at front of LinkedList
    fun push(element: Any) {
        val newNode = Node(element)
        newNode.next = head
        head = newNode
    }

    //append element to LinkedList
    fun add(element: Any) {
        val newNode = Node(element)
        if (head == null) {
            head = newNode
        } else {
            tail!!.next = newNode
        }
    }

    fun insertAt(index: Int, element: Any): Boolean {
        if (index == 0) {
            push(element)
            return true
        }
        var currIdx = -1
        var prevNode: Node? = null
        var currNode = head

        while (currIdx != index - 1 && currNode !=  null) {
            prevNode = currNode
            currNode = currNode.next
            currIdx++
        }
        if (currIdx != index - 1) {
            return false
        }
        val newNode = Node(element)
        newNode.next = currNode
        prevNode!!.next = newNode
        return true
    }

    fun insertAfter(key: Any, element: Any): Boolean {
        val newNode = Node(element)

        var keyNode = head
        while (keyNode != null && keyNode.value != key) {
            keyNode = keyNode.next
        }
        if (keyNode == null) {
            return false
        }
        newNode.next = keyNode.next
        keyNode.next = newNode
        return true
    }

    fun delete(key: Any): Boolean {
        if (head == null) {
            return false
        }

        var nodeBeforeKey: Node? = null
        var currNode = head

        while (currNode != null && currNode.value != key) {
            nodeBeforeKey = currNode
            currNode = currNode.next
        }
        if (currNode == null) {
            return false
        }

        nodeBeforeKey!!.next = currNode.next
        return true
    }

    fun printList() {
        var node = head
        print("[")
        while (node != null) {
            print(node.value)
            if (node.next != null) {
                print(", ")
            }
            node = node.next
        }
        print("]")
    }
}

fun main() {
    val linkedList = LinkedList()
    linkedList.insertAt(0,0)
    linkedList.insertAt(0, 1)
    linkedList.insertAt(2, 5)
    linkedList.printList()
}