import java.util.ArrayList;

public class ProbeLList {
	private ProbeNode head;
	private ProbeNode tail;
	
	public ProbeLList(){}
	
	//https://stackoverflow.com/questions/13245908/iteratively-deep-copy-a-link-list
	//Copy constructor
	public ProbeLList(ProbeLList linkedList) {
		ProbeNode oldList = linkedList.head;
		ProbeNode newList = head;
		while(oldList.next != null) {
			newList = new ProbeNode(oldList.aProbe);
		    oldList.next = newList;
		    newList.next = newList;

		}
	}
	
	//https://www.geeksforgeeks.org/find-length-of-a-linked-list-iterative-and-recursive/
	//Counts elements in the list
	public int getActualSz() {
		int size=0;
		ProbeNode tempNode = head; 
		while (tempNode.next != null) { 
			tempNode = tempNode.next; 
			size++;
		} 
		return size;
	}
	//https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/
	public int insertProbe(Probe probe) {
		//ProbeNode tmpNode = new ProbeNode(probe);
		if(head == null) {
			head = new ProbeNode(probe);
			//return getActualSz();
			tail = head;
		}
		else {
			tail.next = new ProbeNode(probe);
			tail = tail.next;
			//return getActualSz();
			
		}
		return getActualSz();
	}
	
	public int countProbes(String IP) { 
		int probecount=0;
		ProbeNode tempNode = head; 
		while (tempNode.next != null) { 
			if (tempNode.aProbe.getSourceIP().contentEquals(IP)) {
				probecount++;
			}
			tempNode = tempNode.next; 
		} 
		return probecount;
	}
	
	public int countProbes(int dest) { 
		int probecount=0;
		ProbeNode tempNode = head; 
		while (tempNode.next != null) { 
			if (tempNode.aProbe.getDestPort() == (dest)) {
				probecount++;
			}
			tempNode = tempNode.next; 
		} 
		return probecount;
	}
	//Gets the source IP for any entry matching the destination port
	public ArrayList<String> getProbes(int destPort){ 
		ArrayList<String> destList = new ArrayList<String>(this.getActualSz());
		ProbeNode tempNode = head; 
		
		while (tempNode.next != null) { 
			if (tempNode.aProbe.getDestPort() == (destPort)) {
				destList.add(tempNode.aProbe.getSourceIP());
			}
			tempNode = tempNode.next; 
		}
		return destList;
	}
	
	//Returns a list of Probe objects for a given source IP
	public ArrayList<Probe> getProbes(String IP){ 
		ArrayList<Probe> sourceIP = new ArrayList<Probe>(this.getActualSz());
		ProbeNode tempNode = head; 
		while (tempNode.next != null) { 
			if (tempNode.aProbe.getSourceIP().equals(IP)) {
				sourceIP.add(tempNode.aProbe);
			}
			tempNode = tempNode.next; 
		}
		return sourceIP;
	}
	
}

