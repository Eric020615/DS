import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Histogram {
    public static class MergeSort {

        /** The no-argument constructor with an empty body */
        public MergeSort() {
        }

        /** The method for sorting the data */
        public void mergeSort(int[] list) {
            if (list.length > 1) {
                // Merge sort the first half
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
                mergeSort(firstHalf);

                // Merge sort the second half
                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
                mergeSort(secondHalf);

                // Merge firstHalf with secondHalf into list
                merge(firstHalf, secondHalf, list);
            }
        }

        /** The method to merge two sorted lists */
        public void merge(int[] list1, int[] list2, int[] temp) {
            int current1 = 0; // Current index in list1
            int current2 = 0; // Current index in list2
            int current3 = 0; // Current index in temp

            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1] < list2[current2])
                    temp[current3++] = list1[current1++];
                else
                    temp[current3++] = list2[current2++];
            }

            while (current1 < list1.length)
                temp[current3++] = list1[current1++];

            while (current2 < list2.length)
                temp[current3++] = list2[current2++];
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create an object ms to invoke the MergeSort class
        Histogram.MergeSort ms=new Histogram.MergeSort();
        //Number of test case for the testing
        int t = scanner.nextInt();
        // Start for to loop through every test case
        for (int i = 0; i < t; i++) {
            // Store the first line of int type in a variable N
            int N = scanner.nextInt();
            // Store the first line of int type in a variable M
            int M = scanner.nextInt();
            // N represents the number of data points
            // M represents the number of bins
            // Create an array data of int type to store the data points
            int[] data = new int[N];
            // Start for to store every data point in the array data
            for (int j = 0; j < N; j++) {
                data[j] = scanner.nextInt();
            }
            //Invoke the mergeSort method in the MergeSort class to sort and arrange the data points in ascending order
            ms.mergeSort(data);
            // The formula to calculate the width of an interval
            // Store the value of calculation in a variable width of int type
            int width = (data[N - 1] - data[0]) / M;
            // Create an array interval of int type to store the cutoffs for the histogram
            int[] interval = new int[M + 1];
            interval[0] = data[0];
            // Start for to calculate and store the cutoffs of every interval
            for (int q = 1; q < interval.length; q++) {
                interval[q] = interval[q - 1] + width;
            }
            // Start for to calculate and store the cutoffs of every interval
            for (int w = 0; w < interval.length; w++) {
                System.out.print(interval[w] + " ");
            }
            System.out.println();
            int[] count = new int[M];
            // Start for to calculate the counts for each interval
            for (int l = 0; l < M; l++) {
                // Start for to loop through every data point in a particular interval
                for (int j = 0; j < data.length; j++) {
                    if (l == M - 1) {
                        if (data[j] >= interval[l] && data[j] <= interval[l + 1]) {
                            count[l]++;
                        }
                    } else {
                        if (data[j] >= interval[l] && data[j] < interval[l + 1]) {
                            count[l]++;
                        }
                    }
                }
            }
            // Start for to print out the counts for each interval
            for (int p = 0; p < count.length; p++) {
                System.out.print(count[p] + " ");
            }
            System.out.println();
        }
    }

}
