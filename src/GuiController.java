import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class GuiController {

	@FXML
    public Button btn1;
	
	@FXML
    public Button btn2;
	
	@FXML
    public Button btn3;
	
	@FXML
    public Button btn4;
	
	@FXML
    public Button btn5;
	
	@FXML
    public Button btn6;
	
	@FXML
    public Button btn7;
	
	@FXML
    public Button btn8;
	
	@FXML
    public Button btn9;
	
	@FXML
    public Button btn10;
	
	@FXML
    private Button startButton;

    @FXML
    public TableView<Message> tab1;

    @FXML
    private TextArea processus1;

    @FXML
    public TableView<Message> tab2;

    @FXML
    private TextArea processus2;

    @FXML
    public TableView<Message> tab3;

    @FXML
    private TextArea processus3;

    @FXML
    public TableView<Message> tab4;

    @FXML
    private TextArea processus4;

    @FXML
    public TableView<Message> tab5;

    @FXML
    private TextArea processus5;

    @FXML
    public TableView<Message> tab6;

    @FXML
    private TextArea processus6;

    @FXML
    public TableView<Message> tab7;

    @FXML
    private TextArea processus7;

    @FXML
    public TableView<Message> tab8;

    @FXML
    private TextArea processus8;

    @FXML
    public TableView<Message> tab9;

    @FXML
    private TextArea processus9;

    @FXML
    public TableView<Message> tab10;

    @FXML
    private TextArea processus10;
    
    public boolean block = false;
    
    private List<Thread> threads = new ArrayList<Thread>();
    private List<Processus> processus = new ArrayList<Processus>();
    
    @SuppressWarnings("deprecation")
	@FXML
    void startButton(ActionEvent event) throws Exception {
    	if (startButton.getText().equals("Start")) {
			for (int i=0; i<10; i++) {
				Processus p = new Processus(i);
				processus.add(p);
				p.setGuiController(this);
				Thread.sleep(1000);
				Thread th = new Thread(p);
				threads.add(th);
				th.start();
			}
			startButton.setText("Stop");
    	}
    	else {
    		for (int i=0; i<10; i++) threads.get(i).stop();
    		startButton.setDisable(true);
    	}
    }
    
    @FXML
    void log1(ActionEvent event) {
    	if (tab1.isVisible()) {
    		processus1.setText(processus.get(0).getLog());
    		tab1.setVisible(false);
    		processus1.setVisible(true);
    	}
    	else {
    		processus1.setVisible(false);
    		tab1.setVisible(true);
    	}
    }

    @FXML
    void log10(ActionEvent event) {
    	if (tab10.isVisible()) {
    		processus10.setText(processus.get(0).getLog());
    		tab10.setVisible(false);
    		processus10.setVisible(true);
    	}
    	else {
    		processus10.setVisible(false);
    		tab10.setVisible(true);
    	}
    }

    @FXML
    void log2(ActionEvent event) {
    	if (tab2.isVisible()) {
    		processus2.setText(processus.get(0).getLog());
    		tab2.setVisible(false);
    		processus2.setVisible(true);
    	}
    	else {
    		processus2.setVisible(false);
    		tab2.setVisible(true);
    	}
    }

    @FXML
    void log3(ActionEvent event) {
    	if (tab3.isVisible()) {
    		processus3.setText(processus.get(0).getLog());
    		tab3.setVisible(false);
    		processus3.setVisible(true);
    	}
    	else {
    		processus3.setVisible(false);
    		tab3.setVisible(true);
    	}
    }

    @FXML
    void log4(ActionEvent event) {
    	if (tab4.isVisible()) {
    		processus4.setText(processus.get(0).getLog());
    		tab4.setVisible(false);
    		processus4.setVisible(true);
    	}
    	else {
    		processus4.setVisible(false);
    		tab4.setVisible(true);
    	}
    }

    @FXML
    void log5(ActionEvent event) {
    	if (tab5.isVisible()) {
    		processus5.setText(processus.get(0).getLog());
    		tab5.setVisible(false);
    		processus5.setVisible(true);
    	}
    	else {
    		processus5.setVisible(false);
    		tab5.setVisible(true);
    	}
    }

    @FXML
    void log6(ActionEvent event) {
    	if (tab6.isVisible()) {
    		processus6.setText(processus.get(0).getLog());
    		tab6.setVisible(false);
    		processus6.setVisible(true);
    	}
    	else {
    		processus6.setVisible(false);
    		tab6.setVisible(true);
    	}
    }

    @FXML
    void log7(ActionEvent event) {
    	if (tab7.isVisible()) {
    		processus7.setText(processus.get(0).getLog());
    		tab7.setVisible(false);
    		processus7.setVisible(true);
    	}
    	else {
    		processus7.setVisible(false);
    		tab7.setVisible(true);
    	}
    }
    
    @FXML
    void log8(ActionEvent event) {
    	if (tab8.isVisible()) {
    		processus8.setText(processus.get(0).getLog());
    		tab8.setVisible(false);
    		processus8.setVisible(true);
    	}
    	else {
    		processus8.setVisible(false);
    		tab8.setVisible(true);
    	}
    }

    @FXML
    void log9(ActionEvent event) {
    	if (tab9.isVisible()) {
    		processus9.setText(processus.get(0).getLog());
    		tab9.setVisible(false);
    		processus9.setVisible(true);
    	}
    	else {
    		processus9.setVisible(false);
    		tab9.setVisible(true);
    	}
    }
    
}
