package Radix_sort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size_arr = input.nextInt();
        int[] arr = new int[size_arr];
        for (int i = 0; i < size_arr; i++) {
            arr[i] = input.nextInt();
        }
        radixSort(arr);
        input.close();
    }
    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        QueueLink[] queues = new QueueLink[10];
        for (int i = 0; i < 10; i++) {
            queues[i] = new QueueLink();
        }
        int exp = 1;
        int step = 1;
        while (max / exp > 0) {
            for (int number : arr) {
                int digit = (number / exp) % 10;
                queues[digit].enqueue(number);
            }
            int index = 0;
            for (int i = 0; i < 10; i++) {
                while (!queues[i].isEmpty()) {
                    arr[index++] = (int) queues[i].dequeue();
                }
            }
            System.out.print("Step " + step++ + ": ");
            printArray(arr);

            exp *= 10;
        }
        System.out.print("Result => ");
        printArray(arr);
    }
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}

