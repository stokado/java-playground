package edu.nazarov.search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{-1, 0, 2, 3, 5, 6, 6, 9};
        System.out.println(search(array, 6));
        System.out.println(search(array, -3));
    }

    /**
     * Perform a binary search in {@code array} to find a {@code target} element in the array
     *
     * @param array array to perform a search in
     * @param target value to find
     * @return index of element if it's present in the array, -1 otherwise
     */
    public static int search(int[] array, int target) {
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                l = mid + 1;
            }
            if (array[mid] > target) {
                r = mid - 1;
            }
        }

        return -1;
    }
}
