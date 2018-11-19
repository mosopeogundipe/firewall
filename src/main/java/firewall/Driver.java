/**
 * 
 */
package firewall;


/**
 * @author mosopeogundipe
 * main class where program starts
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 2 || !args[0].equals("-csvpath"))
		{
			System.out.println("Invalid Start Arguments" + "\n" + "Arguments should be of the form: " + " " 
					+ "java -cp firewall.jar firewall.Driver -csvpath <CSVPATH>" );
			System.exit(0);
		}
		String csvPath = args[1].trim();
		System.out.println("Started Building database...");
		Firewall firewall = new Firewall(csvPath);
		System.out.println("Finished Building database...");
		System.out.println("Enter command below:");
		System.out.println("Command should be of the form \"direction,protocol,port,ipaddress\" ");
		new CommandLineUtilities().processInput(firewall);
		
	}

}
