package businesslogic.memberbl;

public class MemAccountInfo {
	private double MaxOwe,toReceive, toPay;

	public MemAccountInfo(double maxOwe, double toReceive, double toPay) {
		
		this.MaxOwe = maxOwe;
		this.toReceive = toReceive;
		this.toPay = toPay;
	}

	public double getMaxOwe() {
		return MaxOwe;
	}

	public double getToReceive() {
		return toReceive;
	}

	public double getToPay() {
		return toPay;
	}

	public void setMaxOwe(double maxOwe) {
		MaxOwe = maxOwe;
	}

	public void setToReceive(double toReceive) {
		this.toReceive = toReceive;
	}

	public void setToPay(double toPay) {
		this.toPay = toPay;
	}
	
	
	
	
	

}
