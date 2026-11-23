package test;

import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RACHEL RAMBU
 */
public class Term implements Comparable<Term> {//term harus bisa di searching makanya harus comparable
    
    //ada 2 atribut karena kelas term harus bisa 
    protected String term;//untuk menyimpan kata (dictionary) 
    protected LinkedListOrderedUnique<Document> documentList;//untuk menyimpan posting list
    //jadi documentlist adalah linkedlist dengan tipe dokument. 
    //posting list sturktur datanya adalah linked list 

    public Term(String inTerm) {
        term = inTerm;
        // Buat document list
        documentList = new LinkedListOrderedUnique<Document>();

    }

    public void addDocument(String doc) {
        Document existingDoc = documentList.get(new Document(doc));
        if (existingDoc == null) {
            documentList.addSort(new Document(doc));
        }
    }

    public String getTerm() {
        return term;
    }

    public LinkedListOrderedUnique<Document> getDocumentList() {
        return documentList;
    }

    public int compareTo(Term o) {
        return term.compareTo(o.term);
    }

    @Override
    public String toString() {
        String temp = term + " => ";
        temp = temp + documentList.toString();
        return temp;
    }

}
//menyimpan term dan posting list yang mengandung term tersebut.