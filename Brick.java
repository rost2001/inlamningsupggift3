
package a15Game;

/**
 * Klass som representerar en bricka i spelet
 */
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Brick {
	final Label label;
	int columnIndex; // används i spelet så brickorna hamnar rätt
	int rowIndex;

	// konstruktor som fixar alla labels
	public Brick(String nr) {
		this.label = new Label(nr);
		this.label.setFont(new Font("Arial", 100));
		this.label.setMaxWidth(Double.MAX_VALUE);
		this.label.setAlignment(Pos.CENTER);
	}

	public void setColumnIndex(int i) {
		this.columnIndex = i;
	}

	public void setRowIndex(int i) {
		this.rowIndex = i;
	}
}
