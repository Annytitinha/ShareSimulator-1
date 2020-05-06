/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulate;

import View.LogoManager;
import View.Resource;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import View.MainView;

/**
 *
 * @author Anaiana Ramos 2016306
 */
public class Main extends Application {
    
    public static Stage childClassStage;
   
    @Override
    public void start(Stage primaryStage) {
        initWindow();
    }
    public static void initWindow(){
        Resource.initWindowSize();
        
        childClassStage = new Stage();
        MainView.getInstance().initView();
                
        childClassStage.setScene(MainView.getInstance().root_ui_scene);
        childClassStage.setMaximized(true);
        childClassStage.initStyle(StageStyle.DECORATED);
        childClassStage.getIcons().add(LogoManager.getInstance().getIcon());
        childClassStage.setTitle("Simulation");
        childClassStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
        childClassStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
