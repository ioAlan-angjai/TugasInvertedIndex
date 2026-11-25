package test;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Turunan LinkedList yang menjaga elemen tetap terurut menaik dan unik.
 *
 * @author RACHEL RAMBU
 */
public class LinkedListOrderedUnique<E> extends LinkedList<E> {

    /** Menyisipkan elemen secara terurut; dilewati jika sudah ada (compareTo == 0). */
    public boolean addSort(E e) {
        ListIterator<E> iterator = this.listIterator();
        while (iterator.hasNext()) {
            E temp = iterator.next();
            if (((Comparable) temp).compareTo(e) > 0) {
                iterator.previous(); // Mundur satu langkah untuk sisip sebelum temp
                iterator.add(e);
                return true;
            } else if (((Comparable) temp).compareTo(e) == 0) {
                return true; // Sudah ada, pertahankan unik
            }
        }
        iterator.add(e);
        return true;
    }

    /** Mencari elemen dengan compareTo == 0, mengembalikan null jika tidak ketemu. */
    public E get(E e) {
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
            temp = temp + iterator.next() + " ";
        }
        return temp;
    }
}