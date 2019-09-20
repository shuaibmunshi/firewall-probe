public class Probe {
	private String sourceIP;
	private int sourcePort;
	private String destIP;
	private int destPort;
	private String probeTime;
	
	public Probe (String probeTime, String sourceIP, int sourcePort, String destIP, int destPort) {
		this.probeTime = probeTime;
		this.sourceIP = sourceIP;
		this.sourcePort = sourcePort;
		this.destIP = destIP;
		this.destPort = destPort;

	}
	public String getProbeTime() {
		return this.probeTime;
	}
	public String getSourceIP() {
		return this.sourceIP;
	}
	public int getSourcePort() {
		return this.sourcePort;
	}
	public String getDestIP() {
		return this.destIP;
	}
	public int getDestPort() {
		return this.destPort;
	}


}


