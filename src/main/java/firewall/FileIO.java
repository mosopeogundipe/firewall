/**
 * 
 */
package firewall;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author mosopeogundipe
 *
 */
public class FileIO {
	
	/**
	 * reads from csv input file, line by line
	 * @param filePath
	 * @param rules
	 */
	public static void readDataLineByLine(String filePath, HashMap<String, HashMap<String, HashSet<Packet>>> rules)
	{ 	  
	    try {  
	        FileReader filereader = new FileReader(filePath); 
	        CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
	        String[] nextRecord; 
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	processLine(nextRecord, rules);
	            } 
	            System.out.println(); 
	    }
	    catch (FileNotFoundException fex)
	    {
	    	System.out.println("Input File Specified doesn't exist. Please specify correct location of csv file");
	    	System.exit(1);
	    }
	    catch (IOException iex)
	    {
	    	System.out.println("Error reading from csv file");
	    	System.exit(1);
	    }
	    catch (Exception e) 
	    { 
	        e.printStackTrace(); 
	        System.exit(1);
	    } 
	}
	
	/**
	 * processes each line from csv file and stores in rules data structure
	 * nextRecord[0] - direction
	 * nextRecord[1] - protocol
	 * nextRecord[2] - port
	 * nextRecord[3] - ip address
	 * @param nextRecord - each line from csv file
	 * @param rules - data structure that stores the firewall rules from input. The Key-Value pairs represent "<IPAddress, <Port, HashSet<Packet>>"
	 */
	public static void processLine(String[] nextRecord, HashMap<String, HashMap<String, HashSet<Packet>>> rules)
	{
		//I didn't create a new String object for every nextRecord array element [e.g String port = nextRecord[2], String IP = nextRecord[3]], just to conserve memory
		//Strings are immutable in java, and creating so many String objects would lead to memory waste.
		if(!rules.containsKey(nextRecord[3]))	//check if rules HashMap doesn't contain the IP Address
    	{
    		HashSet<Packet> packet = new HashSet<Packet>();
    		packet.add(new Packet(Direction.valueOf(nextRecord[0]), Protocol.valueOf(nextRecord[1])));
    		rules.put(nextRecord[3], new HashMap<String, HashSet<Packet>>());
    		rules.get(nextRecord[3]).put(nextRecord[2], packet);
    	}
    	else 
    	{
    		if(!rules.get(nextRecord[3]).containsKey(nextRecord[2])) //check if rules HashMap doesn't contain the Port
    		{
    			HashSet<Packet> packet = new HashSet<Packet>();
        		packet.add(new Packet(Direction.valueOf(nextRecord[0]), Protocol.valueOf(nextRecord[1])));
        		rules.get(nextRecord[3]).put(nextRecord[2], packet);
    		}
    		else	//rules HashMap contains IP and Port already, so just add Packet object to HashSet<Packet>
    		{
    			rules.get(nextRecord[3]).get(nextRecord[2]).add(new Packet(Direction.valueOf(nextRecord[0]), Protocol.valueOf(nextRecord[1])));
    		}
    	}
	}
}
