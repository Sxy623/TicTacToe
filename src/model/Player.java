package model;

public enum Player { 
	
	CIRCLE, CROSS;
	
	public String colorString() {
		if (this == CIRCLE)
			return "red";
		else 
			return "blue";
	}
	
	public String opponentColorString() {
		if (this == CIRCLE)
			return "blue";
		else 
			return "red";
	}
	
}