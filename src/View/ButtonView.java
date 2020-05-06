/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Simulate.Simulation;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Augusto Souza 2017376
 */
public class ButtonView {

    public ScrollPane BtnView;
    public VBox mainBtnGroup;

    private static Button start_btn;
    private static Button view_result_btn;
    private static Button exit_btn;

    public ImageView logo_view;

    public double logo_height;

    private static ButtonView btn_view = new ButtonView();
    
    public static ButtonView getInstance(){
        return btn_view;
    }
    
    public ButtonView(){
        initView();
    }
    private void initView() {
        BtnView = new ScrollPane();
        BtnView.setFitToWidth(true);
        BtnView.setFitToHeight(true);
        BtnView.setStyle("-fx-background: #44dbbd;-fx-border-color: #44dbbd;");
        BtnView.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        BtnView.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        initLogoView();
        initBtns();
        BtnView.setContent(mainBtnGroup);
    }

    private void initLogoView() {
        Image logo_image =LogoManager.getInstance().getLogo();
        double height = logo_image.getHeight();
        double width = Resource.SIDE_AREA_WIDTH / 2;
        logo_height = height * ((double) width / (double) logo_image.getWidth());
        logo_view = new ImageView(logo_image);
        logo_view.setFitWidth(width);
        logo_view.setFitHeight(logo_height);
    }

    private void initBtns() {
        start_btn = new Button("START SIMULATION");
        setMainBtnProperty(start_btn);

        view_result_btn = new Button("SIMUATION RESULT");
        setMainBtnProperty(view_result_btn);
      
        exit_btn = new Button("EXIT");
        setMainBtnProperty(exit_btn);

        start_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    initStyleAllMainBtns();
                    changeStyleAllMainBtns(1);
                    MainView.getInstance().rightArea.setCenter(StateView.getInstance().getBoard());
                    Simulation.getInstance().simuationAsBackgroud();
                }
            }
        });
        view_result_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    initStyleAllMainBtns();
                    changeStyleAllMainBtns(2);
                    if (!Simulation.getInstance().IsDone()) {
                        return;
                    }
                    String report = Simulation.getInstance().getReport();
                    MainView.getInstance().rightArea.setCenter(ResultView.getInstance().getBoard());
                    ResultView.getInstance().getTitle().setText("SIMUATION REPORT");
                    ResultView.getInstance().getResultArea().setText(report);
                }
            }
        });
        
        exit_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    System.exit(0);
                }
            }
        });

        Separator sep = new Separator();
        sep.setId("separator2");
        mainBtnGroup = new VBox();
        mainBtnGroup.setSpacing(5);
        mainBtnGroup.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(logo_view, new Insets(10, 0, 0, 0));
        VBox.setMargin(start_btn, new Insets(5, 0, 0, 0));
        mainBtnGroup.getChildren().addAll(logo_view, sep, start_btn, view_result_btn, exit_btn);
    }

    private void setMainBtnProperty(Button button) {
        button.setCursor(Cursor.HAND);
        button.setWrapText(true);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setId("main_btn");
        button.setPrefWidth(Resource.SIDE_AREA_WIDTH - 10);
    }

    private void initStyleAllMainBtns() {
        start_btn.setId("main_btn");
        view_result_btn.setId("main_btn");
        exit_btn.setId("main_btn");
    }

    private void changeStyleAllMainBtns(int index) {
        initStyleAllMainBtns();
        if (index == 1) {
            start_btn.setId("main_btn_select");
        } else if (index == 2) {
            view_result_btn.setId("main_btn_select");
        } else if (index == 3) {
            exit_btn.setId("main_btn_select");
        }
    }

    public static void disableBtns() {
        start_btn.setDisable(true);
        view_result_btn.setDisable(true);
        exit_btn.setDisable(true);
    }

    public static void enableBtns() {
        start_btn.setDisable(false);
        view_result_btn.setDisable(false);
        exit_btn.setDisable(false);
    }
}
