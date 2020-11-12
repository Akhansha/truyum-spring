package com.cognizant.truyum;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.truyum.model.MenuItem;
/**
 * truYum Case
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        List<MenuItem> menuItems = (List<MenuItem>) ctx.getBean("menuItemList");
        for(MenuItem item : menuItems) {
        	System.out.println(item);
        }
    }
}
