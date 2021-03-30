import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {

    @FXML
    private StackPane parent_StackPane;

    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private Button btn_load;

    public void initialize() {
        System.out.println("initialized");

        // setup button
        btn_load.setOpacity(0.2f);
        CornerRadii btnCorner = new CornerRadii(25f);
        Background btnBackground = new Background(new BackgroundFill(Color.DODGERBLUE, btnCorner, Insets.EMPTY));
        btn_load.setBackground(btnBackground);
        btn_load.setTextFill(Color.WHITE);

        String defaultFontFamily = btn_load.getFont().getFamily();
        double defaultFontSize = btn_load.getFont().getSize();
        btn_load.setFont(Font.font(defaultFontFamily, FontWeight.BOLD, defaultFontSize));

        // animation on values
        Timeline timelineBtn = new Timeline();
        KeyValue kv1 = new KeyValue(btn_load.opacityProperty(), 1, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.seconds(2), kv1);
        timelineBtn.getKeyFrames().add(kf);

        ColorAdjust colorAdjAnimated = new ColorAdjust();
        colorAdjAnimated.setHue(0.0f);
        KeyValue kvC1 = new KeyValue(colorAdjAnimated.hueProperty(), 1f, Interpolator.LINEAR);
        KeyValue kvC2 = new KeyValue(colorAdjAnimated.hueProperty(), 0.2f, Interpolator.EASE_IN);
        KeyValue kvC3 = new KeyValue(colorAdjAnimated.hueProperty(), 0.8f, Interpolator.EASE_OUT);
        KeyFrame kfC1 = new KeyFrame(Duration.seconds(3), kvC1);
        KeyFrame kfC2 = new KeyFrame(Duration.seconds(6), kvC2);
        KeyFrame kfC3 = new KeyFrame(Duration.seconds(9), kvC3);
        timelineBtn.getKeyFrames().add(kfC1);
        timelineBtn.getKeyFrames().add(kfC2);
        timelineBtn.getKeyFrames().add(kfC3);

        timelineBtn.play();

        btn_load.setEffect(colorAdjAnimated);

    }

    @FXML
    void handleLoad(ActionEvent event) throws IOException {
        System.out.println("click, load...");

        // setup next scene and current scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene1.fxml"));
        Parent nextSceneRoot = fxmlLoader.load();
        Scene thisScene = btn_load.getScene();

        nextSceneRoot.translateYProperty().set(thisScene.getHeight());
        parent_StackPane.getChildren().add(nextSceneRoot);

        // timeline and key-value, key-frame
        Timeline transitionTimeline = new Timeline();
        KeyValue keyValue = new KeyValue(nextSceneRoot.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.6), keyValue);
        transitionTimeline.getKeyFrames().add(keyFrame);
        transitionTimeline.setOnFinished(e -> {
            parent_StackPane.getChildren().remove(root_AnchorPane);
        });
        transitionTimeline.play();
    }

    @FXML
    void handleMouseEnter(MouseEvent event) {
        System.out.println("mouse enter");

        ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.2), btn_load);
        scaleT.setFromX(1f);
        scaleT.setFromY(1f);
        scaleT.setToX(1.2f);
        scaleT.setToY(1.2f);
        scaleT.setInterpolator(Interpolator.EASE_IN);

        FadeTransition fadeT = new FadeTransition(Duration.seconds(0.2), btn_load);
        fadeT.setFromValue(0.2);
        fadeT.setToValue(1);

        ParallelTransition allTransition = new ParallelTransition(btn_load, scaleT, fadeT);
        allTransition.play();
    }

    @FXML
    void handleMouseExit(MouseEvent event) {
        System.out.println("mouse exit");

        ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.2), btn_load);
        scaleT.setFromX(1.2f);
        scaleT.setFromY(1.2f);
        scaleT.setToX(1f);
        scaleT.setToY(1f);
        scaleT.setInterpolator(Interpolator.EASE_IN);

        FadeTransition fadeT = new FadeTransition(Duration.seconds(0.2), btn_load);
        fadeT.setFromValue(1);
        fadeT.setToValue(0.2);

        ParallelTransition allTransition = new ParallelTransition(btn_load, scaleT, fadeT);
        allTransition.play();
    }

    @FXML
    void handleMousePressed(MouseEvent event) {
        System.out.println("mouse pressed");

        ColorAdjust colorAdj = new ColorAdjust();
        colorAdj.setBrightness(0.5);

        btn_load.setEffect(colorAdj);
        btn_load.setScaleX(1.1f);
        btn_load.setScaleY(1.1f);

    }

    @FXML
    void handleMouseReleased(MouseEvent event) {
        System.out.println("mouse released");

        ColorAdjust colorAdj = new ColorAdjust();
        colorAdj.setBrightness(0.0);

        btn_load.setEffect(colorAdj);
        btn_load.setScaleX(1.2f);
        btn_load.setScaleY(1.2f);

    }

}