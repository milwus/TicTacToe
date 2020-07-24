import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TicTacToe extends Application {

    private final Moves X = new Moves();
    private final Moves O = new Moves();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("TicTacToe by milwus");

        VBox root = new VBox(5);
        root.setPadding(new Insets(20, 60, 20, 60));
        root.setAlignment(Pos.CENTER);

        Label messageLabel = new Label("X plays...");
        messageLabel.setFont(new Font(50));
        messageLabel.setPadding(new Insets(0, 10, 50, 10));

        HBox row1 = new HBox(5);
        Button r1c1 = new Button();
        r1c1.setMinSize(100,100);
        Button r1c2 = new Button();
        r1c2.setMinSize(100,100);
        Button r1c3 = new Button();
        r1c3.setMinSize(100,100);
        row1.getChildren().addAll(r1c1, r1c2, r1c3);

        HBox row2 = new HBox(5);
        Button r2c1 = new Button();
        r2c1.setMinSize(100,100);
        Button r2c2 = new Button();
        r2c2.setMinSize(100,100);
        Button r2c3 = new Button();
        r2c3.setMinSize(100,100);
        row2.getChildren().addAll(r2c1, r2c2, r2c3);

        HBox row3 = new HBox(5);
        Button r3c1 = new Button();
        r3c1.setMinSize(100,100);
        Button r3c2 = new Button();
        r3c2.setMinSize(100,100);
        Button r3c3 = new Button();
        r3c3.setMinSize(100,100);
        row3.getChildren().addAll(r3c1, r3c2, r3c3);
        row3.setPadding(new Insets(0, 0, 30, 0));

        Button resetBtn = new Button("Play again");
        resetBtn.setMinHeight(50);
        resetBtn.setFont(Font.font(20));
        resetBtn.setDisable(true);

        root.getChildren().addAll(messageLabel, row1, row2, row3, resetBtn);

        Image img = new Image("img.png",32,32,false,true);
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(105, 105, false, false, false, false));
        root.setBackground(new Background(background));


        r1c1.setOnAction(event -> {
            play(1, 1, r1c1, messageLabel);
            r1c1.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r1c2.setOnAction(event -> {
            play(1, 2, r1c2, messageLabel);
            r1c2.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r1c3.setOnAction(event -> {
            play(1, 3, r1c3, messageLabel);
            r1c3.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r2c1.setOnAction(event -> {
            play(2, 1, r2c1, messageLabel);
            r2c1.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r2c2.setOnAction(event -> {
            play(2, 2, r2c2, messageLabel);
            r2c2.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r2c3.setOnAction(event -> {
            play(2, 3, r2c3, messageLabel);
            r2c3.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r3c1.setOnAction(event -> {
            play(3, 1, r3c1, messageLabel);
            r3c1.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r3c2.setOnAction(event -> {
            play(3, 2, r3c2, messageLabel);
            r3c2.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });
        r3c3.setOnAction(event -> {
            play(3, 3, r3c3, messageLabel);
            r3c3.setDisable(true);
            checkIfFinished(root, messageLabel, resetBtn);
        });

        resetBtn.setOnAction(event -> {
            X.resetBitMask();
            O.resetBitMask();
            Moves.resetMovesPlayed();
            Moves.resetGame();
            messageLabel.setText("X plays...");
            changeAllButtonFields(root, false);
            resetBtn.setDisable(true);
        });

        primaryStage.setScene(new Scene(root, 430,550));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void play(int row, int column, Button btn, Label message) {
        btn.setFont(Font.font(40));

        if (Moves.isX()) {
            X.plays(row, column);
            btn.setText("X");
            message.setText("O plays...");
        } else {
            O.plays(row, column);
            btn.setText("O");
            message.setText("X plays...");
        }
    }

    private void checkIfFinished(VBox root, Label message, Button resetBtn) {
        if (Moves.getGame().isFinished()) {
            message.setText(Moves.endMessage());
            changeAllButtonFields(root, true);
            resetBtn.setDisable(false);
        }
    }

    private void changeAllButtonFields(VBox root, boolean disable) {
        for (Object o : root.getChildren()) {
            if (o instanceof HBox) {
                for (Object p : ((HBox) o).getChildren()) {
                    if (p instanceof Button) {
                        ((Button) p).setDisable(disable);

                        if (!disable) {
                            ((Button) p).setText("");
                        }
                    }
                }
            }
        }
    }
}
