/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Augusto Souza 2017376
 */
public class ResultView {
    private BorderPane generate_pane;
    private BorderPane generate_group_pane;

    private Label title_label;
    private HBox titleBox;
    
    private TextArea result_view = new TextArea();
    
    private VBox contentBox;
    
    
    private static ResultView instance = new ResultView();
    
    public ResultView(){
        initView();
    }
    //Singleton pattern
    public static ResultView getInstance(){
        return instance;
    }
    public void initView() {
        initTitleArea();
      
        initResultArea();
        
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
    private void initResultArea(){
        contentBox = new VBox();
        contentBox.setSpacing(10);
        contentBox.setAlignment(Pos.CENTER);
        
        result_view.setId("log_area");
        result_view.setEditable(false);
        result_view.setPrefSize(MainView.getInstance().rightArea.getPrefWidth(), MainView.getInstance().rightArea.getPrefHeight());
        VBox.setMargin(result_view, new Insets(20, 15, 20, 15));
        
        contentBox.getChildren().addAll(result_view);
    }
    public BorderPane getBoard(){
        return generate_pane;
    }
    public TextArea getResultArea(){
        return result_view;
    }
    public Label getTitle(){
        return title_label;
    }
}
