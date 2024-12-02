package htl.steyr.adventskalender;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdventCalendarApp extends Application {
    private static final int GRID_SIZE = 4;
    private static final int TOTAL_BUTTONS = 24;

    // Liste der Rätsel
    private final List<String> puzzles = new ArrayList<>(List.of(
            "Was ist die Binärdarstellung von 42?",
            "Wie heißt der Vorgänger von Java?",
            "Was ist die Zeitkomplexität von Quicksort im Durchschnitt?",
            "Was ist die höchste Zahl, die ein 8-Bit-Integer speichern kann?",
            "Was ist 111 in dezimaler Darstellung?",
            "Nenne ein Beispiel für eine funktionale Programmiersprache.",
            "Welches Unternehmen hat Java entwickelt?",
            "Wasa ist der Standard-Port für HTTPS?",
            "Welche Zahl ist die Basis des Hexadezimalsystems?",
            "Was ist das Ergebnis von 2^10?",
            "Wie viele Bits hat ein Byte?",
            "Was ist die Abkürzung für Central Processing Unit?",
            "Wie lautet der Befehl, um ein Git-Repository zu klonen?",
            "Welches Datenbanksystem verwendet SQL?",
            "Wie viele Primzahlen gibt es unter 10?",
            "Was ist ein Algorithmus?",
            "Nenne eine Programmiersprache für Webentwicklung.",
            "Was bedeutet OOP?",
            "Was ist eine rekursive Funktion?",
            "Was ist der Unterschied zwischen einem Compiler und einem Interpreter?",
            "Wie wird in Java eine Klasse definiert?",
            "Wie nennt man eine Schleife, die nie endet?",
            "Was bedeutet HTTP?",
            "Was ist der Unterschied zwischen RAM und ROM?"
    ));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        Random random = new Random();

        // GridPane Einstellungen
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            Button button = new Button("Türchen " + (i + 1));
            styleButton(button); // Styling anwenden
            button.setOnAction(e -> openPuzzleOrImage(random));
            grid.add(button, i % GRID_SIZE, i / GRID_SIZE);
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setTitle("Adventskalender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setPrefSize(100, 100); // Einheitliche Größe
        button.setStyle(
                "-fx-background-color: #f4a261; " +
                        "-fx-border-color: #2a9d8f; " +
                        "-fx-border-width: 2px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold;"
        );

        // Hover-Effekt
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #e76f51; " +
                        "-fx-border-color: #2a9d8f; " +
                        "-fx-border-width: 2px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #f4a261; " +
                        "-fx-border-color: #2a9d8f; " +
                        "-fx-border-width: 2px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold;"
        ));
    }

    private void openPuzzleOrImage(Random random) {
        Stage popup = new Stage();
        popup.setTitle("Überraschung!");

        boolean showPuzzle = random.nextBoolean();

        if (showPuzzle && !puzzles.isEmpty()) {
            // Wähle ein zufälliges Rätsel und entferne es aus der Liste
            String puzzle = puzzles.remove(random.nextInt(puzzles.size()));
            Label puzzleLabel = new Label(puzzle);
            puzzleLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px; -fx-text-alignment: center;");
            popup.setScene(new Scene(new VBox(puzzleLabel), 800, 200));
        } else if (showPuzzle) {
            // Zeige eine Nachricht, wenn keine Rätsel mehr übrig sind
            Label messageLabel = new Label("Alle Rätsel wurden bereits verwendet!");
            messageLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px; -fx-text-alignment: center;");
            popup.setScene(new Scene(new VBox(messageLabel), 800, 200));
        } else {
            // Zufälliges Bild anzeigen
            String[] imagePaths = {
                    "/images/image1.png",
                    "/images/image2.png",
                    "/images/image3.png",
                    "/images/image4.png",
                    "/images/image5.png"
            };
            Image image = new Image(getClass().getResourceAsStream(imagePaths[random.nextInt(imagePaths.length)]));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);
            popup.setScene(new Scene(new VBox(imageView), 500, 500));
        }

        popup.show();
    }
}
