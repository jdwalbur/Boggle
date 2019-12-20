/*
Jakob Walburger
Tile.java - A class to represent teach tile of the board. Each object holds a character, and two ints to represent
the row and col of where the tile is located on the board
 */

public class Tile {
    private char letter;
    private int row;
    private int col;
    private boolean flag;

    /**
     *
     * @param ch the character of the tile
     * @param row the row position of the tile in the board
     * @param col the column position fo the tile in the board
     */
    public Tile(char ch, int row, int col){
        letter = Character.toUpperCase(ch);
        this.row = row;
        this.col = col;
        flag = false;
    }

    /**
     *
     * @return the letter as a character
     */
    public char getLetter(){
        return letter;
    }


    /**
     * set flag to the opposite value as before
     */
    //If the user selects a unselected character then this will set the flag to true
    //Vice versa if the tile is already selected then it will set the flag to false
    public void select(){
        flag = !flag;
    }

    /**
     *
     * @return the value of flag as a boolean
     */
    public boolean getFlag(){
        return flag;
    }

    /**
     *
     * @return the row of the tile as an int
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return the column of the tile as an int
     */
    public int getCol(){
        return col;
    }

    /**
     *
     * @param other the other object we are comparing
     * @return the boolean value of if the row and column are the same for
     * both of the objects
     */
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

        Tile otherTile = (Tile)other;

        return this.getRow() == otherTile.getRow() && this.getCol() == otherTile.getCol();
    }

}
