/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Leong Gao Chong
 */
public interface ListInterface<T> {
    void add(T element);

    void remove(T element);

    boolean contains(T element);

    boolean isEmpty();

    int size();

    Iterator<T> iterator();
}

