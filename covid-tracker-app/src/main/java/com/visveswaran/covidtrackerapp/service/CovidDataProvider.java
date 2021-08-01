package com.visveswaran.covidtrackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visveswaran.covidtrackerapp.dto.DataExtractor;
import com.visveswaran.covidtrackerapp.dto.Regional;
import com.visveswaran.covidtrackerapp.dto.Summary;

@Service
public class CovidDataProvider {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//Api which provides Data
	private String url = "https://api.rootnet.in/covid19-in/stats/latest";
	
	//This method extracts and stores result in convenient format
	private DataExtractor fetchData() throws JsonMappingException, JsonProcessingException 
	{
		String jsonStr = restTemplate.getForObject(url,String.class);
		ObjectMapper om = new ObjectMapper();
		DataExtractor root = om.readValue(jsonStr,DataExtractor.class);
		return root;
		
	}
	
	//This method extracts summary 
	public Summary summaryData() throws JsonMappingException, JsonProcessingException {
				
		Summary result = fetchData().getData().getSummary();
		
		return result;
	}
	
	//This method extracts and returns the latest data of each states 
	public List<Regional> getStatesCovidData() throws JsonMappingException, JsonProcessingException {
		
		List<Regional> result = fetchData().getData().getRegional();
		return result;
	}
	
	//This method extract and returns the latest data of given parameter (state)
	public Regional findState(String state) throws JsonMappingException, JsonProcessingException  {
		List<Regional> allData;
		Regional res = null;
		allData = getStatesCovidData();
		for(Regional particularData : allData) 
		{
			if(particularData.getLoc().equalsIgnoreCase(state)) {
				res = particularData;
				break;
			}
				
		}
		
		return res;
	} 
	
	//This method extracts the updated Date of the data 
	public String updatedDate() throws JsonMappingException, JsonProcessingException {
		
		String date = "";
		
			String temp = fetchData().getLastRefreshed().substring(0, 10);
			
			date += temp.substring(8, 10);
			date += temp.substring(7, 8);
			date += temp.substring(5, 7);
			date += temp.substring(4,5);
			date += temp.substring(0, 4);
			
		return date;
	}
	
	//This method extracts the time at which the data is updated 
	public String updatedTime() throws JsonMappingException, JsonProcessingException {
				
			String time = "";
				
			String temp = fetchData().getLastRefreshed().substring(11, 19);
		
			//extract the hours,minutes,second from UTC
			int hrs = Integer.parseInt(temp.substring(0, 2));
			int min = Integer.parseInt(temp.substring(3, 5));
			int sec = Integer.parseInt(temp.substring(6, 8));
			
			//Conversion of UTC to IST  
			int carry = (min + 30) / 60;
			min = (min+30) % 60;	
			hrs = (hrs+carry+5) % 24;
		    
			//calculate time is converted and the result is passed
			time = hrs + " : " + min + " : " + sec;			
			return time;
				
	}
	

}
