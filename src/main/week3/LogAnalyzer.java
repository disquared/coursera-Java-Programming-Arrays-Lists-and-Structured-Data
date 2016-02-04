package week3;

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
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
