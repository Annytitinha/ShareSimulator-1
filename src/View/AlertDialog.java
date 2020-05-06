/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Simulate.Main;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Augusto Souza 2017376
 */
public class AlertDialog {
    private Stage confirm_stage;
    private BorderPane confirm_pane = new BorderPane();
    private Label title_label = new Label("ALERT");
    private Label alert_label = new Label();
    private ImageView close_area;
    private Button ok_btn;
    double xOffset = 0;
    double yOffset = 0;
    
    private static AlertDialog dlg = new AlertDialog();
    
    public static AlertDialog getInstance(){
        return dlg;
    }
    public AlertDialog(){
        initView();
    }
    private void initView() {
        title_label.setId("login_title_label");
        close_area = new ImageView(LogoManager.getInstance().getCloseIcon());
        close_area.setFitWidth(20);
        close_area.setFitHeight(20);
        close_area.setCursor(Cursor.HAND);
        close_area.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    confirm_stage.close();
                }
            }
        });
        HBox topBox = new HBox();
        Region top_region = new Region();
        HBox.setHgrow(top_region, Priority.ALWAYS);
        topBox.getChildren().addAll(title_label, top_region, close_area);
        initBtns();

        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER_RIGHT);
        btnBox.setSpacing(10);
        HBox.setMargin(ok_btn, new Insets(15, 0, 0, 0));
        btnBox.getChildren().addAll(ok_btn);

        //init sign in area
        GridPane alarm_pane = new GridPane();
        alarm_pane.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-border-width: 5 0 0 0;"
                + "-fx-border-color: #00dcff;");
        alarm_pane.setPadding(new Insets(25, 35, 25, 35));
        alarm_pane.setHgap(10);
        alarm_pane.setVgap(15);
        alert_label.setId("username_label");
        alert_label.setPadding(new Insets(0, 30, 0, 30));

        alarm_pane.add(alert_label, 0, 0, 1, 1);

        alarm_pane.setAlignment(Pos.CENTER);

        GridPane signGroup = new GridPane();
        signGroup.setHgap(20);
        signGroup.setVgap(5);

        signGroup.add(topBox, 0, 0, 1, 1);
        signGroup.add(alarm_pane, 0, 1, 1, 1);
        signGroup.add(btnBox, 0, 2, 1, 1);

        signGroup.setAlignment(Pos.CENTER);

        VBox registerBox = new VBox();
        registerBox.setSpacing(10);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.getChildren().addAll(signGroup);
        
        StackPane confirm_stack = new StackPane();
        confirm_stack.setId("main_register_area");
        StackPane.setMargin(registerBox, new Insets(30, 30, 30, 30));
        confirm_stack.getChildren().addAll(registerBox);
        DropShadow e = new DropShadow();
        e.setWidth(5);
        e.setHeight(5);
        e.setOffsetX(3);
        e.setOffsetY(3);
        e.setBlurType(BlurType.THREE_PASS_BOX);
        e.setRadius(0);
        confirm_stack.setEffect(e);
        
        //confirm_pane.setId("main_area");
        BorderPane.setMargin(confirm_stack, new Insets(0, 3, 3, 0));
        confirm_pane.setCenter(confirm_stack);
        
        Scene scene = new Scene(confirm_pane);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("resource/style/customStyles.css").toExternalForm());
        scene.setOnMousePressed(event -> {
            xOffset = confirm_stage.getX() - event.getScreenX();
            yOffset = confirm_stage.getY() - event.getScreenY();
        });
        //Lambda mouse event handler
        scene.setOnMouseDragged(event -> {
            confirm_stage.setX(event.getScreenX() + xOffset);
            confirm_stage.setY(event.getScreenY() + yOffset);
        });
        confirm_stage = new Stage();
        confirm_stage.initStyle(StageStyle.UNDECORATED);
        confirm_stage.setScene(scene);
        confirm_stage.setResizable(false);

        confirm_stage.initOwner(Main.childClassStage);
        confirm_stage.initModality(Modality.WINDOW_MODAL);
        
        ChangeListener<Number> widthListener = (observable, oldValue, newValue) -> {
            double stageWidth = newValue.doubleValue();
            confirm_stage.setX(Main.childClassStage.getX() + Main.childClassStage.getWidth() / 2 - stageWidth / 2);
        };
        ChangeListener<Number> heightListener = (observable, oldValue, newValue) -> {
            double stageHeight = newValue.doubleValue();
            confirm_stage.setY(Main.childClassStage.getY() + Main.childClassStage.getHeight() / 2 - stageHeight / 2);
        };

        confirm_stage.widthProperty().addListener(widthListener);
        confirm_stage.heightProperty().addListener(heightListener);

        confirm_stage.setOnShown(event -> {
            confirm_stage.widthProperty().removeListener(widthListener);
            confirm_stage.heightProperty().removeListener(heightListener);
        });
    }

    public void show(String message) {
        this.alert_label.setText(message);
        confirm_stage.showAndWait();
    }
    private void initBtns() {
        ok_btn = new Button("OK");
        ok_btn.setId("loginnbtn");
        ok_btn.setPadding(new Insets(0, 30, 0, 30));
        ok_btn.setPrefHeight(37);
        ok_btn.setCursor(Cursor.HAND);

        ok_btn.setOnAction((evt) -> {
            confirm_stage.close();
        });
        ok_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirm_stage.close();
            }
        });
    }
}
