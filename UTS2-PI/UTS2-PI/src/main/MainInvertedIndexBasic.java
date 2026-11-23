/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import test.InvertedIndexBasic;
import java.util.Scanner;
import test.Document;
import test.LinkedListOrderedUnique;
import test.ReadFileToken;
/**
 *
 * @author RACHEL RAMBU
 */
public class MainInvertedIndexBasic {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Membaca dokumen dan membangun Inverted Index
        ReadFileToken reader = new ReadFileToken();
        InvertedIndexBasic cobaList = reader.readDocuments("..\\Koleksi");

        // Menampilkan Inverted Index
        System.out.println("\n===== INVERTED INDEX =====");
        System.out.println(cobaList);

        // Searching
        Scanner search = new Scanner(System.in);

        System.out.println("\n===== SEARCHING =====");
        System.out.print("Masukkan keyword: ");

        String keyword = search.nextLine();
        LinkedListOrderedUnique<Document> hasil = cobaList.searchTerm(keyword);
        System.out.println("\nHasil pencarian:");
        if(hasil.isEmpty()){
            System.out.println("Keyword tidak ditemukan !");
        }else {
            System.out.println(hasil);
        }
        
    }
}
