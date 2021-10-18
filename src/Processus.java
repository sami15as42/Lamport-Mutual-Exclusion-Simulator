import java.util.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.Math;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class Processus implements Runnable {
	
	private int horloge;
	private List<Message> file;
	private int site; 
	
	
	private GuiController ctrl = null;
	private String log = ""; 
	private int nbAcq;
	
	public Processus(int site) {
		super();
		this.horloge = 0;
		this.file = new ArrayList<Message>();
		this.site = site;
		for (int j=0; j<10; j++) {
			this.file.add(new Message("rel", 0, j));
		}
	}
	
	public void setGuiController(GuiController ctrl) {
		this.ctrl = ctrl;
		switch(this.site) {
			case 0:
				this.ctrl.tab1.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab1.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab1.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab1.getItems().add(this.file.get(i));
				break;
			case 1:
				this.ctrl.tab2.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab2.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab2.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab2.getItems().add(this.file.get(i));
				break;
			case 2:
				this.ctrl.tab3.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab3.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab3.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab3.getItems().add(this.file.get(i));
				break;
			case 3:
				this.ctrl.tab4.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab4.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab4.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab4.getItems().add(this.file.get(i));
				break;
			case 4:
				this.ctrl.tab5.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab5.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab5.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab5.getItems().add(this.file.get(i));
				break;
			case 5:
				this.ctrl.tab6.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab6.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab6.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab6.getItems().add(this.file.get(i));
				break;
			case 6:
				this.ctrl.tab7.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab7.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab7.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab7.getItems().add(this.file.get(i));
				break;
			case 7:
				this.ctrl.tab8.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab8.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab8.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab8.getItems().add(this.file.get(i));
				break;
			case 8:
				this.ctrl.tab9.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab9.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab9.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab9.getItems().add(this.file.get(i));
				break;
			case 9:
				this.ctrl.tab10.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("type"));
				this.ctrl.tab10.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("horloge"));
				this.ctrl.tab10.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("site"));
				for (int i=0; i<10; i++) this.ctrl.tab10.getItems().add(this.file.get(i));
				break;
			default:
				break;
		}	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized void execute() throws Exception {
		System.out.println(this.site);
		Thread.sleep(10000-this.site*1000);
		int port = 6800 + this.site;
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
        serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        serverSocketChannel.bind(new InetSocketAddress("localhost", port));
        serverSocketChannel.configureBlocking(false);
        System.out.println("Processus " + this.site + " started");//serverSocketChannel
        while(true) {
        	if (!this.isDemandeSC()) demandeSC();
        	
        	while(true) {
	        	SocketChannel socketChannel = serverSocketChannel.accept();
	        	if(socketChannel==null) break;
	            ByteBuffer buffer = ByteBuffer.allocate(1024);
	            while (socketChannel.read(buffer) != -1) {
	                buffer.flip();
	                String messageStr = StandardCharsets.UTF_8.newDecoder().decode(buffer).toString();
	                List<String> params = new ArrayList<String>(Arrays.asList(messageStr.split(",")));
	                Message message = new Message(params.get(0), Integer.parseInt(params.get(1)), Integer.parseInt(params.get(2)));
	                reception(message);
	                if (buffer.hasRemaining()) {
	                    buffer.compact();
	                } else {
	                    buffer.clear();
	                }
	            }
	            socketChannel.close();
        	}
        	int temps = (int)(Math.random() * 4) + 1;
        	Thread.sleep(temps*1000);
        }
    }
		
	public void envoyer(Message message, int site) throws Exception {
		AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        socketChannel.connect(new InetSocketAddress("localhost", 6800 + site)).get();
        String messageStr = message.getType() + "," + message.getHorloge() + "," + message.getSite();
        ByteBuffer outputBuffer = ByteBuffer.wrap(messageStr.getBytes());
        socketChannel.write(outputBuffer).get();
        this.setLog("--> P" + (site+1) + " [" + message.getType() + "," + message.getHorloge() + "," + (message.getSite()+1) + "]");
        socketChannel.close();
	}
	
	public void diffuser(Message message) throws Exception {
		for (int i=0; i<10; i++) {
			if (this.site!=i) {
				envoyer(message, i);
			}
		}
	}
	
	public boolean isDemandeSC() {
		return this.file.get(this.site).getType().equals("req");
	}

	private void demandeSC() throws Exception {
		this.nbAcq = 9;
		Message requete = new Message("req", this.horloge, this.site);
		diffuser(requete);
		this.file.set(this.site, requete);
		while(this.ctrl.block) {}
		this.ctrl.block = true;
		this.setFile(this.site, requete);
		this.ctrl.block = false;
		this.horloge += 1;
		if (this.nbAcq==0) {
			for (int i=0; i<10; i++) {
				if (this.site!=i) {
					if ((this.file.get(this.site).getHorloge()>this.file.get(i).getHorloge() || (this.file.get(this.site).getHorloge()==this.file.get(i).getHorloge() && this.site>i))&& this.file.get(i).getType().equals("req")) return;
				}
			}
			sectionCritique();
		}
	}
	
	private void sortieSC() throws Exception {
		Message release = new Message("rel", this.horloge, this.site);
		while(this.ctrl.block) {}
		this.ctrl.block = true;
		this.setBtnColor("-fx-background-color: #E8E8E8");
		this.ctrl.block = false;
		this.setLog("Sortie de SC");
		diffuser(release);
		this.file.set(this.site, release);
		while(this.ctrl.block) {}
		this.ctrl.block = true;
		this.setFile(this.site, release);
		this.ctrl.block = false;
		this.horloge += 1;
	}
	
	private void reception(Message message) throws Exception {
		switch(message.getType()) {
			case "req":
				maj(message.getHorloge());
				this.file.set(message.getSite(), message);
				while(this.ctrl.block) {}
				this.ctrl.block = true;
				this.setFile(message.getSite(), message);
				this.ctrl.block = false;
				this.setLog("<-- P" + (message.getSite()+1) + " " + message.toString());
				Message ack = new Message("acq", this.horloge, this.site);
				envoyer(ack, message.getSite());
				break;
			case "rel":
				maj(message.getHorloge());
				this.file.set(message.getSite(), message);
				while(this.ctrl.block) {}
				this.ctrl.block = true;
				this.setFile(message.getSite(), message);
				this.ctrl.block = false;
				this.setLog("<-- P" + (message.getSite()+1) + " " + message.toString());
				break;
			case "acq":
				this.nbAcq -= 1;
				maj(message.getHorloge());
				this.setLog("<-- P" + (message.getSite()+1) + " " + message.toString());
				if (!this.file.get(message.getSite()).getType().equals("req")) {
					this.file.set(message.getSite(), message);
					while(this.ctrl.block) {}
					this.ctrl.block = true;
					this.setFile(message.getSite(), message);
					this.ctrl.block = false;
				}
			    break;
			default:
				return;
		}
		if (this.isDemandeSC() && this.nbAcq==0) {
			for (int i=0; i<10; i++) {
				if (this.site!=i) {
					if ((this.file.get(this.site).getHorloge()>this.file.get(i).getHorloge() || (this.file.get(this.site).getHorloge()==this.file.get(i).getHorloge() && this.site>i))&& this.file.get(i).getType().equals("req")) return;
				}
			}
			sectionCritique();
		}
	}
	
	private void sectionCritique() throws Exception {
		while(this.ctrl.block) {}
		this.ctrl.block = true;
		this.setBtnColor("-fx-background-color: #00ff00");
		this.ctrl.block = false;
		this.setLog("Entrée en SC");
		int tempsSectionCritique = (int)(Math.random()) + 1;
		try {
			Thread.sleep(tempsSectionCritique * 1000);
			sortieSC();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setFile(int index, Message message) {
		switch(this.site) {
			case 0:
				this.ctrl.tab1.getItems().set(index, message);
				break;
			case 1:
				this.ctrl.tab2.getItems().set(index, message);
				break;
			case 2:
				this.ctrl.tab3.getItems().set(index, message);
				break;
			case 3:
				this.ctrl.tab4.getItems().set(index, message);
				break;
			case 4:
				this.ctrl.tab5.getItems().set(index, message);
				break;
			case 5:
				this.ctrl.tab6.getItems().set(index, message);
				break;
			case 6:
				this.ctrl.tab7.getItems().set(index, message);
				break;
			case 7:
				this.ctrl.tab8.getItems().set(index, message);
				break;
			case 8:
				this.ctrl.tab9.getItems().set(index, message);
				break;
			case 9:
				this.ctrl.tab10.getItems().set(index, message);
				break;
			default:
				break;
		}
	}
	
	public void setBtnColor(String style) {
		switch(this.site) {
			case 0:
				this.ctrl.btn1.setStyle(style);
				break;
			case 1:
				this.ctrl.btn2.setStyle(style);
				break;
			case 2:
				this.ctrl.btn3.setStyle(style);
				break;
			case 3:
				this.ctrl.btn4.setStyle(style);
				break;
			case 4:
				this.ctrl.btn5.setStyle(style);
				break;
			case 5:
				this.ctrl.btn6.setStyle(style);
				break;
			case 6:
				this.ctrl.btn7.setStyle(style);
				break;
			case 7:
				this.ctrl.btn8.setStyle(style);
				break;
			case 8:
				this.ctrl.btn9.setStyle(style);
				break;
			case 9:
				this.ctrl.btn10.setStyle(style);
				break;
			default:
				break;
		}
	}
	
	public void maj(int k) {
		if (this.horloge < k) this.horloge = k;
		this.horloge += 1;
	}
	
	public void setLog(String text) {
		this.log += text + "\n";
	}
	
	public String getLog() {
		return this.log;
	}
}
