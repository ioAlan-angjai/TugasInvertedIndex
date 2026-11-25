/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * Merepresentasikan satu entitas dokumen pada posting list.
 * Mengimplementasikan {@link Comparable} agar daftar dokumen dapat diurutkan
 * secara leksikografis (alfabetis) dan di-intersect dengan cepat.
 *
 * @author RACHEL RAMBU
 */
public class Document implements Comparable<Document> {

    /** Nama file dokumen (mis. "doc1.txt"). */
    protected String document;
//    protected int frequency;

    /**
     * Konstruktor default tanpa parameter.
     */
    public Document() {
    }

    /**
     * Membuat objek Document baru dengan nama file tertentu.
     *
     * @param inDocument nama file dokumen
     */
    public Document(String inDocument) {
        document = inDocument;
//        frequency = 1;
    }

    /**
     * Mengambil nama file dokumen.
     *
     * @return nama dokumen
     */
    public String getDocument() {
        return document;
    }

    /**
     * Membandingkan dua dokumen berdasarkan urutan alfabetis nama filenya.
     * Digunakan oleh {@link LinkedListOrderedUnique#addSort} dan proses intersect.
     *
     * @param o objek Document pembanding
     * @return nilai negatif, nol, atau positif sesuai urutan String.compareTo
     */
    public int compareTo(Document o) {
        return document.compareTo(o.document);
    }

    /**
     * Mengembalikan representasi string berupa nama file dokumen.
     *
     * @return nama dokumen
     */
    public String toString() {
        return document;
    }
//    public int getFrequency() {
//        return frequency;
//    }
}
//menyimpan nama dokumen dan membandingkan nama dokumen