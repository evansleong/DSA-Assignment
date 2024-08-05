/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public interface HashMapInterface<K, V> {

    void put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    boolean isEmpty();

    int size();

    void remove(K key);

    Iterator<K> iterator();
}
