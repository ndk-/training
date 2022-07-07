package training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MaxHeap Test")
class MaxHeapTest {

    @Test
    @DisplayName("size() should return size of the heap")
    void size() {
        var empty = new MaxHeap(1);
        var one = new MaxHeap(1);
        var many = new MaxHeap(5);

        one.insert(1);
        many.insert(1);
        many.insert(2);
        many.insert(3);

        assertThat(empty.size()).isEqualTo(0);
        assertThat(one.size()).isEqualTo(1);
        assertThat(many.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("peek() should return node with the maximum value")
    void peek() {
        var empty = new MaxHeap(1);
        var heap = new MaxHeap(9);

        var numbers = List.of(1, 3, 50, 6, 7, 10, 8, 4, 2);

        numbers.forEach(heap::insert);

        assertThat(heap.peek()).contains(50);
        assertThat(empty.peek()).isEmpty();
    }

    @Test
    @DisplayName("pop() should return node with maximum value and remove it from the heap")
    void pop() {
        var heap = new MaxHeap(9);
        var numbers = List.of(1, 3, 5, 2, 40, 30, 10, 4, 50);

        numbers.forEach(heap::insert);

        var sortedNumbersDescending = numbers.stream().sorted(Comparator.reverseOrder());

        sortedNumbersDescending.forEach(number -> assertThat(heap.pop()).contains(number));

        assertThat(heap.pop()).isEmpty();
    }

    @Test
    @DisplayName("insert() should dynamically grow a heap when it is at the max capacity")
    void insertDynamicallyGrows() {
        var heap = new MaxHeap(1);

        var numbers = List.of(1, 3, 5, 2, 40, 30, 10, 4, 50);

        numbers.forEach(heap::insert);

        assertThat(heap.size()).isEqualTo(numbers.size());
        assertThat(heap.peek()).contains(50);
    }
}