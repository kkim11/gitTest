/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevinkim
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Inversions {

    // merge two sorted subarrays in a[], also counting the inversions
    // that had existed before merging them.
    private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversions = 0;
        // copy a[] to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // merge from aux[] back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) { //second part of swap
                a[k] = aux[j++];
            } else if (j > hi) { //second part of swap
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) { //first part of swap
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            } else { //if already sorted then increment pointer
                a[k] = aux[i++];
            }
        }
        return inversions;
    }

    // Sort the subarray a[lo..hi], and also return the number
    // of inversions that were in the array before sorting.
    private static long sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        long inversions = 0;
        inversions += sort(a, aux, lo, mid);
        inversions += sort(a, aux, mid + 1, hi);
        inversions += merge(a, aux, lo, mid, hi);
        return inversions;
    }

    // Sort the array a, and also return the number of inversions
    // that were in the array before sorting.
    public static long sort(int[] a) {
        int n = a.length;
        return sort(a, new int[n], 0, n - 1);
    }

    public static void main(String[] args) {
        // First read integers into an ArrayList (resizable)
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        // Copy them to an int array a
        int n = list.size();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = list.get(i);
        }
        // Now sort a, report number of inversions, and time required.
        System.out.printf("Read %d integers. Sorting ... %n", n);
        long beg = System.currentTimeMillis();
        long inversions = sort(a);
        double secs = (System.currentTimeMillis() - beg) / 1000.0; // elapsed time
        System.out.printf("Found %d inversions in %.3f seconds%n",
                inversions, secs);
    }

}
