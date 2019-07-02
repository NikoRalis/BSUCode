import java.io.File;


public class ProcessText {
    public static void main (String[] args)
    {
    	String filename;
        if (args.length == 0) 
        {
            System.err.println("Usage: java ProcessText file1 [file2 ...]");
            System.exit(1);
        }

        for (int i=0; i<args.length; i++)
        {
        	filename= args[i];
        	File file = new File(filename);
        
        	if (file.exists()){
        		TextStatistics textFile = new TextStatistics(file);
        		
        		System.out.println(textFile.toString());
   
        		
        	}
        	else {
        		System.err.println("The file\" "+ filename+ "\" is not readable or does not exist.");
        	}
        }
    }

}
