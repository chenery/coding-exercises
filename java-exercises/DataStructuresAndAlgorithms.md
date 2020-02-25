# Arrays & Strings

# Lists

https://en.wikipedia.org/wiki/List_(abstract_data_type)

- Implemented as linked lists or dynamic arrays
- Also implemented using [https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree] [todo]
- Basis for Queue and Stack

Operations (performance of array implementation):

- add(item) O(1) (amortized)
- get(index) O(1)
- head() O(1)
- tail() O(1)
- iteration (O(n) for n elements)

In Java:
- ArrayList/Vector (synchronized)
- LinkedList (not synchronized) (Collections.synchronizedList)

Example of list iteration using a for loop taken from LinkedList.indexOf
```java
int index = 0;
for (Node<E> x = first; x != null; x = x.next) {
    if (o.equals(x.item))
        return index;
    index++;
}
```

## Dynamic arrays

- Resizable array that can grow (and shrink) with usage
- Resize is typically a new larger array and data copy (expensive)
- Good locality of reference and data cache utilisation

# Queues

[https://en.wikipedia.org/wiki/Queue_(abstract_data_type)]

- FIFO
- Common implementations are circular buffers and (doubly) linked lists
- Used in implementation of BFS
- Deque (doubled ended queue) is a generalized queue that allows operations on the head and tail. 

Operations:

- enqueue (add/offer): O(1)
- dequeue (remove/poll): O(1)
- search: O(n) ave/worse case

Java:

- ArrayDeque<T> implements Deque<T> (extends Queue<T>) (no capacity restrictions, not synchronized, faster than Stack<T> and LinkedList<T>)
- LinkedList<E> implements List<E>, Deque<E> (Doubly linked list, not synchronized)

# Stacks

[https://en.wikipedia.org/wiki/Stack_(abstract_data_type)]

- LIFO, only requires access to one end of the queue, so can be implemented as a singly linked list.
- Required for implementation of DFS.

Operations:

- push: O(1)
- pop: O(1) (peek, pop without removing)

Java:

- Stack<E> extends Vector<E>, but preferred to use ArrayDeque<E> - Stack is threadsafe, hence slower. 
Extension of Vector makes Stack a leaky abstraction, plus Stack is concrete, not an interface.

## Array implementation [todo implement push() and pop()]

```java
class ArrayStack<E> {
    int maxSize;
    int top;
    E[] items;
}
     
```

## List implementation [todo implement push() and pop()]

```java
class Frame<E> {
    E data;
    Frame next;       
}

class ListStack<E> {
    Frame<E> top; // null when empty
    int size;
}
     
```

# Heap

[https://en.wikipedia.org/wiki/Heap_(data_structure)]

- A tree structure where there a relationship when parents and children is maintained, but there is no relationship
between siblings or cousins.
- Partially ordered: a max heap is when the parent is >= the children, min heap when the parent <= the children.
- Designed for repeated removal for the root node
- “Priority Queue”: a tree where the root is the min/max value.

Applications:

- Heapsort
- Selection algorithms
- Graph algorithms
- K-way merge - to merge many sorted input streams into a single sorted output stream

[https://en.wikipedia.org/wiki/Binary_heap]

Insert (bubble/sift up):

- Add the element to the bottom left of the tree
- Compare and swap with parent until heap property is satisfied
- The number of operations depends on the height of the tree, hence O(log n) worse case complexity

Extract (bubble/sift down):

- Replace the root element with that from the bottom right of the tree
- Compare and swap the new root with it's children until heap property is satisfied. (Swap with the larger child for max heap.)
- The number of operations depends on the height of the tree, hence O(log n) worse case complexity

Building a heap

- Insert n items to achieve O(nlog n)
- Better to use Floyd's method of arbitrarily filling the tree, then from the bottom up, recursively sifting down the 
parent nodes of the subtrees. This ends up being O(n).

## Implementing a binary tree as an array
Let n be the number of elements in the heap and i be an arbitrary valid index of the array storing the heap. 
If the tree root is at index 0, with valid indices 0 through n − 1, then each element a at index i has

- children at indices 2i + 1 and 2i + 2
- its parent at index floor((i − 1) ∕ 2). 

This implementation is relevant to Heapsort and Priority Queue.

# Priority Queue

[https://en.wikipedia.org/wiki/Priority_queue]

Used with graphs a lot:

- Dijkstra's algorithm for minimum distance to get the minimum adjacent edge.
- Prim's algorithm for minimum spanning tree

Operations:

- isEmpty():
- insertWithPriority(priority): 
- pullHighestPriorityElement(): 

Java:

- PriorityQueue<E> extends AbstractQueue<E> -> dynamic array, representing a balanced binary heap
- add/offer & poll/dequeue: O(log n)
- remove(Object): O(n)
- peek(): O(1)

# Hash & Maps

# Sorting Algorithms

# Trees

# Graphs (BFS & DFS)

# Recursion