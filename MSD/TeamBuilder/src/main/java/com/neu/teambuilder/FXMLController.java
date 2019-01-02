package com.neu.teambuilder;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.HiddenSidesPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {
	
	@FXML
	private HiddenSidesPane pane;
    
    @FXML
    private Label pinLabel;
    
    @FXML
    private void handleMouseClicked(MouseEvent event) {
    	if (pane.getPinnedSide() != null) {
	      pinLabel.setText("(unpinned)");
	      pane.setPinnedSide(null);
	    } else {
	      pinLabel.setText("(pinned)");
	      pane.setPinnedSide(Side.TOP);
	    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
