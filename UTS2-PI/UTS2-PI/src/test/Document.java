/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author RACHEL RAMBU
 */
public class Document implements Comparable<Document> {

    protected String document;
//    protected int frequency;

    public Document() {
    }

    public Document(String inDocument) {
        document = inDocument;
//        frequency = 1;
    }

    public String getDocument() {
        return document;
    }

    public int compareTo(Document o) {
        return document.compareTo(o.document);
    }

    public String toString() {
        return document;
    }
//    public int getFrequency() {
//        return frequency;
//    }
}
//menyimpan nama dokumen dan membandingkan nama dokumen