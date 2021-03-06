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


public class FireTestCaseV2 {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 60);
        driver.get("http://www.csgofast.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div[2]/ul/li[3]")));
        
        System.out.println("waitover");
        int count =  1;
        int inaRow = 3;
        //if we win we want to reset variable
        boolean win = true;
        //if redwins we want to switch sides
        boolean redWin = true;
        while(count < 11 && count < 11){
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[1]/div/div/div/div/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='30']")));
        	
        	//If black or green count goes up
        	if( (driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item black']")).size() != 0 ||
        			driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item green']")).size() != 0) &&
        		!redWin){
        		count++;
        	}
        	else if( (driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item red']")).size() != 0 ||
        			driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item green']")).size() != 0) &&
        			redWin){
        		count++;
        	}
        	//if it is red we reset count and set win to true to start at 10 later
        	//if we win we reset value also
        	if(driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item red']")).size() != 0 && redWin == false){
        		count = 1;
        		win = true;
        		redWin = true;
        		//clear value
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[2]")).click();
        	}
        	else if(driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item black']")).size() != 0 && redWin == true){
        		count = 1;
        		win = true;
        		redWin = false;
        		//clear value
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[2]")).click();
        	}
        	//if we won before and count is greater than inaRow we start with 10
        	if( count > inaRow && win == true && redWin == false){
        		//+10
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[3]")).click();
        		//red
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li")).click();
        		win = false;
        	}
        	else if(count > inaRow && win == true && redWin == true){
        		//+10
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[3]")).click();
        		//black
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li[3]")).click();
        		win = false;
        	}
        	//else black was inaRow+1 in a row or more but we still haven't won we double
        	if( count > inaRow+1 && win == false && redWin == false){
        		//x2
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[7]")).click();
        		//red
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li")).click();
        	}
        	else if(count > inaRow && win == true && redWin == true){
        		//x2
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[7]")).click();
        		//black
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li[3]")).click();
        	}
        	
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[1]/div/div/div/div/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='25']")));
        	System.out.println(count);
        	System.out.println(win);
        	
        }
        /*1 +10
        2 red
        3 x2
        4 trash*/
        
		//Closing browser
		driver.close();
    }
}