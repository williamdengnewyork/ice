package com.exercise.ice;
/*
Problem: 
Write me a program that that accepts an array of integers, and prints out the pairs of these integers that sum to 7.  
For example, If the input is [1, 2, 3, 4, 5, 6], the output could be (1,6), (2, 5), (3, 4), or (6,1), (5, 2), (4,3).   
Different combinations of the same pair of numbers e.g, (1, 6) and (6, 1) are not distinct.
*/

import java.util.Arrays;

public class FindPairsOfSum {
	private static int time=0;
    private static final int MAX = 100000;

    public static void main(String[] args) {

		int sumValue = 7;
		int[] a = {6,1,5,-3,4,2,9,11,13,8,0,7,16,10,3};  // unsorted array 		
		
		System.out.println("Array Size     : " + a.length );
		System.out.println("Sum Value = " + sumValue);

		System.out.println("Solution 1 - work for unsorted array"); 
		time=0;
		searchPairsBySum(a, sumValue); 
		System.out.println("Time Complexity: " + time );
				
		
		System.out.println("Solution 2 - pre-sorted array beforehand"); 
		time=0;
		Arrays.sort(a);
		searchPairsBySumInSortedArray(a, sumValue);  
		System.out.println("Time Complexity: " + time );
	}

	// O(n)
    static void searchPairsBySum(int[] a, int sum){
		if (a == null || a.length == 0){
			System.out.println("Warning: Array is null or has no element!");
			return;
		}
		if (a.length < 2){
			System.out.println("Warning: Array has less than 2 elements!");
			return;
		}		
        boolean[] binmap = new boolean[MAX]; 
		boolean[] binmapNegative=new boolean[MAX];
		for (int i = 0; i < a.length; i++) {
			int temp = sum - a[i];
			if (temp < 0) {
				if (binmapNegative[Math.abs(temp)]) {
					System.out.println("(" + temp + "," + a[i] + ")");
				}
			} else {
				if (binmap[temp] ) {
					System.out.println("(" + temp + "," + a[i] + ")");
				}
			}

			if (a[i] < 0) {
				binmapNegative[Math.abs(a[i])] = true;
			} else {
				binmap[a[i]] = true;
			}
			time += 1;
		}
    }
    
    //If a[] is already sorted, this implementation is faster than O(n) on average
	public static void searchPairsBySumInSortedArray(int[] a, int target){
		if (a == null || a.length == 0){
			System.out.println("Warning: Array is null or has no element!");
			return;
		}
		if (a.length < 2){
			System.out.println("Warning: Array has less than 2 elements!");
			return;
		}		
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
	

}
