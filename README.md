# Simple Firewall Application
=========================================================

This is a simple firewall application that only decides whether a packet should be accepted or not, based on the rules uploaded in a csv file following the desired format. The "FirewallRules.csv" file in this repository is an example of a correct input csv file.

### Things to note:
1. This program executes from command line, and returns response there
2. The "Driver.java" class is the main point of entry into the program
3. Test cases can be found in the class "FirewallTest.java" class in this repository, under the path "\src\test\java\firewall"

### Future Refinements/Optimizations
I didn't include the following in the initial build because of time constraint, and would look try to implement them in future:
1. Functionality for logging and config files
2. Support for multi-threading in the whole application
3. Implementing a data structure in the firewall class that has a more balanced space/time complexity relationship. E.g considering the possibility of using a balanced binary tree (O(log n) time complexity and O(n) space complexity) vs the current implementation of Hashmap and HashSet. 

### To run the program:
1. In the command line terminal, navigate to the folder where the firewall.jar file exists
2. Run the command "java -cp firewall.jar firewall.Driver -csvpath <CSVPATH>", where <CSVPATH> will be the full path of the csv input file
3. **Example of correct command is: java -cp firewall.jar firewall.Driver -csvpath C:\Users\Documents\FirewallRules.csv**
4. Program will have to be restarted if an incorrect command is entered, because it will display an error and terminate the program.
5. Once the database finishes building (i.e when the constructor in firewall returns), you should see a "Enter command below: " prompt on screen
6. Enter search command in the form "direction,protocol,port,ipaddress"
7. **Do not include spaces in the search command, as the accepts_packet method expects well-formed input according to given spec**
8. You would get the response "true" or "false", for the search result.
9. When done, enter "exit" to exit the program gracefully

### Team of interest:
I'm interested in the Platform and Policy teams.

Thanks for reading
