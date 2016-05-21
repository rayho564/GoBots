package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FireCaseV4ForCSGODouble {
    static WebDriver driver;
    static Wait<WebDriver> wait;
    
    public static void bet(int amount){
    	
    	String plusOne = "//div[@id='mainpage']/div[4]/div/div/button[3]";
        String plusTen = "//div[@id='mainpage']/div[4]/div/div/button[4]";
        String plusHundred = "//div[@id='mainpage']/div[4]/div/div/button[5]";
        String plusThousand = "//div[@id='mainpage']/div[4]/div/div/button[6]";
        String clear = "//div[@id='mainpage']/div[4]/div/div/button";

    	
    	driver.findElement(By.xpath(clear)).click();
        
    	while( amount > 0)
    	{
	    	if( amount < 10)
	    	{
	    		driver.findElement(By.xpath(plusOne)).click();
	    		amount -= 1;
	    	}
	    	else if(amount >= 10 && amount < 100)
	    	{
	    		driver.findElement(By.xpath(plusTen)).click();
	    		amount -= 10;
	    	}
	    	else if(amount >= 100 && amount < 1000)
	    	{
	    		driver.findElement(By.xpath(plusHundred)).click();
	    		amount -= 100;
	    	}
	    	else if(amount >= 1000)
	    	{
	    		driver.findElement(By.xpath(plusThousand)).click();
	    		amount -= 1000;
	    	}
    	}

    }
    
    public static int bet(int baseBet, int times){
    	String timesTwo = "//div[@id='mainpage']/div[4]/div/div/button[8]";
    	String clear = "//div[@id='mainpage']/div[4]/div/div/button";
    	int betAmount = baseBet;
    	
    	driver.findElement(By.xpath(clear)).click();
    	bet(baseBet);
    	while(times > 0 )
    	{
    		pause(100);
    		driver.findElement(By.xpath(timesTwo)).click();
    		betAmount *= 2;
    		times--;
    	}
    	
    	return betAmount;
    }
    
    //pause in ms
    public static void pause (int waitTime){
    	try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void removeExtra(){
    	while( driver.findElements(By.xpath("//div[@id='mainpage']/div/button")).size() != 0){
    		driver.findElement(By.xpath("//div[@id='mainpage']/div/button")).click();
    		pause(1000);
		}
    }
    
    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10000);
        driver.get("http://www.csgodouble.com/");
        
        String presence = "//ul[2]/li/a";
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(presence)));
        
        driver.findElement(By.xpath("//div[@id='navbar']/ul[2]/li/a")).click();
        driver.findElement(By.xpath("//div[@id='navbar']/ul[2]/li/ul/li[5]/a")).click();
        pause(1000);
        driver.findElement(By.xpath("//div[@id='settingsModal']/div/div/div[2]/form/div/label/input")).click();
        driver.findElement(By.xpath("//div[@id='settingsModal']/div/div/div[3]/button[2]")).click();
     
        System.out.println("waitover");
        int blackCount = 0, redCount = 0;
        int inaRow = 1;
        int max = 16;
        int baseBet = 2;
        int betAmount = 0;
        int times = 1;
        //if we win we want to reset variable
        boolean win = true;
        //if redwins we want to switch sides
        boolean redWin = true;
        
        String redHistory = "//div[3]/div/div[10][@class='ball ball-1']";
        String redzero = "//div[3]/div/div[9][@class='ball ball-1']";
        String blackHistory = "//div[3]/div/div[10][@class='ball ball-8']";
        String blackzero = "//div[3]/div/div[9][@class='ball ball-8']";
        String zeroHistory = "//div[3]/div/div[10][@class='ball ball-0']";
        String clear = "//div[@id='mainpage']/div[4]/div/div/button";
    
        //CSGO Double point value are higher by x10
        
        String betRed = "//div[@id='panel1-7']/div/button";
        String betBlack = "//div[@id='panel8-14']/div/button";
        String timesTwo = "//div[@id='mainpage']/div[4]/div/div/button[8]";
        final String firstTime = "^Rolling in 30[\\s\\S]*$";
        //String timeThirty = "//div[6]/div[2][@style='Rolling in 30']";
        final String secondTime = "^Rolling in 15[\\s\\S]*$";
        
        while(blackCount < 15 && redCount < 15){
        	
        	pause(3000);
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
        			removeExtra();
        			String presence = "//ul[2]/li/a";
        			if( driver.findElements(By.xpath(presence)).size() != 0){
        				System.out.println("pass");
        				return true;
        			}
        			else{ 
        				System.out.println("fail");
        				driver.get(driver.getCurrentUrl());
        				return false;
        			}
	                    /*if(driver.findElement(By.xpath("//body")).getText().matches("^Connection failed[\\s\\S]*$"))
	                    {
	                    	System.out.println("Error");
	                    	driver.get(driver.getCurrentUrl());
	                    	return false;
	                    }
	                    else{
	                    	System.out.println("pass");
	                    	return true;
	                    }*/
        			
               }
        	});
        	
        	
        	removeExtra();
        	
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[1]/span")).getText().matches(firstTime)) 
                    	return true;
                    else
                       return false;
               }
        	});
        	 
        	
        	//if it is red we reset count and set win to true to start at 10 later
        	//if we win we reset value also
        	if(driver.findElements(By.xpath(redHistory)).size() != 0 && blackCount >= inaRow){
        		win = true;
        		betAmount = baseBet;
        		times = 1;

        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        		
        	}
        	else if(driver.findElements(By.xpath(blackHistory)).size() != 0 && redCount >= inaRow){
        		win = true;

        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        		betAmount = baseBet;
        		times = 1;

        		
        	}
        	//If black or zero count goes up
        	if( (driver.findElements(By.xpath(blackHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(blackzero)).size() != 0) ) ){
        		blackCount++;
        		redCount = 0;
        	}
        	//if red or zero count goes up
        	else if( (driver.findElements(By.xpath(redHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(redzero)).size() != 0 ) ) ){
        		redCount++;
        		blackCount = 0;
        	}
        	
        	//Long streaks are dangerous
        	if(blackCount < max && redCount < max){
	        	//if we won before and count is greater than inaRow we start with 10
	        	if( blackCount == inaRow && win == true){
	        		
	        		bet(baseBet);
	        		betAmount = baseBet;
	        		//red
	        		driver.findElement(By.xpath(betRed)).click();
	        		win = false;
	        	}
	        	else if(redCount == inaRow && win == true){
	        		bet(baseBet);
	        		betAmount = baseBet;
	        		//black
	        		driver.findElement(By.xpath(betBlack)).click();
	        		win = false;
	        	}
	        	//else black was inaRow+1 in a row or more but we still haven't won we double
	        	if( blackCount >= inaRow+1 && win == false){
	        		//x2
	        		betAmount = bet(baseBet, times);
	        		times++;
	        		//red
	        		driver.findElement(By.xpath(betRed)).click();
	        	}
	        	else if(redCount >= inaRow+1 && win == false){
	        		//x2
	        		betAmount = bet(baseBet, times);
	        		times++;

	        		//black
	        		driver.findElement(By.xpath(betBlack)).click();
	        	}
        	}
        	
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[1]/span")).getText().matches(secondTime)) 
                    	return true;
                    else
                       return false;
               }
        	});
        	System.out.println("blackCount:" + (blackCount));
        	System.out.println("redCount" + (redCount));
        	System.out.println("betAmount" + (betAmount));
        	System.out.println(win);
        	
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[1]/span")).getText().matches("^Rolling in 0[\\s\\S]*$")) 
                    {
                    	return true;
                    	
                    }
                    else
                    	return false;
                       
               }
        	});

        	pause(11000);
        	
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
        			removeExtra();
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[1]/span")).getText().matches("^Rolling in 0.00[\\s\\S]*$")
                    		|| driver.findElement(By.xpath("//div[@id='mainpage']/div[1]/span")).getText().matches("^Confirming[\\s\\S]*$")) 
                    {
                    	driver.get(driver.getCurrentUrl());
                    	return false;
                    }
                    else
                       return true;
               }
        	});
        	
        }
        /*1 +10
        2 red
        3 x2
        4 trash*/
        
		//Closing browser
		driver.close();
    }
}