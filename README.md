# CS301 — Chapter 4 Readme: Algorithms and Data Structures (Princeton IntroCS §4)

> Based on Princeton's IntroCS Chapter 4 for Java. See section notes 4.1 through 4.5 and quiz-problem study notes.

---

## 4.1 Performance (Analysis of Algorithms)

**Key ideas**
- Respect the cost. Measure and model running time and memory, not just correctness.
- Follow the scientific method for performance work: observe, hypothesize, predict, verify, validate.
- Use empirical tools: Stopwatch timing, doubling tests, and plotting. The classic `ThreeSum` client exhibits about cubic growth, so doubling `n` multiplies time by about 8.
- Use mathematical tools: tilde notation for leading terms, and order of growth classes like log n, n, n log n, n^2, n^3.

**Doubling test and log–log plot**
- The doubling hypothesis asks how T(2n)/T(n) behaves. For `ThreeSum` the ratio approaches 8.
- A log–log plot of running time vs n becomes a line whose slope is the exponent, so slope 3 suggests n^3.

**How to count**
- Total time is roughly sum over all statements of (cost per statement) × (frequency of execution). Cost per statement depends on the system, frequency depends on your algorithm.
- Focus on the inner loop. For triple nested loops that execute a test n(n − 1)(n − 2)/6 times, the leading term is ~ n^3/6.

**Order-of-growth shorthand**
- If T(n) ~ c·f(n), then f(n) is the order of growth. Typical f(n) in this chapter: log n, n, n log n, n^2, n^3.
- Ratios help: if T(n) ~ c·n^3 then T(2n)/T(n) → 8.

**Memory estimates on the JVM (rules of thumb)**
- Primitives have fixed sizes (for example, int is 32 bits).
- Every object has header overhead plus its fields.
- Arrays are objects, so they have a header and then elements; an n by n int matrix stores about ~ 4n^2 bytes just for the elements, ignoring headers.
- Strings store 2 bytes per character plus overhead.

**What to practice**
- Use `Stopwatch` and doubling tests to build a hypothesis, then validate it on larger n.
- Derive tight upper bounds by counting loop iterations and using tilde notation.

---

## 4.2 Sorting and Searching

**Binary search**
- Solve search in a sorted array in O(log n). Keep a current [lo, hi] range, probe mid = lo + (hi − lo)/2, and throw away half each step.
- Same idea in bisection to invert a monotone function f: maintain bounds (lo, hi) with f(lo) ≤ y ≤ f(hi), then bisect until the interval is small enough.

**Insertion sort**
- Simple in-place stable sort. Treat the left prefix as sorted and insert the next item by shifting larger elements right.
- Best case is already sorted input: linear time.
- Average and worst with random or reverse input: about 1/4 n^2 and 1/2 n^2 comparisons respectively, so quadratic time.

**Mergesort**
- Divide and conquer: split array, sort subarrays, then merge.
- Stable, deterministic worst case about n log n comparisons, and linear extra space for the auxiliary array.
- Why it matters: the gap between n^2 and n log n is huge at realistic n.

**Frequency counts via sorting**
- Sort to coalesce equal keys, make a single pass to count frequencies, then sort counters by count. This is a classic reduction to sorting.

**Practice pointers**
- Prefer mergesort when you need worst case guarantees and stability.
- Use binary search for membership tests and rank queries on sorted arrays.
- For integers from a small domain, counting sort runs in linear time and is a good exercise.

---

## 4.3 Stacks and Queues

**Stacks (LIFO)**
- Core ops: `push`, `pop`, `isEmpty`. Great for undo, recursion, parsing, DFS, and more.
- Implementations
  - Array backed: keep `items[]` and `n`. On `push` double the array when full, on `pop` halve when one quarter full. This gives amortized constant time and avoids overflow.
  - Linked list backed: keep a `first` node with fields `item` and `next`. Push and pop at the head in constant time.

**Queues (FIFO)**
- Core ops: `enqueue`, `dequeue`, `isEmpty`.
- Implementations
  - Linked list with `first` and `last` references. Enqueue at the tail, dequeue at the head.
  - Resizing array with circular indexing for head and tail.

**Generics and autoboxing**
- Use `Stack<Item>` or `Queue<Item>` with a type parameter. Java autoboxing lets you store primitive values via their wrapper types when needed.

**Applications to know**
- Balanced parentheses: push opening delimiters, on closing make sure the stack is nonempty and that the type matches; valid if stack is empty at the end.
- Expression evaluation: Dijkstra two-stack algorithm for fully parenthesized infix expressions.
- M/M/1 queue simulation: models a single-server FIFO system with exponential interarrival and service times.

---

## 4.4 Symbol Tables

**What a symbol table is**
- A dictionary from keys to values that supports `put`, `get`, `contains`, iteration, and often `delete`.

**API conventions**
- Keys are immutable and not null; values are not null.
- `get` returns null when not found, so `contains(k)` is commonly defined as `get(k) != null`.
- Ordered symbol tables support additional operations if keys implement `Comparable`.

**Elementary implementations**
- Sequential search in an unordered list or array: simple but linear per operation.
- Binary search on a sorted array: `get` is logarithmic time but `put` is linear due to shifting.

**Efficient implementations**
- Hash table with separate chaining
  - Representation: array of m chains (linked lists). Use `hash(key) = abs(key.hashCode() % m)`.
  - Expected constant time for `put` and `get` if the hash function spreads keys well and you resize to keep load factor in check.
