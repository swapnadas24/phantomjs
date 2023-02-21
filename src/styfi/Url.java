package styfi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Url {
  @Test(priority=1)
  public void thread() throws BiffException, IOException, InterruptedException
  {
	//open excel file for read & write
	  File f=new File("/home/swapnamudra/myfolder/Excel/check.xls");
	  Workbook wb = Workbook.getWorkbook(f);
	  Sheet sh=wb.getSheet(0);
	  //WritableWorkbook wwb=Workbook.createWorkbook(f,wb);
	  //WritableSheet wsh = wwb.getSheet(0);
	  int nr=sh.getRows();	
	  int count=2;

	  //Data driven testing
	  for(int i=1;i<nr;i++)
	  {	
	   //Get Data driven from excel sheet
	    String url=sh.getCell(0,i).getContents();
	    DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("takesScreenshot", true);
	    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/home/swapnamudra/myfolder/Driver/phantomjs");
	    PhantomJSDriver obj = new PhantomJSDriver(caps);
	    obj.get(url);
	    obj.manage().window().maximize();
	    Thread.sleep(200);        
	  	Thread t = new Thread()
	  	{ 
	  	public void run()
	  	{
	  	synchronized(url)
	  	{
	       System.out.println(obj.getCurrentUrl());
	  	 try{
	  		 Thread.sleep(100);
	  		}catch(InterruptedException e) {}
	  	 }
	  	}
	  }; 
	  	t.start();                      
	int startlen = 10;
	  if (startlen <= nr) {
	  if(obj.getPageSource().contains("400 Bad Request"))
	  {
	   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE); 
	   File fail= new File("/home/swapnamudra/myfolder/picture/400/url" +count);
	   BufferedImage bi = ImageIO.read(src);
	   Thread.sleep(200);
	   ImageIO.write(bi,"png", fail);   
	  }               
	  else if(obj.findElement(By.xpath("no-result-title page_title_404")).isDisplayed())
	  {
		  File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		  File fail= new File("/home/swapnamudra/myfolder/picture/404/url" +count);
		  BufferedImage bi = ImageIO.read(src);
		  Thread.sleep(200);
		  ImageIO.write(bi,"png", fail); 	    
	  }
	  else if(obj.getPageSource().contains("405 - METHOD NOT ALLOWED - STYFI"))
	  {
//		System.out.println("Taking screenshot now"); 
		File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);   
		File fail= new File("/home/swapnamudra/myfolder/picture/405/url" +count);
		BufferedImage bi = ImageIO.read(src);
		Thread.sleep(200);
		ImageIO.write(bi,"png", fail);   
	  }
	  else if(obj.findElement(By.xpath("//h1[@class='no-result-title page_title_410']")).isDisplayed()) //** correct
	  {	  
	   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
	   File fail= new File("/home/swapnamudra/myfolder/picture/410/url" +count);
	   BufferedImage bi = ImageIO.read(src);
	   Thread.sleep(200);
	   ImageIO.write(bi,"png", fail);  
	  }
	  else if(obj.getPageSource().contains("500 - SERVER ERROR - STYFI"))   //**correct
	  {
		File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		File fail= new File("/home/swapnamudra/myfolder/picture/500/url" +count);
		BufferedImage bi = ImageIO.read(src);
		Thread.sleep(200);
		ImageIO.write(bi,"png", fail);   
	  }

	  else
	  {  
		File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
	   File pass= new File("/home/swapnamudra/myfolder/picture/pass/url" +count);
	   BufferedImage bi = ImageIO.read(src);
	   Thread.sleep(200);
	   ImageIO.write(bi,"png", pass);                 
	  }                                   
	  count++;
	  obj.close();
	  Thread.sleep(300);
	}
	startlen = startlen + 10;
	obj.quit();
	 }
	wb.close();
   }
	}

