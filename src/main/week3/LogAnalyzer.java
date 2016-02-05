package week3;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
    	 FileResource resource = new FileResource(filename);
    	 for (String line : resource.lines()) {
    		 LogEntry le = WebLogParser.parseEntry(line);
    		 records.add(le);
    	 }
     }
     
     public int countUniqueIPs() {
    	 List<String> uniqueIPs = new ArrayList<>();
    	 for (LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 if (!uniqueIPs.contains(ipAddr)) {
    			 uniqueIPs.add(ipAddr);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 List<String> uniqueIPs = new ArrayList<>();
    	 for (LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 int statusCode = le.getStatusCode();
    		 if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
    			 uniqueIPs.add(ipAddr);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public List<String> uniqueIPVisitsOnDay(String someday) {
    	 List<String> uniqueVisits = new ArrayList<>();
    	 for (LogEntry le : records) {
    		 Date accessTime = le.getAccessTime();
    		 String ipAddr = le.getIpAddress();
    		 Format formatter = new SimpleDateFormat("MMM dd");
    		 String formattedDate = formatter.format(accessTime);
    		 if (formattedDate.equals(someday) && !uniqueVisits.contains(ipAddr)) {
    			 uniqueVisits.add(ipAddr);
    		 }
    	 }
    	 return uniqueVisits;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
    	 System.out.println("All log entries with status higher than " + num + ":");
    	 for (LogEntry le : records) {
    		 int statusCode = le.getStatusCode();
    		 if (statusCode > num) {
    			 System.out.println(le);
    		 }
    	 }
     }
     
     
}
