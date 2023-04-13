package org.example;

import exception.ArrayFullException;
import exception.IndexValidException;
import exception.ItemValidException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] arr;
    int size;

    public StringListImpl(int count) {
        arr = new String[count];
    }

    @Override
    public String add(String item) {
        validateItem(item);
        validateSize();
        arr[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        validateSize();
        validateIndex(index);
        if (size == index) {
            arr[size++] = item;
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
        arr[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = arr[index];
        if (index != size) {
            System.arraycopy(arr, index + 1, arr, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return arr[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(arr, otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
      Arrays.fill(arr,null);
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    public void validateItem(String item) {
        if (item == null) {
            throw new ItemValidException("Указан неверный элемент");
        }
    }

    public void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexValidException("Указан неверный индекс");
        }
    }

    public void validateSize() {
        if (size == arr.length) {
            throw new ArrayFullException("Массив заполен");
        }
    }
}
