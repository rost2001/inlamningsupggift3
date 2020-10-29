package a15Game;

/**
 * En klass som symboliserar ett 15 spel
 */
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class A15Game extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("15 Game");
		VBox root = new VBox();
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(true);

		ArrayList<Brick> bricks = new ArrayList<Brick>(); // alla brickor i spelet
		ArrayList<Integer> columnIndex = new ArrayList<Integer>(); // index för columner
		ArrayList<Integer> rowIndex = new ArrayList<Integer>(); // index för rader

		Button btn = new Button("New Game!");
		final Label winLabel = new Label();

		// spelet startar om när man trycker på knappen
		btn.setOnAction(e -> {
			this.start(primaryStage);
		});

		// loop som lägger in alla brickorna med nr i listan
		for (int i = 1; i < 16; i++) {
			Brick b = new Brick(Integer.toString(i));
			bricks.add(b);
		}
		bricks.add(new Brick(" ")); // lägger till sista tomma brickan
		Collections.shuffle(bricks); // blandar brickorna i spelet

		// loop som lägger på en event listener på alla brickor
		for (int i = 0; i < bricks.size(); i++) {
			Brick b = bricks.get(i);
			b.label.setOnMouseClicked(e -> {

				int k = bricks.indexOf(b); // platsen i listan för att kunna jämföra med grannarna
				if (k + 1 >= 0 && k + 1 < bricks.size() && GridPane.getRowIndex(bricks.get(k).label) == GridPane
						.getRowIndex(bricks.get((k) + 1).label)) { // håller sig inom gränsen och på samma grid row
					if (bricks.get(k + 1).label.getText().equals(" ")) {
						bricks.get(k + 1).label.setText(bricks.get(k).label.getText());
						bricks.get(k).label.setText(" ");
					}
				}
				if (k - 1 >= 0 && k - 1 < bricks.size() && GridPane.getRowIndex(bricks.get(k).label) == GridPane
						.getRowIndex(bricks.get((k) - 1).label)) {
					if (bricks.get(k - 1).label.getText().equals(" ")) {
						bricks.get(k - 1).label.setText(bricks.get(k).label.getText());
						bricks.get(k).label.setText(" ");
					}
				}
				if (k + 4 >= 0 && k + 4 < bricks.size()) {
					if (bricks.get(k + 4).label.getText().equals(" ")) {
						bricks.get(k + 4).label.setText(bricks.get(k).label.getText());
						bricks.get(k).label.setText(" ");
					}
				}
				if (k - 4 >= 0 && k - 4 < bricks.size()) {
					if (bricks.get(k - 4).label.getText().equals(" ")) {
						bricks.get(k - 4).label.setText(bricks.get(k).label.getText());
						bricks.get(k).label.setText(" ");
					}
				}
				// sätter win label om alla ligger rätt
				if (checkWin(bricks)) {
					winLabel.setText("Win!");
					winLabel.setFont(new Font("Arial", 50));
				}
			});
		}

		// lägger in alla column och row celler i 2 listor
		for (int i = 0; i < bricks.size(); i++) { // i för rader
			for (int j = 0; j < 4; j++) { // j för columner
				columnIndex.add(j);
				rowIndex.add(i);
			}
		}

		// två loopar som sätter storleken på alla celler i gridden
		for (int i = 0; i < 4; i++) {
			ColumnConstraints column = new ColumnConstraints(150);
			grid.getColumnConstraints().add(column);
		}
		for (int i = 0; i < 4; i++) {
			RowConstraints row = new RowConstraints(150);
			grid.getRowConstraints().add(row);
		}

		// sätter ut alla bricks i gridden och x, y positionerna
		for (int i = 0; i < bricks.size(); i++) {
			grid.add(bricks.get(i).label, columnIndex.get(i), rowIndex.get(i));
			bricks.get(i).setColumnIndex(columnIndex.get(i));
			bricks.get(i).setRowIndex(rowIndex.get(i));
		}

		grid.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		root.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		root.getChildren().addAll(grid, btn, winLabel);
		root.setAlignment(Pos.CENTER);// knappen i mitten
		Scene scene = new Scene(root, 600, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Metod som kollar om spelet är klart
	 * 
	 * @param bricks arrayen med alla brickor
	 * @return om alla ligger i ordning
	 */
	private boolean checkWin(ArrayList<Brick> bricks) {
		String s = "";
		for (int i = 0; i < bricks.size(); i++) {
			s += bricks.get(i).label.getText();
		}
		if (s.equalsIgnoreCase("123456789101112131415 ")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		launch(args);
	}
}