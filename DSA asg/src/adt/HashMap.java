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
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    public HashMap() {
        head = null;
        tail = null;
        size = 0;
    }

    //put method is o(1)
    @Override
    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void replace(K key, V newValue) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = newValue; // Replace the value with the new value
                return;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Key not found: " + key);
    }

    @Override
    public V get(K key) {
        Node<K, V> current = head;
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
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
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
                current = current.next;
                return key;
            }
        };
    }

}