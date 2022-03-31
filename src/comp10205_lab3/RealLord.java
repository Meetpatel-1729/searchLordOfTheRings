/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp10205_lab3;

import java.util.ArrayList;

/**
 * I, Meet Patel, student number 000794612, certify that all the work submitted
 * is my own work; that I have not copied it from any other source. I also
 * certify that I have not allowed my work to be copied by others.
 *
 * @author Meet Patel
 */
public class RealLord {

    private final String characterName; // Character Name

    private Double closenessFactor; // Closeness Factor which is clossness count / total occurence of the character

    private ArrayList<Integer> characterPositions; // Character position in the novel

    private Integer closenessCount; // closeness count between character and ring

    /**
     * Parameterized constructor which will set the character name, set the
     * closeness count to 0 and create a new array list for each character to
     * store it's location in novel
     *
     * @param chName Character Name.
     */
    public RealLord(String chName) {
        this.characterName = chName;
        this.closenessCount = 0; // default closeness count for each character
        characterPositions = new ArrayList<>(); // create new arraylist for each character
    }

    // To get the charcater name
    public String getcharacterName() {
        return characterName;
    }

    // To get the clossenes factor which is clossness count / total occurence of the character
    public Double getClosenessFactor() {
        return closenessFactor;
    }

    // To set the closeness factor which is clossness count / total occurence of the character
//    public void setClosenessFactor(double cFactor) {
//        this.closenessFactor = cFactor;
//    }
    public void setClosenessFactor() {
        this.closenessFactor = (double) getClosenessCount() / getCharacterPositions().size();
    }

    // To get the arraylist of character positions in the novel
    public ArrayList<Integer> getCharacterPositions() {
        return characterPositions;
    }

    // To set the location in the arraylist
    public void setLocations(int location) {
        this.characterPositions.add(location);
    }

    // To get the closeness count 
    public Integer getClosenessCount() {
        return closenessCount;
    }

    // To set the closeness count
    public void setCloseness() {
        this.closenessCount++;
    }

    /**
     * toString Method
     *
     * @return Object name with it's values.
     */
    // 
    @Override
    public String toString() {
        return "[" + characterName + ", " + getCharacterPositions().size() + "] Close to Ring " + getClosenessCount()
                + " Closeness Factor " + String.format("%.4f", getClosenessFactor());
    }

}
