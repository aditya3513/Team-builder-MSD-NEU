/**
 * 
 */
package com.neu.teambuilder.resources;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

/**
 * @author ideepakkrishnan
 *
 */
public class Util {
	
	private static Random random = new Random(System.currentTimeMillis());
	
	/**
	 * Generates a random Long number and returns it to the
	 * caller. The seed value is System.currentTimeMillis().
	 * @return the random number
	 */
	public static Long generateRandomNumber() {
		return random.nextLong();
	}
	
	/**
	 * Generates an ImageView object using the image stored
	 * at the path passed in as argument
	 * @param path The path of the image file
	 * @param height Height of the object to be created
	 * @param width Width of the object to be created
	 * @return The generated ImageView object
	 */
	public static ImageView createImageView(
			String path, double height, double width) {
		ImageView newImgView = new ImageView(path);
		newImgView.setFitWidth(height);
		newImgView.setFitHeight(width);
		
		return newImgView;
	}
	
	/**
	 * Generates a graphic button object using the image
	 * at the path passed in as argument
	 * @param handler Action event handler
	 * @param image Path of the image to be used as content
	 * @param height The button height
	 * @param width The button width
	 * @return The generated Button object
	 */
	public static Button createImageButton(
			EventHandler<ActionEvent> handler, ImageView image,
			double height, double width) {
		Button newBtn = new Button();
		newBtn.setGraphic(image);
		newBtn.setMinHeight(height);
		newBtn.setMinWidth(width);
		newBtn.setMaxHeight(height);
		newBtn.setMaxWidth(width);
		newBtn.setPrefHeight(height);
		newBtn.setPrefWidth(width);
		newBtn.setBackground(Background.EMPTY);
		newBtn.setOnAction(handler);
		
		return newBtn;
	}

}
