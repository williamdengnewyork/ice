package com.exercise.ice;

/*
Problem: 
Write me a program that that accepts an array of integers, and prints out the pairs of these integers that sum to 7.  
For example, If the input is [1, 2, 3, 4, 5, 6], the output could be (1,6), (2, 5), (3, 4), or (6,1), (5, 2), (4,3).   
Different combinations of the same pair of numbers e.g, (1, 6) and (6, 1) are not distinct.
*/

public class FindPairsOfSum {
	static int sorttime=0;
	static int time=0;

	public static void main(String[] args) {

		// Assumption: Input array of unsorted integers
		int[] a = {6,1,5,3,4,2,9,11,13,8,7}; 		
		int sumValue = 13;

		searchPairsBySum(a, sumValue);
		System.out.println("Time Complexity - sorting: " + sorttime );
		System.out.println("Time Complexity - search : " + time );
		
	}

	public static void searchPairsBySum(int[] a, int target){
		if (a == null || a.length == 0){
			System.out.println("Warning: Array is null or has no element!");
			return;
		}
		
		System.out.println("Array Size: " + a.length );		
		quickSort(a, 0, a.length-1);
		
		int i=0;
		int l=a.length-1;		
		while (i<l){
			time +=1;
			if (a[i]+a[l]==target){
				System.out.println("(" + a[i] + "," + a[l] + ")");
				i += 1;
				l -= 1;
			}else if(a[i]+a[l] > target){
				l -= 1;
			}else{ // (a[i]+a[l] < target)
				i +=1;
			}			
		}
	}

	public static void quickSort(int[] arr, int low, int high) { //O(NlogN)
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
			sorttime +=1;
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
