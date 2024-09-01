/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Leong Gao Chong
 */
public interface LinkedHashMapInterface<K, V> {

    void put(K key, V value);

    V get(K key);

    void replace(K key, V newValue);

    void remove(K key);

    void clear();

    boolean containsKey(K key);

    int size();

    boolean isEmpty();

    Iterator<K> iterator();

    <T> void mergeSort(T[] array, Comparator<T> comparator);
}
