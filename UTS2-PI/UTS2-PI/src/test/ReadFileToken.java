/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author RACHEL RAMBU
 */
public class ReadFileToken {
    public InvertedIndexBasic readDocuments(String path) {

        File name = new File(path);

        InvertedIndexBasic invertedIndex = new InvertedIndexBasic();

        if (name.exists() && name.isDirectory()) {

            String directory[] = name.list();

            for (String directoryName : directory) {

                File file = new File(name.getAbsolutePath() + File.separator + directoryName);

                if (file.isFile()) {

                    try {
                        Scanner input = new Scanner(file);

                        while (input.hasNextLine()) {

                            String sentence = input.nextLine();

                            String[] tokens = sentence.split("\\s+");
                            

                            for (String token : tokens) {
                                token = token.toLowerCase().replaceAll("[^a-z0-9]", "");

                                if (token.length() > 0) {

                                    System.out.println(directoryName + " " + token);
                                    invertedIndex.add(token, directoryName);
                                }
                            }
                        }

                        input.close();

                    } catch (FileNotFoundException e) {
                        System.err.println("Error opening file.");
                    }
                }
            }

        } else {
            System.out.println(path + " does not exist.");
        }

        return invertedIndex;
    }
}
//membaca dokumen, memisahkan teks menjadi token lalu memasukkan dlm inverted index.