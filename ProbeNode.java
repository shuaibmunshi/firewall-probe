public class ProbeNode {
	public Probe aProbe;
	public ProbeNode next;
	
	public ProbeNode(Probe aProbe){
		this.aProbe = aProbe;
	}
	
	public ProbeNode(ProbeNode aNode){
		aProbe = aNode.aProbe;
		next = aNode.next;
	}
}
