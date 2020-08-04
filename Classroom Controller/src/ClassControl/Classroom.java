package ClassControl;

public class Classroom {
	private int cap;
	private String name;
	
	public Classroom(int cap) {
		}
	public Classroom(int cap, String name) {
		this.cap=cap;
		this.name=name;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
