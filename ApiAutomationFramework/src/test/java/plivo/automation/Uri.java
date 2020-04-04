package plivo.automation;

public enum Uri {

	CHANNEL_CREATE ("/api/channels.create"),
	CHANNEL_JOIN ("/api/channels.join"),
	CHANNEL_RENAME ("/api/channels.rename"),
	CHANNEL_ARCHIVE ("/api/channels.archive"),
	CHANNEL_LIST ("/api/channels.list"),
	CHANNEL_INFO ("/api/channels.info");
	
	private final String name;

	private Uri(String name) {
		this.name = name;
	}
	
	public String toString() {
	       return this.name;
	}
}
