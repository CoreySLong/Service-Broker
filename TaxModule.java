
/****************************************************************************************
 * Tax Module
 *
 * Component: Task Layer
 ***************************************************************************************
 *    Function: Calls service broker to call text broker to translate a word given
 *--------------------------------------------------------------------------------------
 *    Input: Tax year, Status, Gross Income
 *    Output: Amount owned
 *--------------------------------------------------------------------------------------
 *    Author: Derrick Kyereh
 *    Version 4/14/2022   CMCS 355
 ***************************************************************************************
 */
import java.io.IOException;
import java.util.*;

public class TaxModule {
    public static void main(String[] args) throws IOException {
        ServiceBroker s= new ServiceBroker();

        Scanner Scan = new Scanner(System.in);
        //gets the pram-list
        String services =args[0];
        //splits the pram-list
        String [] EachEl= services.split(",");
        String TaxYear, C, IncomeB;
        ArrayList<String> P = new ArrayList<String>();
        TaxYear = EachEl[0];
        C=EachEl[1];
        IncomeB =EachEl[2];
// parses the income from string to int
        int Income= Integer.parseInt(IncomeB);
        String FIncome;


// concats the strings into a one string for the service code
        String fileDes = "TB," + TaxYear + C +".txt" + "," + IncomeB +"," + "1";


        //gets the percent and parses it into the decimal format.
        P =  s.Broker(fileDes);
        double percent =Double.parseDouble(P.get(0));

//does the math and returns the amount owned
        double amountOwn = Income * percent;
        System.out.print(String.format("%.2f",amountOwn));


    }

}
