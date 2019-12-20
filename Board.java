/*
Jakob Walburger
Board.java - This creates an ArrayList of and Arraylist of Tiles by randomly selecting predetermined
Die and adding these to the board.
 */


import java.util.ArrayList;
import java.util.Random;

public class Board {

    private ArrayList<ArrayList<Tile>> board;


    /**
     * This first creates the 16 die that boggle uses. Then it will select a random die
     * and then a random side from that die, add it the 2D ArrayList of tiles. Once this
     * is done it will remove that die so it cannot be used again in the same board.
     */
    public Board(){
        ArrayList<String> dieList = new ArrayList<>();
        dieList.add(0, "RIFOBX");//RIFOBX
        dieList.add(1, "IFEHEY");
        dieList.add(2, "DENOWS");
        dieList.add(3, "UTOKND");
        dieList.add(4, "HMSRAO");
        dieList.add(5, "LUPETS");
        dieList.add(6, "ACITOA");
        dieList.add(7, "YLGKUE");
        dieList.add(8, "QBMJOA"); //Plain Q will be displayed as Qu
        dieList.add(9, "EHISPN");
        dieList.add(10, "VETIGN");
        dieList.add(11, "BALIYT");
        dieList.add(12, "EZAVND");
        dieList.add(13, "RALESC");
        dieList.add(14, "UWIFRG");
        dieList.add(15, "PACEMD");

        board = new ArrayList<>();
        ArrayList<Tile> tiles;
        Random random = new Random();//12
        for(int row=0; row<4; row++){
            tiles = new ArrayList<>();
            for(int col=0; col<4; col++){
                int die = random.nextInt(dieList.size());
                int side = random.nextInt(5)+1;
                String currentDie = dieList.get(die);
                char tileFace = currentDie.charAt(side);
                tiles.add(new Tile(tileFace, row, col));

                dieList.remove(dieList.get(die)); //remove the die once its been placed in the board

            }

            board.add(tiles);
        }
    }

    /**
     *
     * @return the 2D ArrayList of Tile representation of the board
     */
    public ArrayList<ArrayList<Tile>> getBoard(){
        return board;
    }

    /**
     *
     * @param row the row of the tile that is being get
     * @param col the column of the tile that is being get
     * @return the Tile at the row and column in the board
     */
    public Tile getTile(int row, int col){
        return board.get(row).get(col);
    }

    @Override
    /**
     * @return the string value of board. Displayed as a 4x4 grid.
     */
    public String toString(){
        int temp=0;
        String str = "";
        for(int i=0; i<4; i++){
            for(int j=temp; j<4; j++){

                if(board.get(i).get(j).getLetter()== 'Q'){
                    str += "Qu ";
                }
                else {
                    str += board.get(i).get(j).getLetter() + "  ";
                }

            }
            str+="\n";
        }
        return str;
    }

}
