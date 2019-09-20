import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class PSDB {

	public static void main(String[] args) {
		ProbeLList testList = new ProbeLList();
		
		//https://stackoverflow.com/questions/13185727/reading-a-txt-file-using-scanner-class-in-java
		//Parse the file into the ProbeLList
		try {
			System.out.println("Reading from file");
			Scanner scanFile = new Scanner(new File("firewall.log.txt"));
			
			while (scanFile.hasNextLine()) {
				//https://stackoverflow.com/questions/22644746/getting-first-value-from-a-comma-separated-string
				String item = scanFile.nextLine();
				String[] splitString = item.split("\\s");
				String timeStamp = splitString[0];
				String sourceIP = splitString[1].split(":")[0];
				String sourcePort = splitString[1].split(":")[1];
				String destIP = splitString[2].split(":")[0];
				String destPort = splitString[2].split(":")[1];
				//System.out.println(sourceIP);
				Probe newProbe = new Probe(timeStamp, sourceIP, Integer.parseInt(sourcePort), destIP, Integer.parseInt(destPort));
				testList.insertProbe(newProbe);
			}
			scanFile.close();
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		//Testing
		System.out.println("#####TESTING#####");
		Probe testProbe1 = new Probe("2019-02-02(10:10:10)", "10.10.10.10", 1000, "192.168.1.1", 23);
		Probe testProbe2 = new Probe("2019-02-02(10:10:10)", "20.20.20.20", 1000, "192.168.1.2", 23);
		System.out.println(testProbe1.getSourceIP() + " " + testProbe1.getSourcePort() + " " + testProbe1.getDestIP() + " " + testProbe1.getDestPort());
		System.out.println(testProbe2.getSourceIP() + " " + testProbe2.getSourcePort() + " " + testProbe2.getDestIP() + " " + testProbe2.getDestPort());

		ProbeLList testProbeLList = new ProbeLList();
		testProbeLList.insertProbe(testProbe1);
		testProbeLList.insertProbe(testProbe2);
		System.out.println("Test List current size: " + testProbeLList.getActualSz());
		//System.out.println("Test List max size: " + testProbeLList.getMaxSize());
		System.out.println("Counting probes for  10.10.10.10" + "  = " + testProbeLList.countProbes("10.10.10.10"));
		System.out.println("Getting probes for  10.10.10.10" + "  = " + testProbeLList.getProbes("10.10.10.10"));

		
		
		//User interaction below
		@SuppressWarnings("resource")
		Scanner getInput = new Scanner(System.in);
		//String input = getInput.next();
		//getInput.close();
		System.out.println("Welcome to the Port Scan Database.");
		while(true) {
			//String input = "";
			System.out.println("Enter IP/DP/PL/IL/END (IP address/Destination Port/Port List/IP List/END):");
			String input = getInput.next(); //Gets user input
			
			switch (input.toUpperCase()) {
			default:
				break;

			case "IP":
				System.out.println("For which IP do you wish to retrieve statistics?");
				String IP = getInput.next();
				System.out.println(IP);
					if (testList.countProbes(IP) == 0) {
						System.out.println("There were no probes from that IP address.");
					}
					else {
						System.out.println("There were " + testList.countProbes(IP) + " probes from " + IP + ".");

					}
				break;
				
			case "DP":
				System.out.println("For which port do you wish to retrieve statistics?");
				String DP = getInput.next();
					if (testList.countProbes(Integer.parseInt(DP)) == 0) {
						System.out.println("There were no probes of port " + DP + ".");
					}
					else {
						System.out.println("There were " + testList.countProbes(Integer.parseInt(DP)) + " probes from " + DP + ".");

					}
				break;
				
			case "PL":
				System.out.println("Enter a source IP address to see a list of ports that it scanned");
				String PL = getInput.next();
				ArrayList<Probe> portsList = new ArrayList<Probe>();
				portsList = testList.getProbes(PL);
				if (portsList.size() == 0) {
					System.out.println("No packets were sent from that IP address.");
				}
				else {
					for (int i = 0; i < portsList.size(); i++) {
						System.out.println("IP " + portsList.get(i).getSourceIP() + " sent a packet from port " + portsList.get(i).getSourcePort() + " to port " + portsList.get(i).getDestPort());

					}
				}
				break;
				
			case "IL":
				System.out.println("For which port do you wish to retrieve statistics?");
				String IL = getInput.next();
				ArrayList<String> ipList = new ArrayList<String>();
				ipList = testList.getProbes(Integer.parseInt(IL));
				if (ipList.size() == 0) 
				{
					System.out.println("There were no probes of port " + IL + ".");
				}
				else {
					System.out.println("The " + ipList.size() + " different IPs who probed port " + IL + " are as follows:");
					for(int i=0; i<ipList.size(); i++) 
					{
						System.out.println(ipList.get(i));
					}
				}
				break;
				
			case "END":
				System.out.println("Goodbye.");
				break;
				
			}
			//getInput.close();

		}
		
	}

}
