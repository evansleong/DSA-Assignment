/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author evansleong
 */
public class HashMap<K, V> implements HashMapInterface<K, V>{
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private LinkedList<Node<K, V>>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = (LinkedList<Node<K, V>>[]) new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Node<K, V> node : table[index]) {
            if (node.key.equals(key)) {
                node.value = value; // Replace value if key exists
                return;
            }
        }

        table[index].add(new Node<>(key, value, null));
        size++;

        if ((float) size / table.length >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Node<K, V> node : table[index]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null; // Key not found
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            Iterator<Node<K, V>> iterator = table[index].iterator();
            while (iterator.hasNext()) {
                Node<K, V> node = iterator.next();
                if (node.key.equals(key)) {
                    iterator.remove();
                    size--;
                    return;
                }
            }
        }
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int bucketIndex = 0;
            private Iterator<Node<K, V>> bucketIterator = (table[bucketIndex] != null) ? table[bucketIndex].iterator() : null;

            @Override
            public boolean hasNext() {
                while (bucketIndex < table.length) {
                    if (bucketIterator != null && bucketIterator.hasNext()) {
                        return true;
                    }
                    bucketIndex++;
                    bucketIterator = (bucketIndex < table.length && table[bucketIndex] != null) ? table[bucketIndex].iterator() : null;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bucketIterator.next().key;
            }
        };
    }

    private int getIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Node<K, V>>[] oldTable = table;
        table = (LinkedList<Node<K, V>>[]) new LinkedList[oldTable.length * 2];
        size = 0;

        for (LinkedList<Node<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Node<K, V> node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }
}
