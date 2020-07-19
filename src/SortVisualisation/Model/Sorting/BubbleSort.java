package SortVisualisation.Model.Sorting;

import SortVisualisation.Model.Pointer;

import java.util.concurrent.Semaphore;

public class BubbleSort extends AbstractSort {
    //private boolean counting = false; // are we counting no-swaps from the start index?
    //private int noSwaps = 0;
    private boolean isFinished = false;
    private int arrLength;
    private Pointer pointer;
    private StepThread stepThread;
    private static final Semaphore blockToStepsSemaphore = new Semaphore(0);
    public BubbleSort(int[] unsortedArray) {
        super(unsortedArray);
        this.arrLength = unsortedArray.length;
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
                for(int i = 0;i<arrLength-1;i++)
                    for(int j = 0;j<arrLength-i-1;j++)
                    {
                        recursiveBubbleSort(sortArray,i,j);

                    }
                // if we get here, we can assume the quickSort finished
                isFinished = true;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void recursiveBubbleSort(int[] inputArray, int i, int j) throws InterruptedException {
            if (inputArray == null || inputArray.length == 0 )
                return;
            pointer.setCurrent(new int[]{j,j+1});           //
            if(inputArray[j]>inputArray[j+1])
            {
                swapValues(j,j+1);
//                pointer.updateIndex(0, leftPointer);
                blockToStepsSemaphore.acquire();

            }
            blockToStepsSemaphore.acquire();
        }
    }
}
