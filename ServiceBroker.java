
/** 
 * Module Name: ServiceBroker.java
 * Author: Dharva Patel
 * Date: 4/21/22
 * Description: Acts as a middle man to execute a file from a given service code
 **/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceBroker {
    /**
     * Main Method
     *
     * @param args String[]
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Creates a new service broker object
        ServiceBroker sb = new ServiceBroker();

        //Create an array list to hold the value that is returned
        ArrayList<String> x;
        x = sb.Broker(args[0]);

        //Prints out the contents of the array list
        for (int i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
    }

    /**
     * Scans the Service-.txt file to read into it
     *
     * @return Scanner in
     * @throws FileNotFoundException
     */
    public Scanner scan() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Service-1.txt"));
        return in;
    }

    /**
     * @param serviceCode String
     * @return ArrayList<String> s
     * @throws IOException
     */
    public ArrayList<String> Broker(String serviceCode) throws IOException {
        ArrayList<String> s = new ArrayList<>();
        Scanner in = scan();

        //Reads the file from the Scan() method
        while (in.hasNextLine()) {
            String serviceFile = in.nextLine();

            //Splits the line from the service text file into the file path and service code
            String[] serviceFileData = serviceFile.split(",");

            //Splits the serviceCode string into two parts the service code and the parameters
            String[] serviceCodeData = serviceCode.split(",", 2);

            //Checks to see if the service codes match
            //If they do send the execute command and paramter list to the call method the break out the loop
            if (serviceCodeData[0].equals(serviceFileData[0])) {
                s = Call(serviceFileData[1], serviceCodeData[1]);
                break;
            } else {
                //If there is no more lines in file to read then call broker again to send a service not found error message
                if (!in.hasNextLine()) {
                    s = Broker("Error,703");
                    break;
                }
            }

        }
        return s;
    }

    /**
     * Calls the processbuilder method with the parameters sent from broker
     *
     * @param serviceFilePath String
     * @param parameterList   String
     * @return ArrayList<String> s
     * @throws IOException
     */
    private ArrayList<String> Call(String serviceFilePath, String parameterList) throws IOException {
        ArrayList<String> s = new ArrayList<>();
        s = ProcessBuilder(serviceFilePath, parameterList);
        return s;
    }

    /**
     * Creates a process builder to execute the jar file
     *
     * @param filePath      String
     * @param parameterList String
     * @return ArrayList<String> x
     * @throws IOException
     */
    private ArrayList<String> ProcessBuilder(String filePath, String parameterList) throws IOException {
        //Splits the filePath string by spaces
        String[] path;
        path = filePath.split(" ");
        File file = new File(path[2]);
        int i = 0;

        ArrayList<String> commands = new ArrayList<>();

        //Adds the elements from path to the arraylist commands
        while (i < path.length - 1) {
            commands.add(path[i]);
            i++;
        }

        //Adds the absolute file path of the file to commands
        commands.add(file.getAbsolutePath());

        //Adds the parameter list string to commands
        commands.add(parameterList);

        //Creates a process builder by using the elements in the commands arraylist
        ProcessBuilder builder = new ProcessBuilder(commands);

        //Starts the process
        Process p = builder.start();

        //Gets the input stream from the process and puts it into the buffered reader 
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s;
        ArrayList<String> x = new ArrayList<String>();

        //Reads each line in the buffered reader and puts it into the arraylist x as long as the input is not null
        while ((s = in.readLine()) != null) {
            x.add(s);
        }

        //Closes the buffered reader, ends the process and returns the arraylist
        in.close();
        p.destroy();
        return x;
    }
}
