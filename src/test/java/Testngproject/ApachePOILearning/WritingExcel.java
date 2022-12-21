package Testngproject.ApachePOILearning;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingExcel {
	
	public static void main(String[] args) throws IOException {
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("EmpInfo");
		
		Object empdata[][]= {	{"EmpID","Name","Job"},
								{101,"ABC","ENG"},
								{102,"Smith","MANAGER"},
								{103,"Scott","ANALYST"}};
		
		
				int rowCount=0;
				
				for(Object emp[]:empdata) // taking first row in emp variable
				{
					XSSFRow row=sheet.createRow(rowCount++);
					int columnCount=0;		
						for(Object value:emp) //taking first row and saving each value in "value"
						{
							XSSFCell cell=row.createCell(columnCount++); //create cell and save value in variable name "cell"
							
							if(value instanceof String)
									cell.setCellValue((String)value);
							if(value instanceof Integer)
									cell.setCellValue((Integer)value);
							if(value instanceof Boolean)
								cell.setCellValue((Boolean)value);	
									
						}
				}
				
				
				String filePath=".\\DataFiles\\Employee.xlsx";
				FileOutputStream outstream=new FileOutputStream(filePath);
				workbook.write(outstream);
				
				outstream.close();
				
				System.out.println("Employee.xls file written successfully...");
		
	}

}
