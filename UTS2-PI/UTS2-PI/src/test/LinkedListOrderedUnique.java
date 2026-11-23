/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author RACHEL RAMBU
 */
public class LinkedListOrderedUnique<E> extends LinkedList<E> {

    public boolean addSort(E e) {//menambahkan term atau doc ke linkedlist dengan terurut dan tanpa duplikasi 
        ListIterator<E> iterator = this.listIterator();
        while (iterator.hasNext()) {
            E temp = iterator.next();
            if (((Comparable) temp).compareTo(e) > 0) {
                iterator.previous();
                iterator.add(e);
                return true;
            } else if (((Comparable) temp).compareTo(e) == 0) {
//                ((Increment) temp).inc(); ini untuk tf 
                return true;
            }
        }
        iterator.add(e);
        return true;
    }

    public E get(E e) {//method searching 
        ListIterator<E> iterator = this.listIterator();
        while (iterator.hasNext()) {
            E temp = iterator.next();
            if (((Comparable) temp).compareTo(e) == 0) {
                return temp;
            }
        }
        return null;
    }

    public String toString() {
        String temp = "";
        ListIterator<E> iterator = this.listIterator();
        while (iterator.hasNext()) {
            temp = temp  + iterator.next() + " ";
        }
        return temp;
    }
}
//mengelola linkedlist agar terurut dan tidak memiliki data duplikat.