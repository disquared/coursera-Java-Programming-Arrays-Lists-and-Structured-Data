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
    
    public void testUniqueIP() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	System.out.println("The file has " + analyzer.countUniqueIPs() + " unique IPs");
    }
    
    public void testUniqueIPsInRange(int low, int high) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	System.out.println("The file has " + analyzer.countUniqueIPsInRange(low, high) + " unique IPs in range " + low + " to " + high);
    }
    
    public void testPrintAllHigherThanNum(int num) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	analyzer.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay(String someday) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog-short_log");
    	List<String> uniqueIPs = analyzer.uniqueIPVisitsOnDay(someday);
    	System.out.println("Unique IP visits on " + someday + ": " + uniqueIPs.size());
    }
    
    public static void main(String[] args) {
    	LogAnalyzerTest test = new LogAnalyzerTest();
    	test.testLogAnalyzer();
    	test.testUniqueIP();
    	test.testPrintAllHigherThanNum(300);
    	test.testUniqueIPVisitsOnDay("Sep 14");
    	test.testUniqueIPsInRange(300, 399);
    }
}
