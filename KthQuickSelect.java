/* Given an array of N elements, not necessarily in ascending order,
devise an algorithm to find the kth largest one. It should run in O(N) time on random inputs.

Are we really supposed to be coming up with these on the fly???

Or is the intent for us to go do searches and find out there's 10 page long wiki articles
on these "well known" painful problems?

Hopefully it's the latter because there's no way...

ref: https://en.wikipedia.org/wiki/Selection_algorithm
ref: https://stackoverflow.com/questions/251781/how-to-find-the-kth-largest-element-in-an-unsorted-array-of-length-n-in-on
ref: https://www.youtube.com/watch?v=wiNfjkMDl3A
ref: https://www.youtube.com/watch?v=XEmy13g1Qxc
*/


import java.util.Random;

public class KthQuickSelect {
        public static int kthLargest(int[] inputArray, int k) {
        if (k < 1 || k > inputArray.length) throw new IllegalArgumentException("k out of range");
        int target = inputArray.length - k; // kth largest -> target index if array were sorted ascending
        int lo = 0;
        int hi = inputArray.length - 1;
        Random rng = new Random();

        while (lo <= hi) {
            int p = lo + rng.nextInt(hi - lo + 1);      // random pivot index
            // https://www.geeksforgeeks.org/dsa/lomuto-partition-algorithm/
            int idx = partition(inputArray, lo, hi, p);          // Lomuto partition
            if (idx == target) return inputArray[idx];
            if (idx < target) lo = idx + 1; else hi = idx - 1;
        }
        throw new AssertionError("unreachable");
    }

        private static int partition(int[] a, int lo, int hi, int p) {
        int pv = a[p];
        swap(a, p, hi);            // move pivot to end
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pv) swap(a, i++, j);  // <= to push equals left; works with dups
        }
        swap(a, i, hi);            // place pivot
        return i;                  // pivot final index
    }

        private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

        // quick tests
        public static void main(String[] args) {
        int[] x = {9, 1, 5, 3, 7, 8, 2, 6, 4};
        System.out.println(kthLargest(x, 1)); // 9
        System.out.println(kthLargest(x, 3)); // 7
        System.out.println(kthLargest(x, 9)); // 1
    }
}
