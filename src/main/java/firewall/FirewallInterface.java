/**
 * 
 */
package firewall;

/**
 * @author mosopeogundipe
 *
 */
public interface FirewallInterface {	
	/**
	 * construct for function that determines whether a firewall should accept a packet
	 * @param direction
	 * @param protocol
	 * @param port
	 * @param ip_address
	 * @return
	 */
	public boolean accept_packet(String direction, String protocol, int port, String ip_address);
	
}
