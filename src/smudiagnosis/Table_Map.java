package smudiagnosis;

import java.util.*;

public class Table_Map {
	
	Map<String,String> returnmap;                                                                                        
	
	public Map<String, String> getImei()
	{
		returnmap = new HashMap();
		returnmap.put(new String("862951023282164"), "862951023282164");
		returnmap.put(new String("862951023270052"), "862951023270052");
		returnmap.put(new String("862951023267272"), "862951023267272");
		returnmap.put(new String("1.0.0.2.0.255"), "Firmware Version");
		returnmap.put(new String("0.0.94.91.9.255"), "Meter Type");
		returnmap.put(new String("0.0.94.91.11.255"), "Category");
		returnmap.put(new String("0.0.94.91.12.255"), "Current Rating");
		returnmap.put(new String("0.0.96.1.4.255"), "Meter Year of Manufacturer");
		returnmap.put(new String("0.0.1.0.0.255"), "Date and Time");
		returnmap.put(new String("0.0.15.0.0.255"), "Single action Schedule for Billing Dates");
		returnmap.put(new String("0.0.13.0.0.255"), "Activity Calender for Time Zones");
		returnmap.put(new String("0.0.22.0.0.255"), "RS 485 Device Address");
		returnmap.put(new String("0.0.44.0.0.255"), "Image Transfer");
		returnmap.put(new String("0.0.40.0.2.255"), "LLS Secret");
		returnmap.put(new String("0.0.40.0.3.255"), "HLS Key");
		returnmap.put(new String("0.0.40.0.5.255"), "HLS Key");
		returnmap.put(new String("0.0.43.0.0.255"), "Global Key Change");
		returnmap.put(new String("0.0.15.0.2.255"), "Image Activation Single Action Schedule");
		returnmap.put(new String("0.0.94.91.26.255"), "ESWF");
		returnmap.put(new String("0.0.10.0.1.255"), "MD Reset");
		returnmap.put(new String("1.0.0.4.2.255"), "Meter CTR");
		returnmap.put(new String("1.0.0.4.3.255"), "Meter PTR");
		returnmap.put(new String("1.0.0.8.0.255"), "Demand interval period");
		returnmap.put(new String("1.0.0.8.4.255"), "Load survey interval period");
		return returnmap;
		
	}
	
	
}
