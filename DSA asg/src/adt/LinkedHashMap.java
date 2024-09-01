/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Leong Gao Chong
 */
public class LinkedHashMap<K, V> implements LinkedHashMapInterface<K, V> {

    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;

    private Node<K, V>[] table;

    private Node<K, V> head;
    private Node<K, V> tail;

    private int size;

    private int capacity;

    private static class Node<K, V> {

        final K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;
        Node<K, V> nextInOrder;
        Node<K, V> prevInOrder;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LinkedHashMap() {
        this.capacity = 1;
        this.table = new Node[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            return Math.abs(key.hashCode() % capacity);
        }
    }

    @Override
    public void put(K key, V value) {
        checkKeyIsNull(key);
        checkValueIsNull(value);
        if ((float) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    current.next = newNode;
                    break;
                }
                current = current.next;
            }
        }

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.nextInOrder = newNode;
            newNode.prevInOrder = tail;
            tail = newNode;
        }

        size++;
    }

    private void resize() {
        capacity *= 2;
        Node<K, V>[] newTable = new Node[capacity];

        Node<K, V> current = head;
        head = null;
        tail = null;

        while (current != null) {
            Node<K, V> nextNode = current.nextInOrder;
            current.next = null;
            current.prev = null;
            current.nextInOrder = null;
            current.prevInOrder = null;

            int newIndex = hash(current.key);
            if (newTable[newIndex] == null) {
                newTable[newIndex] = current;
            } else {
                Node<K, V> temp = newTable[newIndex];
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = current;
            }

            if (tail == null) {
                head = current;
                tail = current;
            } else {
                tail.nextInOrder = current;
                current.prevInOrder = tail;
                tail = current;
            }

            current = nextNode;
        }

        table = newTable;
    }

    @Override
    public V get(K key) {
        checkKeyIsNull(key);
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void replace(K key, V newValue) {
        checkKeyIsNull(key);
        checkValueIsNull(newValue);
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = newValue;
                return;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Key not found: " + key);
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(K key) {
        checkKeyIsNull(key);
        int index = hash(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    table[index] = current.next;
                }

                if (current.prevInOrder != null) {
                    current.prevInOrder.nextInOrder = current.nextInOrder;
                } else {
                    head = current.nextInOrder;
                }

                if (current.nextInOrder != null) {
                    current.nextInOrder.prevInOrder = current.prevInOrder;
                } else {
                    tail = current.prevInOrder;
                }

                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }

        head = null;
        tail = null;

        size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node<K, V> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = current.key;
                current = current.nextInOrder;
                return key;
            }
        };
    }

    @Override
    public <T> void mergeSort(T[] array, Comparator<T> comparator) {
        if (comparator == null || array == null) {
            throw new IllegalArgumentException("Comparator or array cannot be null");
        }
        if (array.length == 0) {
            return;
        }
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Object[array.length];
        mergeSort(array, tempArray, 0, array.length - 1, comparator);
    }

    private <T> void mergeSort(T[] array, T[] tempArray, int left, int right, Comparator<T> comparator) {

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, tempArray, left, mid, comparator);
            mergeSort(array, tempArray, mid + 1, right, comparator);
            merge(array, tempArray, left, mid, right, comparator);
        }
    }

    private <T> void merge(T[] array, T[] tempArray, int left, int mid, int right, Comparator<T> comparator) {

        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (comparator.compare(tempArray[i], tempArray[j]) <= 0) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            array[k] = tempArray[i];
            i++;
            k++;
        }

        while (j <= right) {
            array[k] = tempArray[j];
            j++;
            k++;
        }
    }

    private void checkKeyIsNull(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    }

    private void checkValueIsNull(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

}
