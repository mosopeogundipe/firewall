/**
 * 
 */
package firewall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import firewall.FileIO;
import firewall.Firewall;
import firewall.Packet;
import firewall.RangeChecker;


/**
 * @author mosopeogundipe
 * Junit Test cases to validate correctness of firewall application
 */
public class FirewallTest {

	private static final String filePath = "FirewallRules.csv";	
	private static HashMap<String, HashMap<String, HashSet<Packet>>> rules = new HashMap<String, HashMap<String, HashSet<Packet>>>();
	private static Firewall firewall;
	
	@BeforeClass
	public static void initializeEntities()
	{
		FileIO.readDataLineByLine(filePath, rules);
		firewall = new Firewall(filePath);
	}
	
	@Test
	public void testCorrectNumberOfDistinctIPs()
	{
		assertEquals("Invalid Number Of Items", rules.keySet().size(), 5);
	}
	
	@Test
	public void testCorrectNumberOfDistinctPorts()
	{
		int count = 0;
		for(String key: rules.keySet())
		{
			count += rules.get(key).keySet().size();
		}
		assertEquals("Invalid Number Of Items", count, 6);
	}
	
	@Test
	public void testCorrectNumberOfPackets()
	{
		int count = 0;
		for(String key: rules.keySet())
		{
			HashMap<String, HashSet<Packet>> ipMap = rules.get(key);
			for (String port: ipMap.keySet())
			{
				count += ipMap.get(port).size();
			}			
		}
		assertEquals("Invalid Number Of Items", count, 7);
	}
	
	@Test
	public void testValidIP()
	{
		assertTrue("Invalid IP", RangeChecker.isValidIPRange("122.170.122.0", "122.170.123.255", "122.170.122.215"));
	}
	
	@Test
	public void testValidPort()
	{
		assertTrue("Invalid Port", RangeChecker.isValidPortRange(1, 80, 79));
	}
	
	@Test
	public void testInvalidIP()
	{
		assertFalse("Invalid IP", RangeChecker.isValidIPRange("122.170.122.0", "122.170.120.255", "122.170.123.215"));
	}
	
	@Test
	public void testInvalidPort()
	{
		assertFalse("Invalid Port", RangeChecker.isValidPortRange(1, 80, 81));
	}
	
	@Test
	public void testAcceptPacketInPortRange()
	{
		assertTrue("Invalid Packet", firewall.accept_packet("outbound", "udp", 1500, "52.12.48.92"));
	}
	
	@Test
	public void testAcceptPacketInIPRange()
	{
		assertTrue("Invalid Packet", firewall.accept_packet("inbound", "tcp", 53, "192.168.1.254"));
	}
	
	@Test
	public void testAcceptPacketSingleIPAndPort()
	{
		assertTrue("Invalid Packet", firewall.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
	}
	
	@Test
	public void testAcceptPacketMaxIPAndPort()
	{
		assertTrue("Invalid Packet", firewall.accept_packet("inbound", "udp", 65535, "255.255.255.255"));
	}
	
	@Test
	public void testAcceptPacketMinIPAndPort()
	{
		assertTrue("Invalid Packet", firewall.accept_packet("inbound", "udp", 1, "0.0.0.0"));
	}
	
	@Test
	public void testRejectedPacketInPortRange()
	{
		assertFalse("Invalid Packet", firewall.accept_packet("outbound", "udp", 15000, "52.12.48.92"));
	}
	
	@Test
	public void testRejectedPacketInIPRange()
	{
		assertFalse("Invalid Packet", firewall.accept_packet("inbound", "tcp", 53, "192.168.2.6"));
	}
	
	@Test
	public void testRejectedPacketSingleIPAndPort()
	{
		assertFalse("Invalid Packet", firewall.accept_packet("inbound", "tcp", 81, "192.168.1.3"));
	}
	
	@Test
	public void testRejectedPacketWrongDirection()
	{
		assertFalse("Invalid Packet", firewall.accept_packet("outbound", "tcp", 81, "192.168.1.3"));
	}
	
	@Test
	public void testRejectedPacketWrongProtocol()
	{
		assertFalse("Invalid Packet", firewall.accept_packet("inbound", "udp", 81, "192.168.1.3"));
	}
}
