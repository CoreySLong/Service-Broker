
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/***************************************************************************************
 *  CMCS 355
 *  Error Module
 *  Component: Utility Services
 ***************************************************************************************
 *  Function: Searches text files
 *--------------------------------------------------------------------------------------
 *  Input:
 *      Parameters - text file, tax bracket or word to be translated
 *  Output:
 *      Return – tax percent or translated word
 *--------------------------------------------------------------------------------------
 *  Author: Abdul Umar, Calvin Hurlbert
 *  Version: 04/15/2022
 **************************************************************************************/

public class textBroker {
    
    public static void main(String[] args) throws IOException{
        // split input string by , and gather data
        String[] parms = args[0].split(",");
        String fileName = parms[0];
        String dataIndicator = parms[1];
        String less_or_equal = parms[2];
        boolean found = false;
        boolean exit = false;
        Integer x = 0;
        Integer y = 0;
        ArrayList<String> a_list = new ArrayList<String>();
        ServiceBroker s = new ServiceBroker();
    
        File file = new File(fileName);
        Scanner reader = new Scanner(fileName); // place holder, to allow prog to compile.




        // try to open requested file else file not found error.
        try{
             reader = new Scanner(file);
        }catch(Exception e){
            a_list = s.Broker("Error,404");
            System.out.println(a_list.get(0));
            exit = true;
            //exit prog -- idk how to do that in java 
        }

        

            // the search file for indicator
        while (reader.hasNextLine() && exit == false) {
            String line = reader.nextLine();
            String[] words = line.split(",");

            // if looking for exact match.
            if(less_or_equal.equals("0")){
                if (words[0].equals(dataIndicator)) {
                    System.out.println(words[1]);
                    found = true;
                }
            }
           // if looking for a less than or equal to
            if(less_or_equal.equals("1")){
                x = Integer.parseInt(dataIndicator);// maybe put another try catch here for "over" in tax files
                try{
                    y = Integer.parseInt(words[0]);
                }catch(Exception e){
                    System.out.println(words[1]);
                    found = true;
                }
                if(x <= y && found == false){
                    System.out.println(words[1]);
                    found = true;
                   
                }
            }
            // when match found break out of while loop
            if(found){
                break;
            }
            // if end of file reached and still no match, - no match error.
            if(!reader.hasNextLine()){
                a_list = s.Broker("Error,404");
                System.out.println(a_list.get(0));
            }
        }
            reader.close();
    }

}