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

# Hashtable

- An associative array or map
- Load factor = n / k, where n = number of occupied entries, k is the number of buckets. 
HashMap in Java 10 is 0.75 at which point the array might be resized to increase k.
- Collision resolution typically done using **separate chaining** with linked lists. In which can worse case is then O(n). 
Hashtable would then inherit issues with list. 
To improve the worst case O(n) to O(log n) a **self balancing binary search** tree might be used instead. E.g. HashMap in Java 8.
- Collision resolution also done with open addressing, which is when in the case of a collision, another slot/bucket is chosen 
based on another algorithm, (e.g. linear probing) - this is a simpler implementation and can work well for small records and
smaller load factors.
- Dynamic Resizing - how the table can grow or shrink based on load factor. Usually resizing required re-hashing all the keys
which can be expensive. So some implementations can incrementally resize by maintaining the old and new table until all the keys
are moved to the new one.
- Hash tables can be distributed (**DHT**) - the hashing function locates the entry on a particular node on a network.
DHTs have systems for adding and removing nodes such that not all the nodes will need to be rehashed. E.g. Having a logical
ring of nodes, each responsible for a range of the keyspace. Inserting a node between 2 others means only 2 or the total 
nodes have to redistribute their keys. E.g. Apache Cassandra. 

Java:

- HashMap<K,V> implements Map<K,V> 
- LinkedHashMap<K,V> extends HashMap<K,V> -> maintains a doubly linked list for ordering.
- HashSet<E> implements Set<E> - backed by a HashMap
- LinkedHashSet<E> - backed by a LinkedHashMap

Operations:

- get(key): O(1)
- set(key, value): O(1)
- add(key, value)/remove(key): O(1) average, O(n) worse case

# Trees

## Binary Trees

### Traversal
Given a tree:
     1
    /  \
   2    3
  / \  / \
 4  5 6   7
 
#### Inorder traversal:
- visit left subtree
- visit root
- visit right subtree

Output: 4, 2, 5, 1, 6, 3, 7

- For BST produces non-decreasing output

#### Preorder traversal:
- visit root
- visit left subtree
- visit right subtree

Output: 1, 2, 4, 5, 3, 6, 7

- For getting the prefix/polish order of the tree (e.g. Expression trees)
- Used to copy the tree

#### Postorder traversal:
- visit left subtree
- visit right subtree
- visit root

Output: 4, 5, 2, 6, 7, 3, 1

- For getting the postfix/reverse polish order of the tree
- For deleting a tree, from the leaf nodes
 
### Binary Search Tree (Ordered binary trees)

[https://en.wikipedia.org/wiki/Binary_search_tree]

- Ordered based on the fact the left node is less than the parent, and the right node is more.
- Can build sets and maps from BSTs
- Search & Insert & Delete: O(log n) Average, O(n) worse case
- Space O(n)
- Typically HashTable is faster, BST is better is you want keys in order, 
e.g. range queries, lowest common ancestor, anything needing traversals, sorting

Search Recursively:
```java
class RecursiveSearch {
    Node search(Node node, String key) {
        if (node == null || node.key == key) {
            return node;
        } else if (node.key < key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
}
```

Search Iteratively
```java
class IterativeSearch {
    Node search(Node root, String key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                return current;
            } else if (current.key < key) {
                current = node.left;
            } else {
                current = node.right;
            }
        }
        return current;
    }
}
```

Insert Recursively: insert at first free node
```java
class RecursiveInsert {
    Node insert(Node node, String key, String value) {
        // return the root of the tree
        return insertAt(node, key, value);
    }
    
    Node insertAt(Node node, String key, String value) {
        if (node == null) {
            return new Node(key, value);
        } else if (node.key < value) {
            node.left = insertAt(node.left, key, value);
        } else {
            node.right = insertAt(node.right, key, value);
        }
        return node;
    }
}
```

Insert Iteratively: in Java requires a trailing pointer
```java
class IterativeInsert {
    Node insert(Node node, String key, String value) {
        Node newNode = new Node(key, value);
        Node current = node;
        Node previous = null;
        
        while (current != null) {
            previous = current;
            if (current.key < key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        // New root
        if (previous == null) {
            return newNode;
        }
        
        if (key < previous.key) {
            previous.left = newNode;
        } else {
            previous.right = newNode;
        }
        // Return the parent of the new node
        return previous;
    }
}
```

Deletion:

## Searching e.g. Binary search

### AVL Tree (A self balancing BST) 
### Red-black tree (A self balancing BST)

## Search Trees

## B Tree
## Tries

## K-ary Trees

# Sorting Algorithms

# Graphs (BFS & DFS)

# Recursion

# Extras
[https://stackoverflow.com/questions/15301885/calculate-value-of-n-choose-k]
```
function choose(n, k)
    if k == 0 return 1
    return (n * choose(n - 1, k - 1)) / k
```