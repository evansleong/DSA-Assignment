/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author evansleong
 */

import java.util.Iterator;

public class LinkedList<T> implements ListInterface<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void remove(T item) {
        if (head == null) {
            return;
        }

        if (head.data.equals(item)) {
            head = head.next;
        } else {
            Node<T> current = head;
            while (current.next != null && !current.next.data.equals(item)) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
            }
        }
        size--;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
