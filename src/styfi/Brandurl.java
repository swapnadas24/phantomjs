package styfi;
import java.awt.image.BufferedImage;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import org.openqa.selenium.remote.DesiredCapabilities;
import jxl.Sheet;
import jxl.Workbook;

public class Brandurl
{
  public static void main(String[] args) throws Exception
 {
 //open excel file for read & write
  File f=new File("/home/swapnamudra/myfolder/Excel/urls.xls");
  Workbook wb = Workbook.getWorkbook(f);
  Sheet sh=wb.getSheet(0);
  int nr=sh.getRows();	
  int count=2;

  //Data driven testing
  for(int i=1;i<nr;i++)
  {	
  //Get Data driven from excel sheet
   String urls1="https://www.styfi.in";
   String urls2=sh.getCell(0,i).getContents();
   DesiredCapabilities caps = new DesiredCapabilities();
   caps.setCapability("takesScreenshot",false);
   caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/home/swapnamudra/myfolder/Driver/phantomjs");
   PhantomJSDriver obj = new PhantomJSDriver(caps);
   obj.get(urls1+urls2);
   obj.manage().window().setSize(new Dimension(1400,1000));//(1366, 768)  //obj.manage().window().setSize(new Dimension(1400,1000));
   Thread.sleep(2000);        
   Thread t = new Thread()
   { 
    public void run()
    {
     synchronized(urls1+urls2)
     {
    	 System.out.println(obj.getCurrentUrl());
         try{
            	Thread.sleep(100);
	        }catch(InterruptedException e) {}
 	 }
    }
   }; t.start();                    
   int startlen = 10;
   if (startlen <= nr) 
   {
	   URL url  = new URL(urls1+urls2);
	   HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	   conn.setRequestMethod("GET");
	   int status=conn.getResponseCode();
	   if(status==400)
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/400/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 
	   }
	   else if(status==401)
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/401/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 		
	   }
	   else if(status==402)
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/402/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 		
	   }
	   else if(status==403)
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/403/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 	
	   }
	   else if(status == 404 )
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/404/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 	
	   }
	   else if(status == 405)
	   {
		   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/405/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 	
	   }
	   else if(status==409)
	   {
		   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/409/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   Thread.sleep(200);
		   ImageIO.write(bi,"png", fail); 	
	   }
	   else if(status == 410)   //**correct
	   {
		   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/410/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail);  
	   }
	   else if(status == 500)   
	   {
		   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/500/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail);   
	   }
	   else if(status== 501)
	   {
		   File src = ((TakesScreenshot) obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/501/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 	
	   }
	   else if(status==503)
	   {
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File fail= new File("/home/swapnamudra/myfolder/picture/503/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   ImageIO.write(bi,"png", fail); 	
	   }       
	   else
	   {  
		   File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);  
		   File pass= new File("/home/swapnamudra/myfolder/picture/pass/url" +count);
		   BufferedImage bi = ImageIO.read(src);
		   Thread.sleep(200);
		   ImageIO.write(bi,"png", pass);                 
	   }                              
	   count++;
	   obj.quit();
	   Thread.sleep(200);
   }
   startlen = startlen +10;
   obj.quit();
  } 
  wb.close();   
 }
}



