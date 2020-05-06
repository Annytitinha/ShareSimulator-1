/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Augusto Souza 2017376
 */
public class MainView {
    
    private static MainView mainview = new MainView();
    
    public BorderPane root_ui_pane = new BorderPane();
    public Scene root_ui_scene ;
    public BorderPane root_pane;
    public BorderPane sideArea;
    public BorderPane rightArea;
    
    int left_margin = 20;
    int right_margin = 20;
    
    public static MainView getInstance(){
        return mainview;
    }
    public void initView() {
        sideArea = new BorderPane();
        sideArea.setPrefWidth(Resource.SIDE_AREA_WIDTH);
        sideArea.setPrefHeight(Resource.WINDOW_HEIGHT - 100);
        sideArea.setId("sideArea");   
        sideArea.setCenter(ButtonView.getInstance().BtnView);
        
        rightArea = new BorderPane();
        rightArea.setPrefWidth(Resource.RIGHT_AREA_WIDTH - 40);
        rightArea.setPrefHeight(Resource.WINDOW_HEIGHT - 100);
        rightArea.setId("rightArea");
        
        rightArea.setCenter(StateView.getInstance().getBoard());
        
        root_pane = new BorderPane();
        root_pane.setId("whole_area");
        BorderPane.setMargin(sideArea, new Insets(5, 0, 5, 5));
        BorderPane.setMargin(rightArea, new Insets(5, 5, 5, 0));
       
        root_pane.setLeft(sideArea);
        root_pane.setCenter(rightArea);
        
        root_ui_pane = new BorderPane();
        root_ui_scene = new Scene(root_pane);
        root_ui_scene.getStylesheets().add(getClass().getClassLoader().getResource("resource/style/rootStyles.css").toExternalForm());
    }
}
