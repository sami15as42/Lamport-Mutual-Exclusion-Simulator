
public class Message {
	
	private String type;
	private int horloge;
	private int site;
	
	public Message(String type, int horloge, int site) {
		super();
		this.type = type;
		this.horloge = horloge;
		this.site = site;
	}
	
	public String toString() {
		return "[" + this.type + "," + this.horloge + "," + this.site + "]";
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getHorloge() {
		return horloge;
	}
	
	public void setHorloge(int horloge) {
		this.horloge = horloge;
	}
	
	public int getSite() {
		return site;
	}
	
	public void setSite(int site) {
		this.site = site;
	} 
}
