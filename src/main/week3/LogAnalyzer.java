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
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
     
     public Map<String, Integer> countVisitsPerIP() {
    	 Map<String, Integer> map = new HashMap<>();
    	 for (LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 if (map.containsKey(ipAddr)) {
    			 map.put(ipAddr, map.get(ipAddr) + 1);
    		 } else {
    			 map.put(ipAddr, 1);
    		 }
    	 }
    	 return map;
     }
     
     public int mostNumberVisitsByIP(Map<String, Integer> visitsPerIPMap) {
    	 int result = 0;
    	 for (Entry<String, Integer> entry : visitsPerIPMap.entrySet()) {
    		 if (entry.getValue() > result) {
    			 result = entry.getValue();
    		 }
    	 }
    	 return result;
     }
     
     public List<String> iPsMostVisits(Map<String, Integer> visitsPerIPMap) {
    	 int mostVisits = mostNumberVisitsByIP(visitsPerIPMap);
    	 Map<String, Integer> countVisitsPerIP = countVisitsPerIP();
    	 return countVisitsPerIP.entrySet()
    	 				 .stream()
    	 				 .filter(entry -> entry.getValue() == mostVisits)
    	 				 .map(Entry::getKey)
    	 				 .collect(toList());
     }
     
     public Map<String, List<String>> iPsForDays() {
    	 Map<String, List<String>> result = new HashMap<>();
    	 for (LogEntry le : records) {
    		 Date accessTime = le.getAccessTime();
    		 String ipAddr = le.getIpAddress();
    		 Format formatter = new SimpleDateFormat("MMM dd");
    		 String formattedDate = formatter.format(accessTime);
    		 List<String> ips = result.containsKey(formattedDate) ? result.get(formattedDate) : new ArrayList<>();
    		 ips.add(ipAddr);
    		 result.put(formattedDate, ips);
    	 }
    	 return result;
     }
     
     public String dayWithMostIPVisits(Map<String, List<String>> ipsForDays) {
    	 String dayWithMostIPVisits = "";
    	 int mostVisits = 0;
    	 for (Entry<String, List<String>> entry : ipsForDays.entrySet()) {
    		 if (entry.getValue().size() > mostVisits) {
    			 dayWithMostIPVisits = entry.getKey();
    			 mostVisits = entry.getValue().size();
    		 }
    	 }
    	 return dayWithMostIPVisits;
     }
     
     public List<String> iPsWithMostVisitsOnDay(Map<String, List<String>> ipsForDays, String someday) {
    	 //List<String> ipsOnDay = ipsForDays.get(someday);
    	 List<LogEntry> recordsOnDay = records.stream()
    	 		.filter(le -> new SimpleDateFormat("MMM dd").format(le.getAccessTime()).equals(someday))
    	 		.collect(Collectors.toList());
    	 Collections.sort(recordsOnDay, new Comparator<LogEntry>() {
			@Override
			public int compare(LogEntry o1, LogEntry o2) {
				return o1.getIpAddress().compareTo(o2.getIpAddress());
			}
		});
    	 Map<String, Integer> ipToNumVisitsMap = new HashMap<>();
    	 for (LogEntry le : recordsOnDay) {
    		 String ip = le.getIpAddress();
    		 if (ipToNumVisitsMap.containsKey(ip)) {
    			 ipToNumVisitsMap.put(ip, ipToNumVisitsMap.get(ip) + 1);
    		 } else {
    			 ipToNumVisitsMap.put(ip, 1);
    		 }
    	 }
    	 Integer maxOccurrences = Collections.max(ipToNumVisitsMap.values());
    	 List<String> ipsWithMostVisits = new ArrayList<>();
    	 for (Entry<String, Integer> entry : ipToNumVisitsMap.entrySet()) {
    		 if (entry.getValue() == maxOccurrences) {
    			 ipsWithMostVisits.add(entry.getKey());
    		 }
    	 }
    	 return ipsWithMostVisits;
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
