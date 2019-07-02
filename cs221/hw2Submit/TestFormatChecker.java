import java.io.FileNotFoundException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for the FormatChecker class. 
 *  
 * @author Matt T
 */
public class TestFormatChecker 
{
	// Files used in tests
	private final String noSuchFile = "noSuchFile.txt";
	private static final Object[][] VALID_FILES = { { "valid1.dat" }, { "valid2.dat" }, { "valid3.dat" } }; 
	private static final Object[][] NUMBER_FORMAT_FILES = { { "invalid1.dat" }, { "invalid3.dat" }, { "invalid6.dat" } }; 
	private static final Object[][] DIMENSION_MISMATCH_FILES = { { "invalid2.dat" }, { "invalid4.dat" }, { "invalid5.dat" } , { "invalid7.dat" } }; 
	
	/**
	 * Test: File not found 
	 * Expected Result: FileNotFoundException
	 */
	@Test(groups = "notFound", expectedExceptions = FileNotFoundException.class)
	public void testFileNotFound() throws FileNotFoundException
	{
		FormatChecker.checkFormat(noSuchFile);
	}

	/**
	 * Test: Files with valid data 
	 * Expected Result: ValidFormatException
	 */
	@Test(groups = "valid", dataProvider = "validFiles", expectedExceptions = ValidFormatException.class)
	public void testValidFiles(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	/**
	 * Test: Invalid files with data that is not a number  
	 * Expected Result: NumberFormatException
	 */
	@Test(groups = "numberFormat", dataProvider = "numberFormatFiles", expectedExceptions = NumberFormatException.class)
	public void testNumberFormatFiles(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	/**
	 * Test: Invalid files with dimensions not matching data 
	 * Expected Result: DimensionMismatchException
	 */
	@Test(groups = "dimensionMismatch", dataProvider = "dimensionMismatchFiles", expectedExceptions = DimensionMismatchException.class)
	public void testDimensionMismatch(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	//********** Data Providers ***************************
	   /**
	    * Data: names of files that should cause NumberFormatException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] numberFormatFiles() 
	   {
	      return NUMBER_FORMAT_FILES;
	   }
	   
	   /**
	    * Data: names of files that should cause DimensionMismatchException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] dimensionMismatchFiles() 
	   {
	      return DIMENSION_MISMATCH_FILES;
	   }

	   /**
	    * Data: names of files that should cause ValidFileException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] validFiles() 
	   {
	      return VALID_FILES;
	   }
}
