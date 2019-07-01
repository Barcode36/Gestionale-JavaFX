package controller;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerVisualizzatoreImmagini 
{
	private ArrayList<Image> immagini = new ArrayList<Image>();
	int posizione = 0;
	
	@FXML
    private JFXButton indietro;

    @FXML
    private JFXButton avanti;

    @FXML
    private ImageView imageView;
    
    public void setImmagini(ListView<ImageView> immagini)
    {
    	imageView.setPreserveRatio(true);
    	imageView.setCache(true);
    	for(ImageView v : immagini.getItems())
    	{
    		this.immagini.add(v.getImage());
    	}
    	
    	if(this.immagini.size() > 0)
    	{
    		imageView.setImage(this.immagini.get(0));
    	}
    }
    
    @FXML
    void avantiPremuto(ActionEvent event) 
    {
    	posizione++;
    	if(posizione >= immagini.size()) posizione = immagini.size() - 1;
    	else imageView.setImage(immagini.get(posizione));
    }

    @FXML
    void indietroPremuto(ActionEvent event) 
    {
    	posizione--;
    	if(posizione < 0) posizione = 0;
    	else imageView.setImage(immagini.get(posizione));
    }
}
