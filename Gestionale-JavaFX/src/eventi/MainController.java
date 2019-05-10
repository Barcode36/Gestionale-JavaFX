package eventi;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import logica.*;

public class MainController
{
	 @FXML
	 private TextArea textAreaGioielli;
	 
	 @FXML
	 private ListView<String> listView;
	 
	 public void stampaGioielli(String testo)
	 {
		 textAreaGioielli.appendText(testo+"\n");
		 
	 }
	 
	 public void showListView(ArrayList<Gioiello> gioielli)
	 {
//		 gioielli.sort(new Comparator<String>() 
//		 {
//
//			@Override
//			public int compare(String o1, String o2) 
//			{
//				return o1.compareToIgnoreCase(o2);
//			}
//		});
		 
		 for(Gioiello g : gioielli)
		 {
			 listView.getItems().add(g.getNomeGioiello());
		 }
		 
	 }
}
