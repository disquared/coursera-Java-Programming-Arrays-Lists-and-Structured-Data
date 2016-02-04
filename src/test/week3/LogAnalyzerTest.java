package week3;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LogAnalyzerTest
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	analyzer.printAll();
    }
    
    public static void main(String[] args) {
    	LogAnalyzerTest test = new LogAnalyzerTest();
    	test.testLogAnalyzer();
    }
}
