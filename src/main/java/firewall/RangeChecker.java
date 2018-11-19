/**
 * 
 */
package firewall;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author mosopeogundipe
 *
 */
public class RangeChecker {

	/**
	 * converts IP to numeric value
	 * gotten from https://stackoverflow.com/questions/4256438/calculate-whether-an-ip-address-is-in-a-specified-range-in-java
	 * @param ip
	 * @return
	 */
	 public static long ipToLong(InetAddress ip) {
			byte[] octets = ip.getAddress();
			long result = 0;
			for (byte octet : octets) {
				result <<= 8;
				result |= octet & 0xff;
			}
			return result;
		}

	 	/**
	 	 * confirms that a given IP is within range specified
	 	 * gotten from https://stackoverflow.com/questions/4256438/calculate-whether-an-ip-address-is-in-a-specified-range-in-java
	 	 * @param ipStart
	 	 * @param ipEnd
	 	 * @param ipToCheck
	 	 * @return
	 	 */
		public static boolean isValidIPRange(String ipStart, String ipEnd,
				String ipToCheck) {
			try {
				long ipLo = ipToLong(InetAddress.getByName(ipStart));
				long ipHi = ipToLong(InetAddress.getByName(ipEnd));
				long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
				return (ipToTest >= ipLo && ipToTest <= ipHi);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * confirms that a given port is in the range specified
		 * @param portStart
		 * @param PortEnd
		 * @param portToCheck
		 * @return
		 */
		public static boolean isValidPortRange(int portStart, int PortEnd, int portToCheck)
		{
			return (portToCheck >= portStart && portToCheck <= PortEnd);
		}
	
}
