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
public interface ListInterface<T> {

    void add(T element);

    T get(int index);

    boolean contains(T element);

    boolean isEmpty();

    int size();

    void remove(T item);

    Iterator<T> iterator();
}
