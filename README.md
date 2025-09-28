Assignment 1: Divide-and-Conquer Algorithms

1. Architecture Notes

All algorithms are implemented using safe recursion patterns. A global Metrics class is used to track comparisons, allocations, maximum recursion depth, and execution time. For each algorithm:
	•	MergeSort:
	•	A reusable buffer array is used for merging to minimize memory allocations.
	•	Small arrays (≤16 elements) are sorted with insertion sort to reduce recursion overhead.
	•	QuickSort:
	•	Random pivot selection ensures probabilistic O(n log n) depth.
	•	Always recurses on the smaller partition first to limit recursion depth; larger partition is handled afterwards to reduce stack usage.
	•	Deterministic Select (Median-of-Medians):
	•	Groups of 5 are used to select the pivot.
	•	Recursion occurs only on the required partition.
	•	Closest Pair:
	•	Points are sorted by x-coordinate initially.
	•	Recursive splitting is applied, with a “strip” check limited to 7 neighbors for efficiency.

2. Recurrence Analysis

MergeSort
	•	Method: Divide array into two halves, sort recursively, merge.
	•	Recurrence: T(n) = 2T(n/2) + Θ(n)
	•	Θ-Result: Θ(n log n) (Master Theorem, Case 2)
	•	Comment: Matches theoretical depth and execution time for tested array sizes.

QuickSort (Randomized)
	•	Method: Partition around random pivot, recurse smaller side first.
	•	Recurrence (expected): T(n) = T(n/2) + T(n/2) + Θ(n) ≈ Θ(n log n)
	•	Θ-Result: Θ(n log n) average case, O(n²) worst-case unlikely due to random pivot.
	•	Comment: Metrics confirm recursion depth rarely exceeds 2·log₂(n).

Deterministic Select
	•	Method: Median-of-medians pivot, recurse into one partition.
	•	Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n)
	•	Θ-Result: Θ(n) (Akra–Bazzi method)
	•	Comment: Observed allocations are consistent with theoretical predictions.

Closest Pair
	•	Method: Divide points, solve recursively, combine via strip check.
	•	Recurrence: T(n) = 2T(n/2) + Θ(n)
	•	Θ-Result: Θ(n log n)
	•	Comment: Depth and comparison metrics match expectations for n ≤ 1000.

3. Plots

Plots are generated from Metrics outputs (time vs n and depth vs n).
	•	MergeSort and QuickSort show log-linear growth in time.
	•	Maximum recursion depth for QuickSort is proportional to log₂(n).
	•	Deterministic Select depth grows slower than log₂(n), confirming linear-time behavior.
	•	Closest Pair time grows as n log n; small constant factors observed.

Discussion:
	•	Cache effects: MergeSort benefits from buffer reuse; large arrays may have slight slowdown due to cache misses.
	•	Garbage Collection: QuickSort and Deterministic Select allocate temporary arrays; minimal impact on measured times.

4. Summary
	•	All implementations match theoretical Θ-complexities.
	•	Observed recursion depths and times align closely with Master Theorem and Akra–Bazzi predictions.
	•	Constant-factor effects are minor but noticeable for very small or very large arrays.
	•	Overall, divide-and-conquer algorithms are efficient and correctly implemented for the assignment.
