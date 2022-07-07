package training;

import java.util.Optional;

public class MaxHeap {
    Integer[] heapStorage;
    int size = 0;

    MaxHeap(int capacity) {
        heapStorage = new Integer[capacity + 1];
    }

    public int size() {
        return size;
    }

    public void insert(int value) {
        if (size + 1 == heapStorage.length) growHeapStorage();
        heapStorage[++size] = value;
        bubbleUp(size);
    }


    private void growHeapStorage() {
        var newHeapStorage = new Integer[heapStorage.length * 2];
        System.arraycopy(heapStorage, 0, newHeapStorage, 0, heapStorage.length);

        heapStorage = newHeapStorage;
    }

    private void bubbleUp(int index) {
        var parentIndex = index / 2;
        if (parentIndex == 0) return;
        if (heapStorage[parentIndex] < heapStorage[index]) {
            swap(parentIndex, index);
            bubbleUp(parentIndex);
        }
    }

    private void swap(int parentIndex, int index) {
        var temp = heapStorage[parentIndex];
        heapStorage[parentIndex] = heapStorage[index];
        heapStorage[index] = temp;
    }

    public Optional<Integer> peek() {
        if (size == 0) return Optional.empty();
        return Optional.of(heapStorage[1]);
    }

    public Optional<Integer> pop() {
        if (size == 0) return Optional.empty();

        var value = heapStorage[1];

        removeTop();
        sinkDown(1);

        return Optional.of(value);
    }


    private void removeTop() {
        heapStorage[1] = heapStorage[size];
        heapStorage[size] = null;
        size--;
    }

    private void sinkDown(int index) {
        var childIndex = getChildIndex(index);
        if (heapStorage[childIndex] == null) return;

        if (heapStorage[childIndex] > heapStorage[index]) {
            swap(index, childIndex);
            sinkDown(childIndex);
        }
    }

    private int getChildIndex(int index) {
        var leftChildIndex = index * 2;
        var rightChildIndex = index * 2 + 1;

        if (leftChildIndex > size) return index;
        if (rightChildIndex > size) return leftChildIndex;

        return heapStorage[leftChildIndex] > heapStorage[rightChildIndex] ? leftChildIndex : rightChildIndex;
    }
}