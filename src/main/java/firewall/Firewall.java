/**
 * 
 */
package firewall;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author mosopeogundipe
 * firewall main class
 */
public class Firewall implements FirewallInterface {
	/**
	 * "HashMap<String, HashMap<String, HashSet<Packet>>> rules" is the data structure that stores the rules data from input CSV
	 * (K, V) -->  <IPAddress, <Port, HashSet<Packet>>
	 */
	private static HashMap<String, HashMap<String, HashSet<Packet>>> rules = new HashMap<String, HashMap<String, HashSet<Packet>>>();
	private String foundIP;		//stores the value of IP, used to retrieve the IP from map in the accept_packet method
	private String foundPort;	//stores the value of Port, used to retrieve the IP from map in the accept_packet method
	
	public Firewall(String filePath)
	{
		FileIO.readDataLineByLine(filePath, rules);
	}

	public boolean accept_packet(String direction, String protocol, int port, String ip_address)
	{	
		if((!direction.equals("inbound") && !direction.equals("outbound")) || (!protocol.equals("tcp") && !protocol.equals("udp")))
			return false;
		if(isIPValid(ip_address) && isPortValid(port))
		{
			if(rules.get(foundIP).get(foundPort).contains(new Packet(Direction.valueOf(direction), Protocol.valueOf(protocol))))
				return true;
		}
		foundIP = null;
		foundPort = null;
		return false;
	}
	
	public boolean isIPValid(String ip_address)
	{
		if(rules.containsKey(ip_address))
		{
			foundIP = ip_address;
			return true;
		}			
		for(String ipAddress: rules.keySet())
		{
			String [] ipList = ipAddress.split("-");
			if(ipList.length == 2)
			{
				if(RangeChecker.isValidIPRange(ipList[0], ipList[1], ip_address))
				{
					foundIP = ipAddress;
					return true;	//return true once valid IP is found, so we only go through n items if we absolutely need to
				}					
			}
		}
		return false;
	}
	
	public boolean isPortValid(int port)
	{
		HashMap<String, HashSet<Packet>> portMap = rules.get(foundIP);
		if(portMap.containsKey(String.valueOf(port)))
		{
			foundPort = String.valueOf(port);
			return true;	
		}			
		for(String thePort: portMap.keySet())
		{
			String [] portList = thePort.split("-");
			if(portList.length == 2)
			{
				if(RangeChecker.isValidPortRange(Integer.valueOf(portList[0]), Integer.valueOf(portList[1]), port))
				{
					//System.out.println("entered 2");
					foundPort = thePort;
					return true;	//return true once valid IP is found, so we only go through n items if we absolutely need to	
				}					
			}
		}
		return false;
	}
}
