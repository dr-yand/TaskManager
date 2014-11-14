package taskmanager.client.entity;

public class Task {
	private String id, icon, text, name;

	public Task(String id, String icon, String text, String name) {
		super();
		this.id = id;
		this.icon = icon;
		this.text = text;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
