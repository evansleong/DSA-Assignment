/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public interface LinkedHashMapInterface<K, V> {

    /**
     * Inserts a key-value pair into the map. If the key already exists, the
     * value is updated with the new value.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may or may not contain the specified key.<br>
     * post-condition: If the key does not exist, it is added to the map with
     * the associated value. If the key exists, its value is updated with the
     * new value.
     *
     * @param key the key to be added or updated
     * @param value the value associated with the key
     */
    void put(K key, V value);

    /**
     * Retrieves the value associated with the specified key.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may or may not contain the specified key.<br>
     * post-condition: The map remains unchanged. Returns the value associated
     * with the key if it exists, otherwise returns null.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the map
     * contains no mapping for the key
     */
    V get(K key);

    /**
     * Replaces the value associated with the specified key with the given
     * value.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map contains the specified key.<br>
     * post-condition: The value associated with the key is updated to the new
     * value. Throws a NoSuchElementException if the key does not exist.
     *
     * @param key the key whose associated value is to be replaced
     * @param newValue the new value to be associated with the specified key
     * @throws NoSuchElementException if the key does not exist in the map
     */
    void replace(K key, V newValue);

    /**
     * Removes the key-value pair for the specified key from the map.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may or may not contain the specified key.<br>
     * post-condition: If the key exists, the key-value pair is removed from the
     * map. If the key does not exist, the map remains unchanged.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    void remove(K key);

    /**
     * Checks if the map contains a mapping for the specified key.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may or may not contain the specified key.<br>
     * post-condition: The map remains unchanged. Returns true if the key is
     * present, otherwise returns false.
     *
     * @param key the key whose presence in the map is to be tested
     * @return true if the map contains a mapping for the specified key, false
     * otherwise
     */
    boolean containsKey(K key);

    /**
     * Returns the number of key-value mappings in the map.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may contain zero or more key-value pairs.<br>
     * post-condition: The map remains unchanged. Returns the total number of
     * key-value pairs in the map.
     *
     * @return the number of key-value mappings in the map
     */
    int size();

    /**
     * Returns true if the map contains no key-value mappings.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may contain zero or more key-value pairs.<br>
     * post-condition: The map remains unchanged. Returns true if the map is
     * empty, otherwise returns false.
     *
     * @return true if the map contains no key-value mappings, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns an iterator over the keys in the map.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The map may contain zero or more key-value pairs.<br>
     * post-condition: The map remains unchanged. Returns an iterator that
     * provides sequential access to the keys in the map.
     *
     * @return an iterator over the keys in the map
     */
    Iterator<K> iterator();

    /**
     * Sorts an array of elements using bubble sort algorithm.
     *
     * <p>
     * ADT Spec: <br>
     * pre-condition: The array and comparator must be provided.<br>
     * post-condition: The array is sorted according to the comparator.
     *
     * @param array the array to be sorted
     * @param comparator the comparator used to compare array elements
     * @param <T> the type of elements in the array
     */
    <T> void bubbleSort(T[] array, Comparator<T> comparator);
}
