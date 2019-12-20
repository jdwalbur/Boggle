/*
Jakob Walburger
CS110-B
Game.java handles everything about the Game of boggle except for the user interaction, this is done in
BoggleText.java. This class takes all the other classes and puts them together so we have our game of Boggle
 */
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private ArrayList<Tile> selectedTiles;
    private ArrayList<String> wordList;
    private int score;
    Board board;
    Dictionary dict;

    /**
     * This creates a new game with empty ArrayLists for the selected tiles and the word list
     * as well as creating the board for the game, dictionary, and setting the score to 0
     *
     * @throws IOException
     */
    public Game() throws IOException {
        selectedTiles = new ArrayList<>();
        wordList = new ArrayList<>();
        board = new Board();
        dict = new Dictionary("dictionary.txt");
        score = 0;
    }


    /**
     * @param row the row of the Tile selected
     * @param col the column of the Tile selected
     * @return a boolean that shows if the tile selected is next to the previous tile
     */
    public boolean isValidSelection(int row, int col) {
        if (selectedTiles.isEmpty()) {
            return true;
        }

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                //Check to see if the tile we will be looking at is in the board
                if ((i > -1 && i < 4) && (j > -1 && j < 4)) {
                    //get the tile and get its flag
                    if (board.getTile(i, j).getFlag()) {
                        //get the last tile that was added to the list
                        Tile lastTile = selectedTiles.get(selectedTiles.size() - 1);
                        //if the i (row) and j (col) equal the row and col of the lastTile
                        //Then the word can be selected.
                        if (lastTile.equals(board.getTile(i, j))) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }


    /**
     * @param row the row of the selected tile
     * @param col the column of the selected tile
     */
    public void addToSelected(int row, int col) {
        selectedTiles.add(board.getTile(row, col));
        Tile tile = board.getTile(row, col);
        tile.select(); //set flag to true
    }

    /**
     * @param row the row of the selected tile
     * @param col the column of the selected tile
     */
    public void removeFromSelected(int row, int col) {
        //Check to make sure you can only remove the last added Tile
        //if(selectedTiles.get(selectedTiles.size()-1).equals(board.getTile(row, col))){
        selectedTiles.remove(board.getTile(row, col));
        Tile tile = board.getTile(row, col);
        tile.select(); //set flag to false
        //}
    }

    /**
     * @return an ArrayList of the selected tiles
     */
    public ArrayList<Tile> getSelectedTiles() {
        return selectedTiles;
    }

    /**
     * @param row the row of the tile that will be get
     * @param col the column of the tile that will be get
     * @return the tile at that row and col in the board
     */
    public Tile getTile(int row, int col) {
        return board.getTile(row, col);
    }

    /**
     * Clear the selected tiles
     */
    public void clearSelected() {
        selectedTiles.clear();
    }

    /**
     * Test to see if the tiles selected form a valid word
     */
    public void testSelected() {
        if (dict.isValidWord(selectedTiles)) {
            Word w = new Word(selectedTiles);
            if (!wordList.contains(w.toString())) {
                wordList.add(w.toString());
                score += w.getPoints();
            }
        }
        selectedTiles.clear();
    }

    /**
     * get the score of the game
     * @return the score of the game as an int
     */
    public int getScore() {
        return score;
    }

    /**
     * get the wordList of the Game
     * @return the wordList as an arrayList of Strings
     */
    public ArrayList<String> getWordList() {
        return wordList;
    }


    /**
     * @return the string representation of the game interface and board
     */
    public String toString() {
        String str = "Selected: [";
        for (int i = 0; i < selectedTiles.size(); i++) {
            str += selectedTiles.get(i).getLetter() + " ";
        }
        str += "]\nWords: [";

        for (int i = 0; i < wordList.size(); i++) {
            str += wordList.get(i) + ", ";
        }
        str += "]\nScore: " + score + "\n" + board.toString();

        return str;

    }
}
