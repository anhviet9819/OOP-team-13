package SortVisualisation.Model.Sorting;

import SortVisualisation.Model.Pointer;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class RadixSort extends AbstractSort{
    private int arrLength;
    private boolean isFinished = false;
    private Pointer pointer;
    private StepThread stepThread;
    private static final Semaphore blockToStepsSemaphore = new Semaphore(0);
    public RadixSort(int[] sortArray) {
        super(sortArray);
        this.arrLength = sortArray.length;
        pointer = new Pointer();
    }

    @Override
    public Pointer getPointer() {
        return pointer;
    }

    @Override
    public int[] sortOneStep() {
        if (stepThread == null) {
            stepThread = new StepThread();
            stepThread.start();
        } else {
            blockToStepsSemaphore.release(); // release a semaphore permit so the SteppableThread stops blocking
        }

        return sortArray;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    private static int maxValue(int nums[], int n) {
        int maxValue = nums[0];
        for (int i = 1; i < n; i++)
            if (nums[i] > maxValue)
                maxValue = nums[i];
        return maxValue;
    }
    private class StepThread extends Thread {
        @Override
        public void run() {
            try {
                int m = maxValue(sortArray, arrLength);
                for (int exp = 1; m / exp > 0; exp *= 10)
                    recursiveRadixSort(sortArray, arrLength, exp);
                isFinished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void recursiveRadixSort(int[] inputArray, int n, int exp) throws InterruptedException {
            int output[] = new int[n]; // output nums
            int i;
            int count[] = new int[10];
            pointer.setCurrent(new int[]{0});
            blockToStepsSemaphore.acquire();
            Arrays.fill(count, 0);
            // Store count of occurrences in count[]
            for (i = 0; i < n; i++)
                count[(inputArray[i] / exp) % 10]++;
            pointer.updateIndex(0, i);
            blockToStepsSemaphore.acquire();
            // Change count[i] so that count[i] now contains
            // actual position of this digit in output[]
            for (i = 1; i < 10; i++)
                count[i] += count[i - 1];
            pointer.updateIndex(0, i);
            blockToStepsSemaphore.acquire();
            // Build the output nums
            for (i = n - 1; i >= 0; i--) {
                output[count[(inputArray[i] / exp) % 10] - 1] = inputArray[i];
                count[(inputArray[i] / exp) % 10]--;
                pointer.updateIndex(0, i);
                blockToStepsSemaphore.acquire();
            }
            // Copy the output nums to nums[], so that nums[] now
            // contains sorted numbers according to current digit
            for (i = 0; i < n; i++)
                inputArray[i] = output[i];
            pointer.updateIndex(0, i);
            blockToStepsSemaphore.acquire();
        }
    }
}
