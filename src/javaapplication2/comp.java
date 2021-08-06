package javaapplication2;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;


public class comp {
    String name;
    Float minPrice;
    Float fprice,aprice,cprice;
    String url;

    
    comp(String name)
    {
        this.name=name;
    }
    public void find() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connection="jdbc:mysql://localhost:3306/products?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(connection,"root","megha@2506");


            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from final_links where name='"+this.name+"'");
            while(rs.next()) {
                System.out.println(rs.getString(1) + "\n" + rs.getString(2) + "\n" + rs.getString(3));
                String url1 = rs.getString(2);
                String url2 = rs.getString(3);
                String url3 = rs.getString(4);
                System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("debuggerAddress","localhost:9014");

                WebDriver driver=new ChromeDriver();
                driver.get(url1);
                String price1;
                try{
                WebElement b_nuCI =driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
                price1=b_nuCI.getText();driver.close();}catch(Exception e1)
                {
                    price1="";
                    driver.close();
                }
                float price=Float.parseFloat(price1.replaceAll("[^0-9.]",""));
                this.fprice=price;
                
                System.out.println("Flipkart price= "+price1);
                System.out.println(price);
                
                
                WebDriver driver1=new ChromeDriver();
                String price2;
                driver1.get(url2);
                try{
                WebElement amazon=driver1.findElement(By.xpath("//span[@id='priceblock_dealprice']"));
                price2=amazon.getText();
                driver1.close();
                }catch(Exception e)
                {
                     WebDriver driverC=new ChromeDriver();
                    try{
                    driver1.close();
                   
                    driverC.get(url2);
                    WebElement amazon=driverC.findElement(By.xpath("//span[@id='priceblock_ourprice']"));
                    price2=amazon.getText();
                    driverC.close();
                    }
                    catch(Exception en)
                    {
                        price2="";
                        driverC.close();
                      
                    }
                }
                
                float price3=Float.parseFloat(price2.replaceAll("[^0-9.]",""));
                this.aprice=price3;
                System.out.println("Amazon price= "+this.aprice);
                if(this.aprice==null)
                {
                    this.aprice=Float.MAX_VALUE;
                }
                System.out.println(price3);
                
                
                WebDriver driver2=new ChromeDriver();
                driver2.get(url3);
                String price4;
                try{
                    WebElement croma=driver2.findElement(By.xpath("//span[@class='amount']"));
                    price4=croma.getText();
                    driver2.close();
                }
                catch(Exception e2)
                {
                    price4="";
                    
                }
                
                System.out.println("croma price= "+price4);
                float price5=Float.parseFloat(price4.replaceAll("[^0-9.]",""));
                this.cprice=price5;
                if(this.cprice==null)
                {
                    this.cprice=Float.MAX_VALUE;
                }
                System.out.println(price5);
             
                
                HashMap <String, Float>Map=new HashMap<>();
                if(this.fprice!=Float.MAX_VALUE)
                {
                Map.put("Flipkart",this.fprice);
                }
                if(this.aprice!=Float.MAX_VALUE)
                {
                Map.put("Amazon",this.aprice);
                }
                if(this.cprice!=Float.MAX_VALUE)
                {
                Map.put("Croma",this.cprice);
                }
                String res=new String();
                Float minValueInMap=(Collections.min(Map.values())); 
                minPrice=minValueInMap;
                for(String i: Map.keySet())
                {
                    if(Map.get(i)==minValueInMap)
                    {
                        res=i;
                        name=i;
                        break;
                    }
                }
                System.out.println(res+ " is cheaper, price= "+minValueInMap);
                HashMap <String, String>Map1=new HashMap<>();
                Map1.put("Flipkart",url1);
                Map1.put("Amazon",url2);
                Map1.put("Croma",url3);
                
                for(String i: Map1.keySet())
                {
                    if(i==res)
                    {
                        url=Map1.get(res);
                        break;
                    }
                }
                
                

                
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}



