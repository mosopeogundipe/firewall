/**
 * 
 */
package firewall;

/**
 * @author mosopeogundipe
 * Stores the firewall rules from input csv file
 */
public class Packet {	
	private Direction direction;
	private Protocol protocol;
	public Packet(Direction direction, Protocol protocol)
	{
		this.direction = direction;
		this.protocol = protocol;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Protocol getProtocol() {
		return protocol;
	}
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
	
	//overrode this method from object class to allow Set.Contains method work well
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Packet))
			return false;	
		if (obj == this)
			return true;
		Packet packet = (Packet) obj;
		return (this.direction.equals(packet.direction) && this.protocol.equals(packet.protocol));
	}
	
	//overrode this method from object class to allow Set.Contains method work well
	public int hashCode()
	{
		return this.direction.hashCode() + this.protocol.hashCode();
	}
}

enum Direction
{
	inbound,
	outbound;
}

enum Protocol
{
	tcp,
	udp;
}