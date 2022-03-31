package comp10205_lab3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * I, Meet Patel, student number 000794612, certify that all the work submitted
 * is my own work; that I have not copied it from any other source. I also
 * certify that I have not allowed my work to be copied by others.
 *
 * @author Meet Patel
 */
public class BookWord {

    private final String text; // Stores the word

    private Integer count; // Stores the count 

    /**
     * Parameterized constructor
     *
     * @param wordText Word.
     */
    public BookWord(String wordText) {
        this.text = wordText;
        this.count = 1;
    }

    // To get Text
    public String getText() {
        return text;
    }

    // To get Count
    public Integer getCount() {
        return count;
    }

    // To increment Count
    public void incrementCount() {
        count++;
    }

    /**
     * Overridden equals method
     *
     * @param wordToCompare object.
     * @return true or false based on the compared words
     */
    @Override
    public boolean equals(Object wordToCompare) {
        if (wordToCompare instanceof BookWord) {
            BookWord w = (BookWord) wordToCompare;
            return this.text.equals(w.text);
        }
        return false;
    }

    /**
     * This code was taken from
     * https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
     * Modified by Meet Patel
     *
     * @return hash value
     */
    @Override
    public int hashCode() {
        int result = 17;
        result =  19 * result + text.hashCode();
        return result;
    }

    /**
     * toString method
     *
     * @return text and count
     */
    @Override
    public String toString() {
        return "[" + text + ", " + count + "]";
    }

}
