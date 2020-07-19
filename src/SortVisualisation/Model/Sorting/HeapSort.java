package SortVisualisation.Model.Sorting;

import SortVisualisation.Model.Pointer;

import java.util.concurrent.Semaphore;

public class HeapSort extends AbstractSort{
    private int arrLength;
    private boolean isFinished = false;
    private Pointer pointer;
    private StepThread stepThread;
    private static final Semaphore blockToStepsSemaphore = new Semaphore(0);
    public HeapSort(int[] sortArray) {
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
    private void swapValues(int index1, int index2) {
        int temp = sortArray[index1];
        sortArray[index1] = sortArray[index2];
        sortArray[index2] = temp;
    }
    private class StepThread extends Thread {
        @Override
        public void run() {
            try {
                for(int i = arrLength/2-1;i>=0;i--)
                recursiveHeapSort(sortArray, i, arrLength);
                for(int i=arrLength-1;i>0;i--){
                    swapValues(0,i);
                    recursiveHeapSort(sortArray,0,i);
                }
                // if we get here, we can assume the quickSort finished
              isFinished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void recursiveHeapSort(int[] inputArray, int i, int m) throws InterruptedException {
            if (inputArray == null || inputArray.length == 0 )
                return;
            blockToStepsSemaphore.acquire();
            int largest = i;
            int l=2*i+1;
            int r=2*i+2;
            pointer.setCurrent(new int[]{l, r,largest});
            blockToStepsSemaphore.acquire();
            if (l < m && inputArray[l] > inputArray[largest]) {
                largest = l;
                pointer.updateIndex(2, largest);
                blockToStepsSemaphore.acquire();
            }
            // If right child is larger than largest so far
            if (r < m && inputArray[r] > inputArray[largest]) {
                largest = r;
                pointer.updateIndex(2, largest);
                blockToStepsSemaphore.acquire();
            }
            // If largest is not root
            if (largest != i) {
                int swap = inputArray[i];
                inputArray[i] = inputArray[largest];
                inputArray[largest] = swap;
                // Recursively heapify the affected sub-tree
                blockToStepsSemaphore.acquire();
                recursiveHeapSort(inputArray, largest, m);
            }
        }
    }
}
