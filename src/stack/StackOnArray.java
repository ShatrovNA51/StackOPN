package stack;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class StackOnArray<T> implements Stack<T>{

    private T[] array;
    private int topItemIndex = -1;
    private int stackSize = 0;


    public StackOnArray() {
        // Первоначальный размер массива 10
        this.array = (T[]) new Object[10];
    }

    public StackOnArray(int size) {
        this.array = (T[]) new Object[size];
    }

    @Override
    public void push(T item) {
        array[++topItemIndex] = item;
        stackSize++;
        if (stackSize == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Стэк пустой");
        }
        stackSize--;
        T returnElement = array[topItemIndex--];
        array[topItemIndex + 1] = null;
        if (stackSize <= (int) (array.length * 0.25) && array.length > 10) {
            array = Arrays.copyOf(array, Math.max((int) (array.length * 0.25), 10));
        }
        return returnElement;
    }

    @Override
    public T peek() {
        return array[topItemIndex];
    }

    @Override
    public boolean isEmpty() {
        return stackSize == 0;
    }
}
