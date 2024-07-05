package miniProject;
 
import java.util.Scanner;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//define listner class that is attatched to your test class
@Listeners(miniProject.Extentreport.class)
public class Main
{
	String link;
	String search;
	String select;
	String filePath;
	String Browser;
	boolean condition;
	int rows;
 
	@BeforeClass
	public void Beforeclass() throws IOException
	{
		boolean bol = true;
		while(bol) {
			System.out.println("Enter the browser to Automate \n1. Chrome \n2. Edge");
			Scanner sc = new Scanner(System.in);
			Browser = sc.next();
			if(Browser.equalsIgnoreCase("Chrome") || (Browser.equalsIgnoreCase("Edge"))){
				OnlineMobileSearch.getWebDriver(Browser);
				bol =false;
				sc.close();
			}else {
				System.out.println("Enter Valid Browser name(Chrome or Edge)");	
			}	
	    }
		filePath = System.getProperty("user.dir")+"\\TestData\\input_data.xlsx";
		//Get the row count from the excel
		rows = ExcelUtils.getRowCount(filePath, "Amazon");
		for(int i=1; i<=rows;i++) {
		//read data from excel
		    link = ExcelUtils.getCellData(filePath, "Amazon", rows, 0);
		    search = ExcelUtils.getCellData(filePath, "Amazon", rows, 1);
		  	select = ExcelUtils.getCellData(filePath, "Amazon", rows, 2);
		}
	}
	
	
	@Test(priority=1)
	public void launchingApplication() throws IOException
	{	
		//pass data to the driver
		OnlineMobileSearch.LaunchUrl(link);
	}
	@Test(priority=2)
	public void maximizeApplication() throws IOException, InterruptedException
	{
		//Maximizing the window
	    OnlineMobileSearch.MaximizeWindow();
	}
	@Test(priority=3)
	public void searchItem() throws InterruptedException, IOException
	{
		//Search in the application
		OnlineMobileSearch.toSearch(search);
	}
	@Test(priority=4)
	public void validation() throws IOException, InterruptedException
	{
		//Validate the search string
		condition=OnlineMobileSearch.Validation(search);
	}
	@Test(priority=5)
	public void selectDropdown() throws IOException, InterruptedException
	{
		//Select option “Newest Arrivals"
		OnlineMobileSearch.dropSelect(select);
 
	}
	@Test(priority=6)
	public void writingExcel() throws IOException, InterruptedException
	{
		//write output in excel
		for(int i=1; i<=rows;i++)
		{
		if(condition)
		 {
		 	System.out.println("\nTest Result is Passed");
		 	ExcelUtils.setCellData(filePath, "Amazon",i,4,"Passed");					
		 }
		else
		 {
			System.out.println("\nTest Result is Failed");
			ExcelUtils.setCellData(filePath, "Amazon",i,4,"Failed");
		 }
		}	
	}
	
	@AfterClass
    public void afterclass() throws InterruptedException {
		//Closing the browser
		OnlineMobileSearch.closeBrowser();
	}
}