# TODO:

- Complete notes
 - Tries
 - Merge Sort
 - Quick Sort
 
- Review FB materials again - is there anything that needs setting up, practice etc - headset with microphone etc.

- https://www.facebook.com/careers/life/sample_interview_questions - 3 questions here

- Do more Careercup exercises
- Summarise heuristics from exercises
- Commit notes to memory

# Arrays & Strings

# Lists

https://en.wikipedia.org/wiki/List_(abstract_data_type)

- Implemented as linked lists or dynamic arrays
- Also implemented using Self Balancing Binary Search Tree (for ordered lists)
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

# Trees & Graphs

A graph is a collection of vertices, V (nodes) and connecting edges E.

A tree is rooted, non-cyclic, and typically a specific number of children for each node.

Both trees and graphs can be traversed using a DFS or BFS.  

## Depth First Search (DFS) Traversal

- Recurse to the leaf node of the first child -> children before siblings

```java
class DFSInOrderBinaryTree {
    // call with root
    dfs(Node node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        visit(node);
        dfs(node.right);
    }
}
```

```java
class DFSPreOrderGraph {
    dfs(Graph g, Node node, Set<Node> discovered) {
        visit(node);
        for (Node adjacent : g.adjacentEdges(node)) {
            if (!discovered.contains(adjacent)) {
                dfs(g, adjacent, discovered);
            }
        }
    }
}
```

