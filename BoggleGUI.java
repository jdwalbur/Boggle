import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;


public class BoggleGUI extends Application {
    private BorderPane mainPane;
    private GridPane boardGrid;
    private VBox infoPane;
    private HBox buttonPane;
    private VBox statusPane;
    private Button testWord;
    private Button exit;
    private Button newGame;
    private Text points;
    private Text status;
    private Game game;
    private Word word;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Boggle");
        mainPane = new BorderPane();
        boardGrid = new GridPane();
        statusPane = new VBox();
        try{
            game = new Game();
        } catch(IOException e){
            e.printStackTrace();
        }
        newBoard();


        mainPane.setMinSize(250, 250);
        buttonPane = new HBox(10);
        infoPane = new VBox();
        points = new Text("Score: 0");
        status = new Text("STATUS");


        buttonPane.setAlignment(Pos.CENTER);
        statusPane.setAlignment(Pos.CENTER);


        testWord = new Button("Test Selected");
        testWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent test) {
                int tempPoints = game.getScore();
                resetBoardTiles();
                word = new Word(game.getSelectedTiles());
                game.testSelected();
                int currentPoints = game.getScore();

                if (currentPoints > tempPoints) {
                    infoPane.getChildren().add(new Text(word.toString()));
                    String info = "Score: " + game.getScore();
                    points.setText(info);
                    status.setText("Valid Word");
                } else {
                    status.setText("Invalid Word");
                }
            }
        });


        exit = new Button("End Game");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                exit.setDisable(true);
                testWord.setDisable(true);
                boardGrid.setDisable(true);
                resetBoardTiles();

                status.setText("You made: " + game.getWordList().size() + " words and got " + game.getScore() + " points.");
            }

        });


        //New game will not be disabled when end game is pressed
        newGame = new Button("New Game");
        newGame.setOnAction(this::newGame);



        mainPane.setStyle("-fx-background-color: grey;");
        infoPane.setStyle("-fx-border-width: 2px;" + "-fx-border-color: black;" + "-fx-background-color: grey;");
        statusPane.setStyle("-fx-border-color: black;" + "-fx-border-width: 2px;" + "-fx-background-color: grey;");
        buttonPane.setStyle("-fx-border-color: black;" + "-fx-background-color: grey;");

        infoPane.getChildren().add(new Text("Words:"));
        statusPane.getChildren().add(points);
        statusPane.getChildren().add(status);
        buttonPane.getChildren().add(exit);
        buttonPane.getChildren().add(testWord);
        buttonPane.getChildren().add(newGame);
        mainPane.setTop(statusPane);
        mainPane.setCenter(boardGrid);
        mainPane.setBottom(buttonPane);
        mainPane.setLeft(infoPane);


        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newGame(ActionEvent event) {
        try {
            game = new Game();
            String info = "Score: " + game.getScore();
            points.setText(info);
            resetBoardTiles();
            infoPane.getChildren().clear();
            infoPane.getChildren().add(new Text("Words:"));

            exit.setDisable(false);
            testWord.setDisable(false);
            boardGrid.setDisable(false);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClick(MouseEvent e) {
        TilePane tp = (TilePane) (e.getSource());

        if (game.isValidSelection(tp.getRow(), tp.getCol())) {
            if (game.getSelectedTiles().contains(game.getTile(tp.getRow(), tp.getCol()))) {
                if (game.getTile(tp.getRow(), tp.getCol()).equals(game.getSelectedTiles().get(game.getSelectedTiles().size() - 1))) {
                    game.removeFromSelected(tp.getRow(), tp.getCol());
                    tp.unSelect();
                    status.setText("Deselected");
                }
            } else {
                game.addToSelected(tp.getRow(), tp.getCol());
                tp.setSelected();
                status.setText("Selected");
            }
        } else {
            status.setText("Invalid selection");
        }
    }

    public void newBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                TilePane tilePane = new TilePane(game.getTile(i, j));
                tilePane.setOnMouseClicked(this::handleClick);
                boardGrid.add(tilePane, j, i);
            }
        }
    }


    public void resetBoardTiles() {
        boardGrid.getChildren().clear();

        //reset all flags to false
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //if the flag is TRUE set it to FALSE
                if (game.getTile(i, j).getFlag()) {
                    game.getTile(i, j).select();
                }
            }
        }
        //draw board
        newBoard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
