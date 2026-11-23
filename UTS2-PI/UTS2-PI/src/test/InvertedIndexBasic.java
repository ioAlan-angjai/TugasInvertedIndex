/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ListIterator;

/**
 *
 * @author RACHEL RAMBU
 */
public class InvertedIndexBasic {

    private LinkedListOrderedUnique<Term> termList; // dictionary struktur datanya linkedlist 

    public InvertedIndexBasic() {
        termList = new LinkedListOrderedUnique<Term>();
    }

    public LinkedListOrderedUnique getTermList() {
        return termList;
    }

    public void add(String pTerm, String pDocument) {//token dan doc dimasukkan kesini

        // cari pTerm dalam Term list
        Term vTerm = termList.get(new Term(pTerm));//new Term karena yang dicari adalah kumpulan term 
        // Jika term belum ada
        if (vTerm == null) {
            // Buat simpul Term list
            Term oTerm = new Term(pTerm);
            // tambahkan simpul document ke documentList dari oTerm
            oTerm.addDocument(pDocument);
            // Tambahkan term object oTerm baru pertama ke termList;
            termList.addSort(oTerm);
        } // Jika term sudah ada
        else {
            // tambahkan simpul document ke documentList dari pTerm
            vTerm.addDocument(pDocument);
        }
    }
    
    //processing boolean queries (intersection) 
    private LinkedListOrderedUnique<Document> intersect(
            LinkedListOrderedUnique<Document> p1,
            LinkedListOrderedUnique<Document> p2) {

        LinkedListOrderedUnique<Document> answer  = new LinkedListOrderedUnique<Document>();
               
        ListIterator<Document> iterator1 = p1.listIterator();
        ListIterator<Document> iterator2 = p2.listIterator();

        Document doc1 = null;
        Document doc2 = null;

        if (iterator1.hasNext()) {
            doc1 = iterator1.next();
        }

        if (iterator2.hasNext()) {
            doc2 = iterator2.next();
        }

        while (doc1 != null && doc2 != null) {
            int comparison = doc1.compareTo(doc2);
            if (comparison == 0) {
                answer.addSort(doc1);
                if (iterator1.hasNext()) {
                    doc1 = iterator1.next();
                } else {
                    doc1 = null;
                }

                if (iterator2.hasNext()) {
                    doc2 = iterator2.next();
                } else {
                    doc2 = null;
                }

            } else if (comparison < 0) {

                if (iterator1.hasNext()) {
                    doc1 = iterator1.next();
                } else {
                    doc1 = null;
                }

            } else {

                if (iterator2.hasNext()) {
                    doc2 = iterator2.next();
                } else {
                    doc2 = null;
                }
            }
        }

        return answer;
    }

    public LinkedListOrderedUnique<Document> searchTerm(String keywords) {
        LinkedListOrderedUnique<Document> result = new LinkedListOrderedUnique<Document>(); //linkedlist yang digunakan untuk menampung dokumen 
        
        if(keywords == null || keywords.trim().isEmpty()){
            return result;
        }
        
        String[] keywordList = keywords.toLowerCase().trim().split("\\s+");
        for (int i = 0; i < keywordList.length; i++) {
            keywordList[i] = keywordList[i].replaceAll("[^a-z0-9]", "");
            
        }
        for (String keyword : keywordList) {
    if (keyword.isEmpty()) {
        return result;
    }
}
        
        Term firstTerm = termList.get(new Term(keywordList[0]));//mencari keyword pertama
        if (firstTerm == null) {
            return result;
        
        }
        
        for (Document doc : firstTerm.getDocumentList()) {//mengambil posting list keyword pertama 
        result.addSort(doc);
    }
        
        for (int i = 1; i < keywordList.length; i++) {
            Term nextTerm = termList.get(new Term(keywordList[i]));
            
            if(nextTerm == null){
                result.clear();
                return result;
            }
            result=intersect(result, nextTerm.getDocumentList());
            
            if(result.isEmpty()){
                break;
            }
            
        }
        
        return result;
    }

    @Override
    public String toString() {
        return termList.toString();
    }

}
//membangun dictionary, posting list, dan menjalankan pencarian