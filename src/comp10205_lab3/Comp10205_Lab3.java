/**
 * COMP10205 - Lab#3 Starter Code
 *
 * @author C. Mark Yendt
 * Modified by Meet Patel
 * @version 1.2 (April 2016, February 2019)
 *
 *
 * DISCUSSION LINEAR SEACRH, BINARY SEACRH AND SIMPLE HASH SET
 *
 * 1. Linear Search: It is the slowest of all as it takes one element from the
 * dictionary and checks it in the novel upto end of the list That's why it is
 * the slowest one and it takes up to much time of around 169383250 us.
 *
 * 2. Binary Search: It is faster compare to the Linear Search as it takes the
 * sorted data split that data into two parts so to find a specific word using binary search it will have to go 
 * through half of the list. That's why it is faster compare to Linear search. it takes around 1305196 us for the given set of data.
 *
 * 3. Simple Hash Set: It is the fastest among Linear and Binary search as first
 * it generates the hash value of the word and put that word in a bucket so to
 * find a word from that list it just need to find a specific bucket only. so it
 * reduces the search area that's why it is fastest among linear and binary
 * search. It takes around 53030 us for the given set of data.
 */
package comp10205_lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Comp10205_Lab3 {

    /**
     * The starting point of the application
     *
     * @param args the command line arguments - not used
     */
    public static void main(String[] args) {
        // File is stored in a resources folder in the project
        final String novelName = "resources/TheLordOfTheRIngs.txt";

        final String dictionaryFile = "resources/US.txt";

        ArrayList<BookWord> novel = new ArrayList<>(); // Arraylist of novel 

        ArrayList<BookWord> dictionary = new ArrayList<>(); // Arraylist of Dictionary

        SimpleHashSet<BookWord> dictionary2 = new SimpleHashSet<>(); // Arratlist of type simple Hash Set

        ArrayList<RealLord> characterArrayList = new ArrayList<>(); // Arraylist of type RealLord

        // Arraylist of all the characters in the novel and ring
        ArrayList<String> characters = new ArrayList<>(Arrays.asList("frodo", "sam", "bilbo", "gandalf", "boromir", "aragorn", "legolas",
                "gollum", "pippin", "merry", "gimli", "sauron", "saruman", "faramir",
                "denethor", "treebeard", "elrond", "galadriel", "ring"));

        // This loop will create all the characters object and will add those objects in characterArrayList
        for (String s : characters) {
            characterArrayList.add(new RealLord(s));
        }

        // Read in the dictionary 
        try {
            Scanner fin = new Scanner(new File(dictionaryFile), "UTF-8"); // Scanner object

            while (fin.hasNext()) {
                String w = fin.next().toLowerCase(); // will convert the word into lowercase

                // TODO : Add code to store dictionary words into an appropriate data structure
                BookWord temp = new BookWord(w); // create an object og BookWord

                dictionary.add(temp); // Add the word into ArrayList

                dictionary2.insert(temp); // Add the word into simple hash set type ArrayList
            }

            fin.close(); // Close the file

        } catch (FileNotFoundException e) {
            // This block of code will catch the file not found exception and print the message
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Read in the text file 
        try {
            int wordPosition = 0; // store the word position

            Scanner fin = new Scanner(new File(novelName)); // Scanner object

            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE 

            // will loop through the end of the file
            while (fin.hasNext()) {
                String fileWord = fin.next().toLowerCase(); // Will convert the word into lowercase

                // check if the word is not blank space
                if (fileWord.length() > 0) {
                    wordPosition++; // will increment the word position

                    //System.out.printf("%s\n", fileWord);
                    BookWord bw = new BookWord(fileWord); // create an object of BookWord

                    // Check if the word already exist in the araaylist or not
                    if (novel.contains(bw)) {
                        novel.get(novel.indexOf(bw)).incrementCount(); // will get the index of the word and increment it's count
                    } else {
                        novel.add(bw); // will add the word if it does not exist in the arraylist
                    }

                    // TODO : Need to create an instance of a BookWord object here and add to ArrayList
                    for (RealLord r : characterArrayList) {
                        // Checks if the word is any of the character 
                        if (r.getcharacterName().equals(fileWord)) {

                            // if the word is any of the character then it will add the position of that word into arraylist
                            r.setLocations(wordPosition);
                        }
                    }
                }
            }
            fin.close(); // Close the file

        } catch (FileNotFoundException e) {
            // This block of code will catch the file not found exception and print the message
            System.out.println("Exception caught: " + e.getMessage());
        }

        // TODO: Analyze and report code here based on lab requirements
        int totalWords = 0; // will store the total number of words

        ArrayList<String> sixtyFourOccurences = new ArrayList<>(); // Stores the words which are occuring exactly 64 times

        // This loop will go through novel arraylist and add each words count into totalWords 
        for (int i = 0; i < novel.size(); i++) {
            if (novel.get(i).getCount() == 64) {
                sixtyFourOccurences.add(novel.get(i).getText());
            }
            totalWords += novel.get(i).getCount();
        }

        System.out.println("Total number of words: " + totalWords); // Prints the total number of words

        System.out.println("\nTotal unique Words: " + novel.size()); // Prints the total number of unique words in the novel

        novel.sort((n1, n2) -> (n2.getCount()).compareTo(n1.getCount())); // Sort the novel in descending order

        System.out.println("\nThe List of the 10 most frequent words and counts: ");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ") " + novel.get(i).toString()); // Print the words which are occuring highest number of times
        }

        sixtyFourOccurences.sort((s1, s2) -> s1.compareTo(s2)); // Sort the Array in Ascending order by comparing ASCII values

        System.out.println("\nList of words occuring 64 times in Lord of The Ring file in Ascending order:");

        // Print all the words which are occuring exactly 64 times in Ascending order
        for (int i = 0; i < sixtyFourOccurences.size(); i++) {
            System.out.println((i + 1) + ") " + sixtyFourOccurences.get(i));
        }

        // The number of words does not contain in the dictionary
        // 1. Using contains method of Arraylist
        totalWords = 0; // set the total number of words to 0

        long startTime = System.nanoTime(); // Stores the Start time

        for (BookWord b : novel) {
            // Checks the dictionary if it contains the novel word
            if (!(dictionary.contains(b))) {
                totalWords++; // increment the word which is not in dictionary
            }
        }
        long endTime = System.nanoTime(); // Stores the end time

        // Prints the word count which are not in the dictionary
        System.out.println("\nThe number of words does not contain in the dictionary (Method 1 - Using contains method): " + totalWords);

        System.out.printf("[Time = %d us]\n", (endTime - startTime) / 100); // total time to execute Using method of Arraylist

        // 2. Using the BinarySearch method of Collections class
        totalWords = 0; // set the total number of words to 0

        startTime = System.nanoTime(); // Stores the Start time

        dictionary.sort((d1, d2) -> (d1.getText()).compareTo(d2.getText())); // Sort the dictionary

        for (BookWord b : novel) {
            // Checks the dictionary if it contains the novel word by using binary search method of collection class
            if ((Collections.binarySearch(dictionary, b, (d1, d2) -> (d1.getText()).compareTo(d2.getText()))) < 0) {
                totalWords++;  // increment the word which is not in dictionary
            }
        }
        endTime = System.nanoTime(); // Stores the end time

        // Prints the word count which are not in the dictionary
        System.out.println("\nThe number of words does not contain in the dictionary (Method 2 - Using Binary Search): " + totalWords);

        // total time to execute Using BinarySearch method of Collections class
        System.out.printf("[Time = %d us]\n", (endTime - startTime) / 100);

        // 3. Using SimpleHashSet Dictionary and the contains method which is provided
        totalWords = 0; // set the total number of words to 0

        startTime = System.nanoTime(); // Stores the Start time

        for (BookWord b : novel) {
            // Checks the dictionary if it contains the novel word by using Simple Hash Set dictionary
            if (!(dictionary2.contains(b))) {
                totalWords++; //  // increment the word which is not in dictionary
            }
        }

        endTime = System.nanoTime(); // Stores the end time

        // Prints the word count which are not in the dictionary
        System.out.println("\nThe number of words does not contain in the dictionary (Method 3 - Using Simple Hash Set Dictionary ): " + totalWords);

        // total time to execute Using Simple Hash Set Class
        System.out.printf("[Time = %d us]\n", (endTime - startTime) / 100);

        // Prints the number of empty buckets
        System.out.println("Number of empty buckets: "
                + (dictionary2.getNumberofEmptyBuckets() * 100) / dictionary2.getNumberofBuckets() + "%");

        startTime = System.currentTimeMillis(); // Stores the Start time
        // Loop through All objects of RealLord
        for (RealLord r : characterArrayList) {
            // Checks if the object is not the ring
            if (!(r.getcharacterName().equals("ring"))) {
                // loop through all the character positions in the arraylist
                for (int i : r.getCharacterPositions()) {
                    // Loop through ring's position in the arraylist (18 is the position of the ring object in the ArrayList)
                    for (int j : characterArrayList.get(18).getCharacterPositions()) {
                        // Checks the proximity distacne cut-off of 42 words on both left and right side
                        if (j >= (i - 42) && j <= (i + 42)) {
                            r.setCloseness(); // Increment the closenes count
                        }
                    }
                }
            }
            r.setClosenessFactor(); // Set the closeness count
        }

        // Sort the objects in Descending order based on Closeness factor
        characterArrayList.sort((c1, c2) -> c2.getClosenessFactor().compareTo(c1.getClosenessFactor()));

        endTime = System.currentTimeMillis(); // Stores the end time

        System.out.println("\nSorted List of characters with a proximity ditance cut-off of 42:");
        // total time to execute Using Simple Hash Set Class
        System.out.printf("[Time = %d ms]\n", (endTime - startTime));

        // Loop through all the objects in the Character Arraylist
        for (int i = 0; i < characterArrayList.size() - 1; i++) {
            System.out.println((i + 1) + ") " + characterArrayList.get(i).toString());
        }

    }
}