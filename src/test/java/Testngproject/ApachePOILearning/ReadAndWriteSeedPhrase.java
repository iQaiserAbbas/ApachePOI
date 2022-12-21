package Testngproject.ApachePOILearning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadAndWriteSeedPhrase {
	
	public static void main(String[] args) throws IOException {
		
		
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();  
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://sonar-wallet.web.app/index.html");  
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[2]/div/div[3]/div")).sendKeys("12345678"); //Typing Password
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[2]/div/div[3]/div")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[3]/div/div[2]/div")).sendKeys("12345678"); //Confirming Password
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[3]/div/div[2]/div")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[4]/div/div[6]")).click(); //clicking on "ok let's go" button
		
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]")).click(); //clicking on "Start with a New Wallet" button
		
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/button/div")).click(); //Clicking on "Go" button
		
		driver.findElement(By.xpath("/html/body/div/div/div/div[4]/div[1]/div/div")).click(); //Clicking on "Tap to reveal" button
		
		String word0 = driver.findElement(By.id("SeedPhraseView-word-0")).getText(); //getting first word of seed phrase
		System.out.println(word0);
		
		String word1 = driver.findElement(By.id("SeedPhraseView-word-1")).getText();
		System.out.println(word1);
		
		String word2 = driver.findElement(By.id("SeedPhraseView-word-2")).getText();
		System.out.println(word2);
		
		String word3 = driver.findElement(By.id("SeedPhraseView-word-3")).getText();
		System.out.println(word3);
		
		String word4 = driver.findElement(By.id("SeedPhraseView-word-4")).getText();
		System.out.println(word4);
		
		String word5 = driver.findElement(By.id("SeedPhraseView-word-5")).getText();
		System.out.println(word5);
		
		String word6 = driver.findElement(By.id("SeedPhraseView-word-6")).getText();
		System.out.println(word6);
		
		String word7 = driver.findElement(By.id("SeedPhraseView-word-7")).getText();
		System.out.println(word7);
		
		String word8 = driver.findElement(By.id("SeedPhraseView-word-8")).getText();
		System.out.println(word8);
		
		String word9 = driver.findElement(By.id("SeedPhraseView-word-9")).getText();
		System.out.println(word9);
		
		String word10 = driver.findElement(By.id("SeedPhraseView-word-10")).getText();
		System.out.println(word10);
		
		String word11 = driver.findElement(By.id("SeedPhraseView-word-11")).getText();
		System.out.println(word11);
		
		Object seeddata[][]= {	{word0, word1, word2, word3, word4, word5, word6, word7, word8, word9, word10, word11 } }; //saving seed phrase in object array
		
		XSSFWorkbook workbook = new XSSFWorkbook(); //creating workbook
		XSSFSheet sheet = workbook.createSheet("SeedPhrase"); //creating sheet in workbook
		
		int rows = seeddata.length; //returning rows of 2d array
		int cols = seeddata[0].length; //going to row 1 and getting total cells (columns)
		
		System.out.println(rows);
		System.out.println(cols);
		
		for (int r=0; r<rows; r++)
		{
		
		XSSFRow row= sheet.createRow(r); //creating 1 row in excel sheet
		
			for (int c=0; c<cols; c++)
			{
				XSSFCell cell= row.createCell(c); //create cell number c in 1st row
				
				Object value = seeddata[r][c];
				cell.setCellValue((String)value); //writing first word in excel in string (not object)

			}
		}
		
		String excelFilePath = ".\\DataFiles\\SeedPhrase.xlsx";
		FileOutputStream outputstream = new FileOutputStream(excelFilePath); //opening output stream on excelfilepath
		workbook.write(outputstream);
		
		outputstream.close(); //closing output stream
		
		System.out.println("Seedphrase Excel File is Created Successfully");
		
		driver.findElement(By.id("SeedPhraseView-setIsModalOpen")).click(); //Clicking on "Got it" button
		
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/button/span")).click(); //Clicking on "Let's Go" button
		
		
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook1 = new XSSFWorkbook(inputstream); //getting workbook from file
		XSSFSheet sheet1 = workbook.getSheet("SeedPhrase"); //getting sheet in workbook 
		
		String[] Seedphrase = new String[12]; 

			Iterator iterator=sheet.iterator();
			XSSFRow row=(XSSFRow) iterator.next(); //returns first row of file
			Iterator cellIterator=row.cellIterator(); 
			
			//System.out.println(Arrays.toString(Seedphrase));
			//System.out.println(cellIterator);
			
			int x = 0;
			
			while(cellIterator.hasNext()) // to read all cells of row
			{
				XSSFCell cell=(XSSFCell) cellIterator.next();
				Seedphrase [x] = cell.getStringCellValue();
				x++;
			}

		System.out.println(Arrays.toString(Seedphrase));		
		
		
		String[] randomSeedphrase = new String[12];
		
		randomSeedphrase[0] = driver.findElement(By.xpath("//*[@data-projection-id=\"16\"]")).getText(); //hardcoded xpath?
		System.out.println(randomSeedphrase[0]);
		
		randomSeedphrase[1] = driver.findElement(By.xpath("//*[@data-projection-id=\"17\"]")).getText(); 
		System.out.println(randomSeedphrase[1]);
		
		randomSeedphrase[2] = driver.findElement(By.xpath("//*[@data-projection-id=\"18\"]")).getText(); 
		System.out.println(randomSeedphrase[2]);
		
		randomSeedphrase[3] = driver.findElement(By.xpath("//*[@data-projection-id=\"19\"]")).getText(); 
		System.out.println(randomSeedphrase[3]);
		
		randomSeedphrase[4] = driver.findElement(By.xpath("//*[@data-projection-id=\"20\"]")).getText(); 
		System.out.println(randomSeedphrase[4]);
		
		randomSeedphrase[5] = driver.findElement(By.xpath("//*[@data-projection-id=\"21\"]")).getText(); 
		System.out.println(randomSeedphrase[5]);
		
		randomSeedphrase[6] = driver.findElement(By.xpath("//*[@data-projection-id=\"22\"]")).getText(); 
		System.out.println(randomSeedphrase[6]);
		
		randomSeedphrase[7] = driver.findElement(By.xpath("//*[@data-projection-id=\"23\"]")).getText(); 
		System.out.println(randomSeedphrase[7]);
		
		randomSeedphrase[8] = driver.findElement(By.xpath("//*[@data-projection-id=\"24\"]")).getText(); 
		System.out.println(randomSeedphrase[2]);
		
		randomSeedphrase[9] = driver.findElement(By.xpath("//*[@data-projection-id=\"25\"]")).getText(); 
		System.out.println(randomSeedphrase[9]);
		
		randomSeedphrase[10] = driver.findElement(By.xpath("//*[@data-projection-id=\"26\"]")).getText(); 
		System.out.println(randomSeedphrase[10]);
		
		randomSeedphrase[11] = driver.findElement(By.xpath("//*[@data-projection-id=\"27\"]")).getText(); 
		System.out.println(randomSeedphrase[11]);

		
		//System.out.println(randomSeedphrase);
		//System.out.println(Seedphrase);
		
		System.out.println(Arrays.toString(randomSeedphrase));
		System.out.println(Arrays.toString(Seedphrase));
		
		/* 
		 for (int i = 0; i < Seedphrase.length; i++) {
		 
			  System.out.println(Seedphrase[i]);
			}
		
		for (int i = 0; i < randomSeedphrase.length; i++) {
			  System.out.println(randomSeedphrase[i]);
			}
		
		*/
		
		for (int i = 0; i <= Seedphrase.length - 1; i++)
		{
		 for (int j = 0; j <= randomSeedphrase.length - 1; j++) 
		 {
		        
		        if (Seedphrase[i].equals(randomSeedphrase[j])) 
		        {
		        	int t = j + 16;
		        	String click = "//*[@data-projection-id=" + "\"" + t + "\"]";		        	
		        	driver.findElement(By.xpath(click)).click();
		        }
		        
		        else 
		        {
		        	System.out.println("just another syso");
		        }
		  
		  } //inner loop
		 } //outer loop
		
		driver.findElement(By.xpath("//*[@id=\"Gonext-handleNext\"]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"DefaultWallet-openWallet\"]")).click();
	}
}