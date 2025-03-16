/*

    Heap class - Containing most of the functionalities
    PriorityQueue class - implement func
    HeapSort class - implement func
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

enum HeapType {
    MIN_HEAP, MAX_HEAP
}

class Heap {
    private List<Integer> mHeap;
    private HeapType heapType;

    // constructor
    public Heap(HeapType type) {
        this.mHeap = new ArrayList<>();
        this.heapType = type;
    }

    public void insert(int value) {
        mHeap.add(value);
        heapifyUp(mHeap.size() - 1);
    }

    public int peek() {
        if(mHeap.isEmpty()) {
            //throw some expection
            return -1;
        }
        return mHeap.get(0);
    }

    public int extract() {
        if(mHeap.isEmpty()) {
            //throw some exception
            return -1;
        }
        int root = mHeap.get(0);
        int lastValue = mHeap.remove(mHeap.size()-1);
        if(!mHeap.isEmpty()) {
            mHeap.set(0, lastValue);
            heapifyDown(0);
        }
        return root;
    }

    public boolean isEmpty() {
        return mHeap.isEmpty();
    }

    private int getParent(int index) {
        // not doing boundary checking
        return (index-1)/2;
    }

    private int getLeftChild(int index) {
        return (2*index)+1;
    }

    private int getRightChild(int index) {
        return (2*index)+2;
    }

    private void swap(int i, int j) {
        int temp = mHeap.get(i);
        mHeap.set(i, mHeap.get(j));
        mHeap.set(j, temp);
    }

    private boolean compare(int a, int b) {
        return (heapType == HeapType.MIN_HEAP) ? a < b : a > b;
    }

    // sink and swim
    private void heapifyUp (int index) {// swim
        while(index > 0) {
            int parentIndex = getParent(index);
            if(compare(mHeap.get(index), mHeap.get(parentIndex))) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) { //sink
        while(true) {
            int leftChild = getLeftChild(index);
            int rightChild = getRightChild(index);
            int targetIndex = index;

            if(leftChild < mHeap.size() && compare(mHeap.get(leftChild), mHeap.get(targetIndex))) {
                targetIndex = leftChild;
            }
            if(rightChild < mHeap.size() && compare(mHeap.get(rightChild), mHeap.get(targetIndex))) {
                targetIndex = rightChild;
            }
            if(targetIndex != index) {
                swap(index, targetIndex);
                index = targetIndex;
            } else {
                break;
            }
        }
    }
}

class PriorityQueue {
    private Heap heap;

    public PriorityQueue(HeapType type) {
        this.heap = new Heap(type);
    }

    public void push(int value) {
        heap.insert(value);
    }

    public int pop() {
        return heap.extract();
    }

    public int peek() {
        return heap.peek();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

class HeapSort {
    public void sort(int[] array, HeapType type) { //in-place sorting to be performed
        Heap heap = new Heap(type);
        for(int i = 0 ; i < array.length ; ++i) {
            heap.insert(array[i]);
        }
        for(int i = array.length - 1 ; i >= 0 ; --i) {
            array[i] = heap.extract();
        }
    }
}

class HeapTest {
    public static void main(String[] args) {
        int[] array = {4,7,3,9,7,2,1};
        System.out.println("Array before sorting: " + Arrays.toString(array));
        HeapSort hs = new HeapSort();
        hs.sort(array, HeapType.MAX_HEAP);
        System.out.println("Array after sorting: " + Arrays.toString(array));

        int[] array1 = {4,7,3,9,7,2,1};
        System.out.println("Array before sorting: " + Arrays.toString(array1));
        hs.sort(array1, HeapType.MIN_HEAP);
        System.out.println("Array after sorting: " + Arrays.toString(array1));

        //Priority Queue example
        System.out.println("\n\nPriority Queue example");
        PriorityQueue pq = new PriorityQueue(HeapType.MAX_HEAP);
        pq.push(2);
        pq.push(8);
        pq.push(5);
        pq.push(1);
        pq.push(4);
        System.out.println("Extracted from queue: " + pq.pop());
        System.out.println("Extracted from queue: " + pq.pop());
        pq.push(9);
        pq.push(3);

        while(!pq.isEmpty()) {
            System.out.println("Extracted from queue: " + pq.pop());
        }
    }
}



