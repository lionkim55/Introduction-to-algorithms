import java.util.Arrays;
import java.util.Random;

public class HWT5L1 {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1_000_000);
        }
        return arr;
    }

    private static void measureTime(String name, Runnable action) {
        long start = System.currentTimeMillis();
        action.run();
        long end = System.currentTimeMillis();
        System.out.printf("%s: %d ms%n", name, (end - start));
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10_000, 100_000};

        for (int size : sizes) {
            System.out.println("\n===== Размер массива: " + size + " =====");
            int[] original = generateRandomArray(size);

            int[] arr1 = Arrays.copyOf(original, original.length);
            measureTime("Bubble Sort", () -> bubbleSort(arr1));

            int[] arr2 = Arrays.copyOf(original, original.length);
            measureTime("Quick Sort (custom)", () -> quickSort(arr2, 0, arr2.length - 1));
            
            int[] arr3 = Arrays.copyOf(original, original.length);
            measureTime("Arrays.sort()", () -> Arrays.sort(arr3));
        }
    }
}
