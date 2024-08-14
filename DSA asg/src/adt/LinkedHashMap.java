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
 * @author evansleong
 */
public class LinkedHashMap<K, V> implements LinkedHashMapInterface<K, V> {

    // Threshold to trigger a resize operation when the load factor exceeds this value
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;

    // Array of linked list nodes representing the hash table
    private Node<K, V>[] table;

    // Head and tail for the doubly linked list maintaining insertion order
    private Node<K, V> head;
    private Node<K, V> tail;

    // Current number of key-value pairs in the map
    private int size;

    // Current capacity of the hash table (size of the array)
    private int capacity;

    // Inner class representing a node in the hash table and the linked list
    private static class Node<K, V> {

        final K key;             // Key for the node
        V value;                 // Value associated with the key
        Node<K, V> next;         // Pointer to the next node in the hash table bucket (for collision handling)
        Node<K, V> prev;         // Pointer to the previous node in the hash table bucket (not used in this implementation)
        Node<K, V> nextInOrder;  // Pointer to the next node in insertion order
        Node<K, V> prevInOrder;  // Pointer to the previous node in insertion order

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor to initialize the hash map with a minimal capacity
    public LinkedHashMap() {
        this.capacity = 1; // Start with a minimal capacity
        this.table = new Node[capacity]; // Initialize the hash table with the given capacity
        this.size = 0; // Initially, the map is empty
    }

    // Hash function to calculate the index for a given key
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    // Method to insert or update a key-value pair in the map
    @Override
    public void put(K key, V value) {
        checkKeyIsNull(key);
        checkValueIsNull(value);
        // Resize the table if the load factor exceeds the threshold
        if ((float) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize(); // Resize the table
        }

        int index = hash(key); // Calculate the index using the hash function
        Node<K, V> newNode = new Node<>(key, value); // Create a new node for the key-value pair

        // If the bucket at the calculated index is empty, add the node directly
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // Traverse the linked list in the bucket to find the key or the end of the list
            Node<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update value if key already exists
                    return;
                }
                if (current.next == null) {
                    current.next = newNode; // Add the new node at the end of the list
                    break;
                }
                current = current.next;
            }
        }

        // Maintain the insertion order using a doubly linked list
        if (tail == null) {
            head = newNode; // If the list is empty, newNode becomes both head and tail
            tail = newNode;
        } else {
            tail.nextInOrder = newNode; // Link the new node at the end of the order list
            newNode.prevInOrder = tail;
            tail = newNode; // Update tail to the new node
        }

        size++; // Increase the size of the map
    }

    // Method to resize the hash table when the load factor threshold is reached
    private void resize() {
        capacity *= 2; // Double the capacity of the table
        Node<K, V>[] newTable = new Node[capacity]; // Create a new table with the updated capacity

        Node<K, V> current = head; // Start from the head of the insertion order list
        head = null; // Reset head and tail
        tail = null;

        // Rehash all nodes and insert them into the new table
        while (current != null) {
            Node<K, V> nextNode = current.nextInOrder; // Store the next node in order
            current.next = null; // Reset the pointers in the current node
            current.prev = null;
            current.nextInOrder = null;
            current.prevInOrder = null;

            int newIndex = hash(current.key); // Recalculate the index for the node
            if (newTable[newIndex] == null) {
                newTable[newIndex] = current; // Insert node if bucket is empty
            } else {
                Node<K, V> temp = newTable[newIndex];
                while (temp.next != null) {
                    temp = temp.next; // Traverse to the end of the list in the bucket
                }
                temp.next = current; // Add the node at the end
            }

            // Rebuild the insertion order list
            if (tail == null) {
                head = current;
                tail = current;
            } else {
                tail.nextInOrder = current;
                current.prevInOrder = tail;
                tail = current;
            }

            current = nextNode; // Move to the next node in the old order list
        }

        table = newTable; // Update the table reference to the new table
    }

    // Method to retrieve the value associated with a given key
    @Override
    public V get(K key) {
        checkKeyIsNull(key);
        int index = hash(key); // Calculate the index using the hash function
        Node<K, V> current = table[index]; // Start from the bucket at the calculated index
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Return the value if the key is found
            }
            current = current.next; // Move to the next node in the list
        }
        return null; // Return null if the key is not found
    }

    // Method to replace the value associated with a key
    @Override
    public void replace(K key, V newValue) {
        checkKeyIsNull(key);
        checkValueIsNull(newValue);
        int index = hash(key); // Calculate the index using the hash function
        Node<K, V> current = table[index]; // Start from the bucket at the calculated index
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = newValue; // Update the value if the key is found
                return;
            }
            current = current.next; // Move to the next node in the list
        }
        throw new NoSuchElementException("Key not found: " + key); // Throw an exception if key is not found
    }

    // Method to check if the map contains a specific key
    @Override
    public boolean containsKey(K key) {
        return get(key) != null; // Return true if the key exists, otherwise false
    }

    // Method to check if the map is empty
    @Override
    public boolean isEmpty() {
        return size == 0; // Return true if the size is 0, otherwise false
    }

    // Method to get the number of key-value pairs in the map
    @Override
    public int size() {
        return size; // Return the size of the map
    }

    // Method to remove a key-value pair from the map
    @Override
    public void remove(K key) {
        checkKeyIsNull(key);
        int index = hash(key); // Calculate the index using the hash function
        Node<K, V> current = table[index]; // Start from the bucket at the calculated index
        Node<K, V> prev = null; // Keep track of the previous node in the list

        while (current != null) {
            if (current.key.equals(key)) {
                // Remove the node from the hash table bucket
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    table[index] = current.next;
                }

                // Update the linked list maintaining insertion order
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

                size--; // Decrease the size of the map
                return;
            }
            prev = current; // Update the previous node
            current = current.next; // Move to the next node in the list
        }
    }

    // Method to provide an iterator over the keys in the map
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node<K, V> current = head; // Start from the head of the insertion order list

            @Override
            public boolean hasNext() {
                return current != null; // Return true if there are more elements
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(); // Throw exception if no more elements
                }
                K key = current.key; // Get the key from the current node
                current = current.nextInOrder; // Move to the next node in order
                return key; // Return the key
            }
        };
    }

    @Override
    public <T> void bubbleSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    // Swap array[j] and array[j + 1]
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
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
