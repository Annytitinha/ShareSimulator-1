/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 *
 * @author Augusto Souza 2017376
 */
public class LogoManager {
    private static LogoManager manager = new LogoManager();
    
    public static LogoManager getInstance(){
        return manager;
    }
    public Image getIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("resource/image/icon.png");
        return new Image(icon_stream);
    }
    public Image getLogo(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("resource/image/logo.png");
        return new Image(icon_stream);
    }
    public Image getLoading(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("resource/image/loading.gif");
        return new Image(icon_stream);
    }
    public Image getCloseIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("resource/image/close_icon.png");
        return new Image(icon_stream);
    }
    public String getURL(String location){
        String path = getClass().getClassLoader().getResource(location).toExternalForm();
        return path;
    }
}
