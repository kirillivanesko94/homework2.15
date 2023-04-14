package org.example;

import exception.ArrayFullException;
import exception.IndexValidException;
import exception.ItemValidException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final Integer[] arr;
    int size;

    public StringListImpl(int count) {
        arr = new Integer[count];
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        validateSize();
        arr[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = arr[index];
        if (index != size) {
            System.arraycopy(arr, index + 1, arr, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] arrCopy = toArray();
        sorted(arrCopy);
        return binarySearch(arrCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
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
        Arrays.fill(arr, null);
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arr, arr.length);
    }

    public void validateItem(Integer item) {
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

    private void sorted(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minId = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minId = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minId] = temp;
        }
    }

    private boolean binarySearch(Integer[] sortedArr, Integer item) {
        int min = 0;
        int max = sortedArr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(sortedArr[mid])) {
                return true;
            }
            if (item < sortedArr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
