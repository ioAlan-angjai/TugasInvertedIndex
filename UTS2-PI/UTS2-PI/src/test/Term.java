package test;

import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Merepresentasikan satu entri kata (term) di dalam dictionary beserta
 * daftar dokumen kemunculannya (posting list).
 *
 * @author RACHEL RAMBU
 */
public class Term implements Comparable<Term> {//term harus bisa di searching makanya harus comparable
    
    //ada 2 atribut karena kelas term harus bisa 
    protected String term;//untuk menyimpan kata (dictionary) 
    protected LinkedListOrderedUnique<Document> documentList;//untuk menyimpan posting list
    //jadi documentlist adalah linkedlist dengan tipe dokument. 
    //posting list sturktur datanya adalah linked list 

    /**
     * Membuat objek Term baru dengan posting list kosong.
     *
     * @param inTerm kata dasar yang disimpan pada dictionary
     */
    public Term(String inTerm) {
        term = inTerm;
        // Buat document list terurut dan unik
        documentList = new LinkedListOrderedUnique<Document>();

    }

    /**
     * Menambahkan nama dokumen ke posting list term ini jika belum ada.
     *
     * @param doc nama file dokumen
     */
    public void addDocument(String doc) {
        // Cek apakah dokumen sudah tercatat agar tidak terjadi duplikasi pada posting list
        Document existingDoc = documentList.get(new Document(doc));
        if (existingDoc == null) {
            documentList.addSort(new Document(doc));
        }
    }

    /**
     * Mengambil string term.
     *
     * @return teks term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Mengambil posting list dokumen yang mengandung term ini.
     *
     * @return daftar dokumen terurut
     */
    public LinkedListOrderedUnique<Document> getDocumentList() {
        return documentList;
    }

    /**
     * Membandingkan dua term secara alfabetis berdasarkan atribut {@code term}.
     *
     * @param o objek Term pembanding
     * @return hasil perbandingan leksikografis
     */
    public int compareTo(Term o) {
        return term.compareTo(o.term);
    }

    /**
     * Mengembalikan format tampilan {@code term => doc1 doc2 ...}.
     *
     * @return representasi string term dan posting list-nya
     */
    @Override
    public String toString() {
        String temp = term + " => ";
        temp = temp + documentList.toString();
        return temp;
    }

}
//menyimpan term dan posting list yang mengandung term tersebut.