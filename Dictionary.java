/*
Jakob Walburger
Dictionary.java - This takes a file and adds every word of the file (line) to an ArrayList, this also has
a method that checks to see if a word is in the dictionary
 */


import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> wordList;

    /**
     * This will take in the filename and add every word in that list to an
     * ArrayList of Strings called wordList
     * @param fileName the filename of the file as a String
     * @throws IOException
     */
    public Dictionary(String fileName) throws IOException {
        wordList = new ArrayList<>();
        Scanner file = new Scanner(new File(fileName));
        while(file.hasNext()){
            wordList.add(file.nextLine().toUpperCase());
        }
    }

    /**
     *
     * @param tiles an ArrayList of tiles
     * @return if the selected tiles are a valid word
     */
    public boolean isValidWord(ArrayList<Tile> tiles){

        Word w = new Word(tiles);

        for(int i=0; i<wordList.size(); i++){
            if(wordList.get(i).equals(w.toString())){
                return true;
            }
        }
        return false;
    }
}
