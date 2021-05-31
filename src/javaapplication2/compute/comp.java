package javaapplication2.compute;
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


class Pricecomp {
    public String name;
    public Float minPrice;
    public String url;
    Pricecomp(String name)
    {
        this.name=name;
    }
    public void find() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connection="jdbc:mysql://localhost:3306/products?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(connection,"root","megha@2506");


            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from final_links where name='name'");
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
                WebElement b_nuCI =driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
                String price1=b_nuCI.getText();
                float price=Float.parseFloat(price1.replaceAll("[^0-9.]",""));
                System.out.println("Flipkart price= "+b_nuCI.getText());
                System.out.println(price);
                driver.close();
                
                WebDriver driver1=new ChromeDriver();
                driver1.get(url2);
                WebElement amazon=driver1.findElement(By.xpath("//span[@id='priceblock_dealprice']"));
                String price2=amazon.getText();
                System.out.println("Amazon price= "+price2);
                float price3=Float.parseFloat(price2.replaceAll("[^0-9.]",""));
                System.out.println(price3);
                driver1.close();
                
                WebDriver driver2=new ChromeDriver();
                driver2.get(url3);
                WebElement croma=driver2.findElement(By.xpath("//span[@class='amount']"));
                String price4=croma.getText();
                System.out.println("croma price= "+price4);
                float price5=Float.parseFloat(price4.replaceAll("[^0-9.]",""));
                System.out.println(price5);
                driver2.close();
                
                HashMap <String, Float>Map=new HashMap<>();
                Map.put("Flipkart",price);
                Map.put("Amazon",price3);
                Map.put("Croma",price5);
                String res=new String();
                Float minValueInMap=(Collections.min(Map.values()));  // This will return max value in the Hashmap
                this.minPrice=minValueInMap;
                for(String i: Map.keySet())
                {
                    if(Map.get(i)==minValueInMap)
                    {
                        res=i;
                        this.name=i;
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
                        this.url=Map1.get(res);
                        break;
                    }
                }
                
                

                
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}

class comp
{
    public static void main(String[] args) {
        
    }
}