## Breadth First Search (BFS)
[https://en.wikipedia.org/wiki/Breadth-first_search]

- Visits siblings before children

     1
    /  \
   2    3
  / \  / \
 4  5 6   7
 
Traversal order: 1 to 7

- Uses a queue
- "Finding shorted path"

```java
class BFSBinaryTree {
    
    // O(N) where n is the number of nodes in the tree
    bfs(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty) {
            Node node = q.remove();
            visit(node);
            q.add(node.left);
            q.add(node.right);
        }
    }
}
```

```java
class BFSGraph {
    
    // O(|V| + |E|) V - vertices, E - edges
    bfs(Graph g, Node start) {
        Queue<Node> q = new ArrayDeque<>();
        Set<Node> discovered = new HashSet<>();
        q.add(start);
        discovered.add(start);
        while(!q.isEmpty) {
            Node node = q.remove();
            // return here if node is the goal
            visit(node);
            for (Node adjacent : g.adjacent(node)) {
                if (!discovered.contains(adjacent)) {
                    discovered.add(adjacent);
                    // NOTE: the parent link traces the SHORTEST PATH back to the start
                    adjacent.parent = node;
                    q.add(adjacent);
                }
            }
        }
    }
}
```

## Binary Trees
### DFS Traversal
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

Deletion: [todo]

### Self balancing binary search tree
[https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree]

A binary search tree that automatically keeps it's height small in the face of arbitrary insertions and deletions.

This is done to ensure O(log n) worse case search time.

[Tree rotations](https://en.wikipedia.org/wiki/Tree_rotation) are performance during insertions. 
This means to change the structure without changing the ordering. A child is moved up into the position of the parent,
the parent is pushed down the appropriate branch, and a child of the original child is redistributed to the other branch. 

### AVL Tree (A self balancing BST) [todo related interview questions]
[https://en.wikipedia.org/wiki/AVL_tree]

- The heights of the 2 subtrees differ by at most 1. This difference is known as the **balance factor**.
- One or more tree rotations may be required at insertion time to maintain this **balance factor**
- AVL trees can be coloured red black, they are more rigid, offer better search performance.


### Red-black tree (A self balancing BST) [todo related interview questions]
[https://en.wikipedia.org/wiki/Red%E2%80%93black_tree]

- Each node in this self balancing binary tree has an extra bit indicating whether it is red or black

The follow properties are used to ensure balance:

- Nodes are red or black
- The root is always black
- All leaves (NIL) are black
- If a node is red, both children are black
- Every path from any node to any of it's descendants goes through the same number of black nodes
- Attempt to insert new nodes as red

## B Tree [todo if time]
## Tries [todo if time]
[https://en.wikipedia.org/wiki/Trie]
- Search tree, also called Prefix tree

## K-ary Trees [todo if time]

## Searching 
### Binary search
[https://en.wikipedia.org/wiki/Binary_search_algorithm]

- Find the position of a value in a **sorted** array.
- Worse case & average performance O(log n)
- Space: O(1)

```java
class BinarySearch<E> {
    
    /*
     * @param data sorted data
     * @param n, the size of the array
     * @param target, the value we are searching for
     * @return the index at which the value resides, or -1
     */
    int search(int[] data, int n, int target) {
        int lower = 0;
        int upper = n - 1;
        
        while(lower <= upper) {
            int midIndex = (lower + upper) / 2;
            if (data[midIndex] < target) {
                lower = midIndex + 1;   
            } else if (data[midIndex] > target) {
                upper = midIndex - 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
```

### Backtracking
[https://en.wikipedia.org/wiki/Backtracking]

This is a graph and tree search algorithm to incrementally build solution candidates as a tree, then backtrack (return to parent),
if the candidate proves to not yield a solution.

Time complexity is typically O(|V| * |E|) where |V| is max length of the solution, and |E| is the number of candidates at each V. 

```java
def bt(c):
    s ← first(P, c)
    if reject(P, c) then return
    if accept(P, c) then output(P, c)
    
    while s ≠ NULL do
        bt(s)
        s ← next(P, s)
```

## Graphs Algorithms [todo]
### Dijkstra's Shorted Path
### Prims's Minimum Spanning Tree
### Kruskal's Minimum Spanning Tree
### Huffman Coding

# Greedy Algorithms [todo learn algorithms and do interview questions]

Is one that makes locally optimal choices to find a global maximum.

[https://www.geeksforgeeks.org/top-20-greedy-algorithms-interview-questions/]

Greedy algorithms are seen often in graph algorithms:

- Kruskal's Minimum Spanning Tree

Minimum Spanning Tree:

- Spanning Tree of a Graph G: A sub-graph of G tree that includes all vertices with minimum number of edges
- Minimum Spanning Tree: The spanning tree of G that has the minimum total weight (sum of edge weights)    

- Prims's Minimum Spanning Tree
- Dijkstra's Shorted Path

Dijkstra’s algorithm is very similar to Prim’s algorithm. The shortest path tree is built up, edge by edge. 
We maintain two sets: a set of the vertices already included in the tree and the set of the vertices not yet included. 
The Greedy Choice is to pick the edge that connects the two sets and is on the smallest weight path from source to the 
set that contains not yet included vertices.

- Huffman Coding 

Huffman Coding is a loss-less compression technique. It assigns variable-length bit codes to different characters. 
The Greedy Choice is to assign least bit length code to the most frequent character.

# Sorting Algorithms
## Insertion Sort
[https://en.wikipedia.org/wiki/Insertion_sort]
- Maintain an ordered list and an unordered remainder. The test value moves back through the array to it's ordered
position by continually swapping with the previous index.
- In place and stable
- use for small or
  partially-sorted arrays
- Time: Ave O(n^2) comparisons and swaps, O(n) best
- Space: O(n) + O(1) Aux
- Java: Used by Arrays.sort() for tiny arrays (< 47) - see DualPivotQuicksort.java

[todo code]
```java

```

## Selection Sorts
### Selection Sort
[https://en.wikipedia.org/wiki/Selection_sort]
- Maintain an ordered list and an unordered remainder. Select the min/max from the unordered remainder to add to the 
ordered list. Key benefit, **minimum number of swaps** - useful in situations where writing to memory is expensive.
- In place, but NOT stable. Space: O(1)
- O(n^2) comparisons, O(n) swaps.

[todo code]
```java

```

### Heap Sort
[https://en.wikipedia.org/wiki/Heapsort]
- **Improved selection sort**, where the unsorted remainder is maintained in a **heap**.
- In place, but NOT stable. Space: O(n) + O(1) - heap plus swap
- Performance improves from O(n^2) to O(n log n) - typically slower than Quicksort but has O(n log n) worse case guarantee

[todo code]
```java

```
 
## Exchange Sorts
### Bubble Sort
[https://en.wikipedia.org/wiki/Bubble_sort]
- Repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order. 
The pass through the list is repeated until the list is sorted (no swaps were made on the pass).
- In place and stable O(1) Space
- Performance of O(n^2) - rarely used -> use insertion sort instead

[todo code]
```java

``` 

### Quicksort
[https://en.wikipedia.org/wiki/Quicksort]
- In practice faster than mergesort and heapsort
- Divide and conquer algorithm
- It works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, 
according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively. This 
- In-place, Efficient implementations of Quicksort are not a stable sort
- Space: O(n) (depends of version)
- Performance: O(n log n), degrades to O(n^2) for pre-sorted inputs or arrays of identical inputs 
 
 - The basic form chooses the pivot (shaded) element as the last element of the partition. 
 ![quicksort example](img/400px-Quicksort-diagram.svg.png)
 
 ```java
algorithm quicksort(A, lo, hi) is
    if lo < hi then
        p := partition(A, lo, hi)
        quicksort(A, lo, p - 1)
        quicksort(A, p + 1, hi)

algorithm partition(A, lo, hi) is
    pivot := A[hi]
    i := lo
    for j := lo to hi do
        if A[j] < pivot then
            swap A[i] with A[j]
            i := i + 1
    swap A[i] with A[hi]
    return i

// sort the entire array    
quicksort(A, 0, length(A) - 1).
```

## Merge Sort
- Divide and conquer algorithm
- Conceptually, a merge sort works as follows:

1. Divide the unsorted list into n sublists, each containing one element (a list of one element is considered sorted).
2. Repeatedly merge sublists to produce new sorted sublists until there is only one sublist remaining. This will be the sorted list.

- Space: O(n) with O(n) auxillary (not in place)
- Performance: O(n log n) worst case guarantee
- Stable
- Top down and bottom up implementations

![Top down mergesort example](img/600px-Merge_sort_algorithm_diagram.svg.png)

[todo top down implementation]
```java

```

[todo bottom up implementation]
```java

```

## Distribution Sorts
### Counting Sort

- For sorting small integers, where the range of values is known and small. 
- Makes use of prefix sum. 
- Radix sort is a more generalised version.
- Space: O(n + k), where k is the range of non-negative key values
- Performance: O(n + k) 

Algorithm:
- Count the frequency of the input terms in an auxiliary array (histogram)
- Perform a prefix sum over count for the range of input values (|k|)
- The value of the prefix sum maps to the index/position of the value in the sorted output array
- To handle duplicates, the prefix sum value is the decremented to sift the output along

[todo example]
```java

```

# Dynamic Programming [todo interview questions]
Divide and conquer is an algorithm design paradigm based on multi-branched recursion. A divide-and-conquer algorithm 
works by recursively breaking down a problem into two or more sub-problems of the same or related type, until these 
become simple enough to be solved directly. The solutions to the sub-problems are then combined to give a solution to 
the original problem.

- sorting (e.g., quicksort, merge sort),
- multiplying large numbers (e.g. the Karatsuba algorithm), 
- finding the closest pair of points, 
- syntactic analysis (e.g., top-down parsers),

[https://www.geeksforgeeks.org/dynamic-programming/]
[https://programming.guide/dynamic-programming-vs-memoization-vs-tabulation.html]

- Longest common subsequence: [https://www.youtube.com/watch?v=ASoaQq66foQ]
- Tabulation/DP -> bottom up
- Memoization -> top down

# Extras
[https://stackoverflow.com/questions/15301885/calculate-value-of-n-choose-k]
```
function choose(n, k)
    if k == 0 return 1
    return (n * choose(n - 1, k - 1)) / k
``` 

# References

- [https://algs4.cs.princeton.edu/cheatsheet/]
- https://www.freecodecamp.org/news/coding-interviews-for-dummies-5e048933b82b/ [todo review this]

# When considering an interview question:

What kind of solution can be used:

- Brute force
- Map -> e.g. remove cost of multiple searches for the same key by mapping, e.g. Two Sum, in this a counting problem?
- Recursive -> does this lead to DP solution?
- DFS e.g. binary search
- BFS e.g. for shortest path
- Does sorting help?
- Can you use back tracking

Maybe try to implement a recursive solution first.

# Interview tips:

- Ask questions, this is a conversation
- Compare multiple solutions with reasoning, discuss space and time complexity
- Can you optimize your solution
- Produce some test cases
- Code won't be run, so be prepared to dry run the code with appropriate test cases