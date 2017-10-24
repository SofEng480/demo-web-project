/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csupomona.cs480;

import java.io.IOException;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author Josh
 */
public class JSoupTest 
{
    private String head = "<head> \n" +
                          " <meta charset=\"utf-8\"> \n" +
                          " <title>Connect2</title> \n" +
                          " <link rel=\"stylesheet\" href=\"css/bootstrap.css\"> \n" +
                          " <link rel=\"stylesheet\" href=\"css/jquery.barCharts.css\"> \n" +
                          " <link rel=\"stylesheet\" href=\"css/style.css\"> \n" +
                          "</head>";
    
    // Test if JSoup.connect is returning the correct html data. Useful test if the BRIC
    // website changes thier html
    @Test
    public void testConnection() throws IOException
    {
        Document doc = Jsoup.connect("http://connect2concepts.com/connect2/?type=circle&key=79E7D1A1-A9F2-400C-AFEA-27BD152A15DA").get();
        
        assertEquals(head, doc.head());
    }
}
