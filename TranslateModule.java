
/****************************************************************************************
 * Translate Module
 *
 * Component: Task Layer
 ***************************************************************************************
 *    Function: Calls service broker to call text broker to translate a word given
 *--------------------------------------------------------------------------------------
 *    Input: language to translate to, word to translate
 *    Output: translated word
 *--------------------------------------------------------------------------------------
 *    Author: Corey Long
 *    Version 4/14/2022   CMCS 355
 ***************************************************************************************
 */

import java.io.IOException;
import java.util.ArrayList;


public class TranslateModule {
    public static void main(String[] args) throws IOException {
        ServiceBroker sb = new ServiceBroker();
        String[] inputs = args[0].split(",", 2);
        
        ArrayList<String> transWord;

        inputs[0] += ".txt";// adding .txt to end of the language so text broker will know which file to read

        // 1st parameter: language to translate to, 2nd parameter: word to translate
        String serviceCode = "TB," + inputs[0] + "," + inputs[1] + ",0"; // prepares string for service broker call

        transWord = sb.Broker(serviceCode);   // calling service broker
        
        String[] engWord = inputs[1].split(",");
        for(int i = 0; i < engWord.length; i++) {
            System.out.println(engWord[i] + ", " + transWord.get(i));
        }
    }
}
























