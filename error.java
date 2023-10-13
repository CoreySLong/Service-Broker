
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**************************************************************************************
  *    Error Module
  *
  * Component: Utility Services
  ***************************************************************************************
  * Function:
  *   Prints Error messages
  *----------------------------------------------------------------------------------------------------------------------------------------
  *    Input:
  *          Parameters - Error code
  *    Output:
  *          Return Error message for Error code in preferred language
  *----------------------------------------------------------------------------------------------------------------------------------------
  *    Author Abdul Umar
  *    Version 04/05/2022   CMCS 355 
  **************************************************************************************/ 
public class error {
    public static void main(String[] args) throws IOException {
        ServiceBroker s = new ServiceBroker();
        String error_code = args[0];
        // opens file to be read
        FileReader fr = new FileReader("options.txt");
        ArrayList<String> d = new ArrayList<>();
        
        // reads contents of file
        String pref_lang = "";
        int x = fr.read();
        while (x != -1){
            pref_lang += (char)x;
            x = fr.read();
        }
        fr.close();

        String final_file = "msg" + pref_lang + ".txt";

        //System.out.println("TB,"+error_code+","+final_file);

        // passes to service broker to call TB
        d = s.Broker("TB,"+final_file+","+error_code+"0");
        System.out.println(d.get(0));
       
    } 
}
