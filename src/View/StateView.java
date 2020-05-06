/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author Augusto Souza 2017376
 */
public class StateView {
    private BorderPane generate_pane;
    private BorderPane generate_group_pane;

    private Label title_label;
    private HBox titleBox;
    
    private ListView<String> log_view = new ListView<String>();
    
    private VBox contentBox;
    
    private ImageView processing;
    
    private static StateView instance = new StateView();
    
    public StateView(){
        initView();
    }
    //Singleton pattern
    public static StateView getInstance(){
        return instance;
    }
    public void initView() {
        initTitleArea();
        processing = new ImageView();
        processing.setImage(LogoManager.getInstance().getLoading());
        processing.autosize();
        processing.setFitWidth(20);
        processing.setFitHeight(20);
        processing.setVisible(false);
                
        initLogArea();
        
        
        generate_group_pane = new BorderPane();
        generate_group_pane.setId("main_area");
        generate_group_pane.setCenter(contentBox);

        generate_pane = new BorderPane();
        generate_pane.setId("black_area");
        BorderPane.setMargin(titleBox, new Insets(5, 0, 0, 0));
        generate_pane.setTop(titleBox);
        generate_pane.setCenter(generate_group_pane);
        generate_pane.requestFocus();
    }
    private void initTitleArea() {
        title_label = new Label("SIMULATION");
        title_label.setId("title_label");
        titleBox = new HBox();
        titleBox.setSpacing(25);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title_label);
    }
    private void initLogArea(){
        contentBox = new VBox();
        contentBox.setSpacing(10);
        contentBox.setAlignment(Pos.CENTER);
        
        HBox topBox = new HBox();
        Label title_label = new Label("Simulation Log");
        title_label.setId("roundLabel3");
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        topBox.setAlignment(Pos.CENTER);
        HBox.setMargin(title_label, new Insets(10, 15, 0, 15));
        HBox.setMargin(processing, new Insets(10, 15, 0, 5));
        topBox.getChildren().addAll(title_label, processing, region);
        
        log_view.setId("log_area");
        log_view.setEditable(false);
        log_view.setPrefSize(MainView.getInstance().rightArea.getPrefWidth(), MainView.getInstance().rightArea.getPrefHeight());
        VBox.setMargin(log_view, new Insets(0, 15, 20, 15));
        
        contentBox.getChildren().addAll(topBox, log_view);
    }
    public BorderPane getBoard(){
        return generate_pane;
    }
    public ListView getLogArea(){
        return log_view;
    }
    public ImageView getProcessing(){
        return processing;
    }
}
