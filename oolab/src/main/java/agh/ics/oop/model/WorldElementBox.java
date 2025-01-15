package agh.ics.oop.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class WorldElementBox extends VBox{
    public WorldElementBox(WorldElement element) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/" + element.getResourceName())));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Label positionLabel = new Label(element.getPosition().toString());
        positionLabel.setStyle("-fx-font-size: 10; -fx-alignment: center;");

        this.setSpacing(5);
        this.setStyle("-fx-alignment: center;");
        this.getChildren().addAll(imageView, positionLabel);
    }
}
