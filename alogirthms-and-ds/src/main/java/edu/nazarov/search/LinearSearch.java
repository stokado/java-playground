package edu.nazarov.search;

public class LinearSearch {
    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 3, 6, 9, 0, -1, 5};
        System.out.println(search(array, 6));
        System.out.println(search(array, -3));
    }

    /**
     * Perform a linear search in {@code array} to find a {@code target} element in the array
     *
     * @param array array to perform a search in
     * @param target value to find
     * @return index of element if it's present in the array, -1 otherwise
     */
    public static int search(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
