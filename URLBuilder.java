import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

public class URLBuilder {
	
	public static void main(String args[]) throws IOException {
		// first check to see if the program was run with the command line argument
	    if(args.length < 1) {
	        System.out.println("Error, usage: java URLBuilder inputfile");
		System.exit(1);
	    }
		
        String csvFile = args[0];
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String retVal = "";
        String formatLine = "-------------------------------------------";
        String domain ="matthewklich.com"

        try {
            br = new BufferedReader(new FileReader(csvFile));
            Boolean first = true;
            while ((line = br.readLine()) != null) {
            	if (!first) retVal += "&";
            	first = false;
                String[] row = line.split(cvsSplitBy);
                retVal += row[0] + row[1] + "o=" + thousandCheck(row[2]) + "&" + row[0] + row[1] + "s=" + thousandCheck(row[3]);
            }
            System.out.println(System.lineSeparator() + formatLine + System.lineSeparator() + 
            		domain + retVal + System.lineSeparator() + formatLine + System.lineSeparator());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	private static String thousandCheck(String s) {
		int num = 0;
		num = Integer.valueOf(s);
		return NumberFormat.getNumberInstance(Locale.US).format(num);
	}
}