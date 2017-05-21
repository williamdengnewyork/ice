package com.exercise.ice;

public class SortingUtil {

	// mergeSort
	public static int[] mergeSort(int[] list) {
		if (list.length <= 1) {
			return list;
		}
		// 1. Split the array in two sub-arrays
		int[] first = new int[list.length / 2];
		int[] second = new int[list.length - first.length];
		System.arraycopy(list, 0, first, 0, first.length);
		System.arraycopy(list, first.length, second, 0, second.length);

		// 2. Sort each half sub-array -- !!!! Recursive function call
		mergeSort(first);
		mergeSort(second);

		// 3. Merge the halves together, overwriting the original array "list"
		merge(first, second, list);
		return list;
	}

	// static proc method
	private static void merge(int[] first, int[] second, int[] result) {
		// Merge both halves into the result array
		// Next element to consider in the first array
		int iFirst = 0;
		// Next element to consider in the second array
		int iSecond = 0;

		// Next open position in the result
		int j = 0;
		// As long as neither iFirst nor iSecond is past the end, move the
		// smaller element into the result.
		while (iFirst < first.length && iSecond < second.length) {
			if (first[iFirst] < second[iSecond]) {
				result[j] = first[iFirst];
				iFirst++;
			} else {
				result[j] = second[iSecond];
				iSecond++;
			}
			j++;
		}
		// copy what's left
		System.arraycopy(first, iFirst, result, j, first.length - iFirst);
		System.arraycopy(second, iSecond, result, j, second.length - iSecond);
	}

	// quick sort
	public static void quickSort(int[] arr, int low, int high) { // O(NlogN)
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}

			while (arr[j] > pivot) {
				j--;
			}

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);

		if (high > i)
			quickSort(arr, i, high);
	}

	
}
