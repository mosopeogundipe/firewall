/**
 * 
 */
package firewall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author DELL
 *
 */
public class CommandLineUtilities {
	
	/**
	 * processes input from command line and returns response
	 * @param firewall
	 */
	public void processInput(Firewall firewall)
	{
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		String command = null;
		try  {
			while(!(command = reader.readLine()).equals("exit"))
			{
				String [] commandSplit = command.split(",");	//array should contain only 4 elements.	
				if(commandSplit.length != 4)
				{
					System.out.println("Invalid command. " + "Command should be of the form \"direction,protocol,port,ipaddress\" ");
					continue;
				}
				else
				{
					boolean result = firewall.accept_packet(commandSplit[0], commandSplit[1], Integer.valueOf(commandSplit[2]), commandSplit[3]);
					System.out.println(result);
				}
			}
			System.out.println("Program Aborted!");
			System.exit(0);				//exit when "exit" command is entered

		} catch (IOException ex) {
			System.out.println("Error reading input from command line");
			System.out.println("\n");
			ex.printStackTrace();
		} 
	}
	
}
