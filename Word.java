/*
Jakob Walburger
Word.java - This takes an array list of Tiles and creates a  string, there is a method that looks at the
length of the string and assigns a point value to the string.
 */

import java.util.ArrayList;

public class Word{

    private String word;
    private int points;

    /**
     * This takes in an ArrayList of tiles converts and stores that as a String as well as
     * calculating the point value of the word based off of its length
     * @param tiles an ArrayList of tiles that is needed to create the word as a String
     */
    public Word(ArrayList<Tile> tiles){
        word = "";
        for(int i=0; i<tiles.size(); i++){
            if(tiles.get(i).getLetter() == 'Q'){
                word += "QU";
            }
            else {
                word += tiles.get(i).getLetter();
            }
        }

        if(word.length() == 3 || word.length() == 4){
            points = 1;
        }
        else if(word.length() == 5){
            points = 2;
        }
        else if(word.length() == 6){
            points = 3;
        }
        else if(word.length() == 7){
            points = 5;
        }
        else if(word.length() >= 8){
            points = 11;
        }
        else {
            points = 0;
        }
    }


    /**
     *
     * @return the amount of points the word is worth as an integer
     */
    public int getPoints(){
        return points;
    }

    @Override
    /**
     *
     * @return return the string value of word
     */
    public String toString(){
        return word;
    }

    @Override
    /**
     * Check to see if each word string is the same
     */
    //This can be useful when we would have to if the word as already been selected
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(other == this){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }

        Word otherWord = (Word)other;

        return this.toString().equals(otherWord.toString());
    }

}
