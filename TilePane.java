import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TilePane extends StackPane {

    private Tile tile;
    private int row;
    private int col;

    /**
     * Create a StackPane that represents a tile.
     * Store the row and col of the tile and add Text to the pane
     * @param tile the tile that will be used to get the information for the TilePane
     */
    public TilePane(Tile tile){
        this.tile = tile;
        this.setStyle("-fx-border-color: black;"
                +"-fx-border-width: 3;");
        this.setPrefSize(50,50);
        Text tileText = new Text();
        if(tile.getLetter() == 'Q'){
            tileText.setText("Qu");
        }
        else{
            tileText.setText(String.valueOf(tile.getLetter()));
        }

        this.getChildren().add(tileText);

        row = tile.getRow();
        col = tile.getCol();
    }

    /**
     * Change the background color of the TilePane when it is selected
     */
    public void setSelected(){
        this.setStyle("-fx-background-color: yellow;" + "-fx-border-color: black;"
                +"-fx-border-width: 3;");
    }

    /**
     * Change the background color of the TIlePane to grey when it is unseleceted.
     */
    public void unSelect(){
        this.setStyle("-fx-background-color: grey;" + "-fx-border-color: black;"
                +"-fx-border-width: 3;");
    }

    /**
     * Get the row of the TilePane
     * @return the row as an int
     */
    public int getRow(){
        return row;
    }

    /**
     * Get the col of the TilePane
     * @return the col as an int
     */
    public int getCol(){
        return col;
    }
}