- Binary search tree (BST)
  - Node stores `(key, value, left, right)` and maintains symmetric order: keys in left are less than the node key, right are greater.
  - Search and insert follow the same left or right decision logic.
  - Cost depends on shape. Best balanced case gives logarithmic time. Random inserts have about 2 ln n compares on average. Worst case degenerates to a linked list with linear time.

**Balanced BSTs**
- Red black trees and similar structures avoid the worst case by rebalancing and guarantee logarithmic performance.

---

## 4.5 Case Study: Small-World Phenomenon

**Graphs**
- A graph has vertices and edges. Many systems are well modeled as graphs: social networks, transportation, the web, biochemical interactions, and more.
- The Graph API supports adding edges and iterating over vertices and their neighbors. The canonical representation here is a symbol table of sets: keys are vertices, values are neighbor sets (adjacency lists).

**Movie data sets and the Bacon number**
- Vertex for each performer and each movie. Edge between a performer and a movie if the performer appeared in the movie.
- Breadth-first search on this bipartite graph finds shortest paths and Kevin Bacon numbers. Bipartite graphs have clustering coefficient 0, so the performer–movie graph is not small-world.

**Small-world properties**
- Sparse edges relative to possible edges.
- Short average path length between random vertices.
- Local clustering: neighbors of a vertex are likely to be connected with one another.
- The performer–performer graph, in which two performers are adjacent if they appeared in the same movie, does show small-world behavior on the giant component.

**Programs to know**
- `Graph.java`, `IndexGraph.java` for building graphs from data.
- `PathFinder.java` for BFS and shortest paths.
- `SmallWorld.java` and `Performer.java` for computing average degree, average path length, and clustering, and for verifying small-world properties.

---

## Notes for the Quiz Problems

**Q1. Triple-nested matrix multiply loop**
- The i, j, k loops each run n times, so the body executes n^3 times. Order of growth is cubic. This matches the inner loop idea from 4.1.

**Q2. mergeSort recurrence**
- T(n) = 2 T(n/2) + Θ(n) for the merge step, which solves to n log n by the Master Theorem. Copying subarrays adds linear work per level and does not change the asymptotic.

**Q3. Naive recursive Fibonacci**
- The recurrence T(n) ≈ T(n − 1) + T(n − 2) + O(1) leads to exponential growth, about φ^n. Memoization or bottom up DP reduces this to linear time.

**Q4. Find i with a[i] = i in a sorted array of distinct integers**
- Define f(i) = a[i] − i. Since a is strictly increasing and i increases by 1, f is strictly increasing. Do binary search on f to find where it is zero. That gives O(log n) time.

**Q5. kth largest in expected linear time**
- Use Quickselect on index target = n − k. Pick a random pivot, partition, and recurse only into the side that contains target. Expected time O(n). A min heap of size k runs in O(n log k) and is a good alternative when k is small.

**Q6. Linked lists vs arrays**
- Linked lists grow and shrink by pointer changes and support O(1) insert and delete at a known position, but random indexing is O(n) and they have poor locality. Arrays support O(1) indexing and better cache locality, but resizing and middle insertions or deletions can be expensive.

**Q7. Linked-list splice**
```
t.next = x.next;
x.next = t;
```
- Inserts node `t` immediately after node `x`, preserving the remainder of the list via the first assignment.

**Q8. Data type for Undo**
- Use a stack so the most recent action is undone first.

**Q9. Balanced brackets with a stack**
```java
import java.util.*;
public class Parentheses {
    private static boolean balanced(String s) {
        Deque<Character> st = new ArrayDeque<>();
        Map<Character,Character> m = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (c=='('||c=='['||c=='{') st.push(c);
            else if (c==')'||c==']'||c=='}') {
                if (st.isEmpty() || st.pop() != m.get(c)) return false;
            }
        }
        return st.isEmpty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(balanced(sc.nextLine()));
    }
}
```

**Q10. Worst-case `put` or `get` in a basic BST**
- Linear time when the tree becomes a chain due to adversarial insertion order.

**Q11. Advantage of a hash table**
- Average constant-time insert, lookup, and delete if the hash function spreads keys well and the table resizes.

**Q12. `HashST.java` methods**
- `contains(k)` returns `get(k) != null` under the non-null key and value convention.
- `size()` tracks a count of key value pairs.
- `keys()` iterates chains or array slots to collect keys, depending on whether you use separate chaining or linear probing.

---

## Code index from the chapter (Java)
- 4.1: `ThreeSum.java`, `DoublingTest.java`
- 4.2: `Questions.java`, `Gaussian.java`, `BinarySearch.java`, `Insertion.java`, `InsertionTest.java`, `Merge.java`, `FrequencyCount.java`
- 4.3: `ArrayStackOfStrings.java`, `LinkedStackOfStrings.java`, `ResizingArrayStackOfStrings.java`, `Stack.java`, `Evaluate.java`, `Queue.java`, `MM1Queue.java`, `LoadBalance.java`
- 4.4: `Lookup.java`, `Index.java`, `HashST.java`, `BST.java`, `DeDup.java`
- 4.5: `Graph.java`, `IndexGraph.java`, `PathFinder.java`, `SmallWorld.java`, `Performer.java`

---

## Quick formulas and reminders
- Doubling rule: if T(n) ~ c·n^p then T(2n)/T(n) → 2^p.
- Insertion sort: best ~ n, average ~ 1/4 n^2, worst ~ 1/2 n^2 comparisons.
- Mergesort: worst ~ n log n comparisons, stable, needs linear auxiliary space.
- Hash tables: expected O(1) for put and get with good hashing and resizing.
- BSTs: best log n, average about 2 ln n compares, worst n.
- BFS gives unweighted shortest paths in graphs.

