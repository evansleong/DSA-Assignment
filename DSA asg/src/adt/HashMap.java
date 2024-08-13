/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author evansleong
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public HashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update value if key exists
                return;
            }
            current = current.next;
        }

        // Prepend the new node to the list (O(1) operation)
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;

        if (size >= LOAD_FACTOR * buckets.length) {
            resize();
        }
    }

    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        buckets = new Node[oldBuckets.length * 2];
        size = 0;

        for (Node<K, V> node : oldBuckets) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
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
        int index = hash(key);
        Node<K, V> current = buckets[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int currentBucket = 0;
            private Node<K, V> currentNode = buckets[currentBucket];

            @Override
            public boolean hasNext() {
                while (currentBucket < buckets.length) {
                    if (currentNode != null) {
                        return true;
                    }
                    currentBucket++;
                    if (currentBucket < buckets.length) {
                        currentNode = buckets[currentBucket];
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = currentNode.key;
                currentNode = currentNode.next;
                return key;
            }
        };
    }
}