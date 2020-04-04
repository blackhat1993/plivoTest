package plivo.automation;

public enum Channel {

	NAME ("name"),
	VALIDATE ("validate"),
	CHANNEL ("channel");
	
	private final String name;

	private Channel(String name) {
		this.name = name;
	}
	
	public String toString() {
	       return this.name;
	}
	
}
