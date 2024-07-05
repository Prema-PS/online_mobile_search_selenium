package miniProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;   
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fis=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;		
	}

	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fis=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			//data=cell.toString(); this will take numeric values as decimal -> use DateFormatter 
			DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
            return data;
		}
		catch (Exception e) 
		{
			data="";
			System.out.println(e.getMessage());
		}
		wb.close();
		fis.close();
		return data;
	}

 
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fis=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fis);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fos=new FileOutputStream(xlfile);
		wb.write(fos);		
		wb.close();
		fis.close();
		fos.close();
	}
	

}
