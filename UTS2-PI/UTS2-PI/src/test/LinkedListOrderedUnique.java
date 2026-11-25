/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Turunan {@link LinkedList} yang menjaga elemen-elemennya selalu terurut menaik
 * dan unik (tanpa duplikasi). Digunakan untuk menyimpan dictionary (Term)
 * maupun posting list (Document).
 *
 * @param <E> tipe elemen yang mengimplementasikan {@link Comparable}
 * @author RACHEL RAMBU
 */
public class LinkedListOrderedUnique<E> extends LinkedList<E> {

    /**
     * Menyisipkan elemen {@code e} pada posisi terurut jika belum ada di list.
     * Jika elemen sudah ada ({@code compareTo == 0}), penyisipan dilewati.
     *
     * @param e elemen yang akan ditambahkan
     * @return selalu {@code true}
     */
    public boolean addSort(E e) {//menambahkan term atau doc ke linkedlist dengan terurut dan tanpa duplikasi 
        ListIterator<E> iterator = this.listIterator();
        while (iterator.hasNext()) {
            E temp = iterator.next();
            // Jika elemen saat ini lebih besar dari e, sisipkan e tepat sebelum temp
            if (((Comparable) temp).compareTo(e) > 0) {
                iterator.previous();
                iterator.add(e);
                return true;
            } else if (((Comparable) temp).compareTo(e) == 0) {
                // Elemen sama sudah ada -> pertahankan keunikan data
//                ((Increment) temp).inc(); ini untuk tf 
                return true;
            }
        }
        // Jika lebih besar dari semua elemen yang ada, tambahkan di akhir list
        iterator.add(e);
        return true;
    }

    /**
     * Mencari elemen yang sama dengan {@code e} secara linear menggunakan {@code compareTo == 0}.
     *
     * @param e objek kunci pencarian
     * @return referensi elemen di dalam list jika ditemukan, atau {@code null} jika tidak ada
     */
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

    /**
     * Menggabungkan seluruh elemen list menjadi satu string dipisahkan spasi.
     *
     * @return representasi string seluruh elemen
     */
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