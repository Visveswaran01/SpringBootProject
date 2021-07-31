package com.visveswaran.covidtrackerapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.visveswaran.covidtrackerapp.dto.Regional;
import com.visveswaran.covidtrackerapp.dto.Summary;
import com.visveswaran.covidtrackerapp.exception.StateNotFoundException;
import com.visveswaran.covidtrackerapp.service.CovidDataProvider;
import com.visveswaran.covidtrackerapp.view.View1;

@Controller
public class AppController {
	
	@Autowired
	private CovidDataProvider covidDataProvider;
	
	
	
	
	@GetMapping("/State/Covid-Data")
	public String getAllStateData(Model model) {
		
		
		List<View1> returnResponse = new ArrayList<>();
		int i = 1;
		try {
			
			List<Regional> data = covidDataProvider.getStatesCovidData();
			
			for(Regional t : data) {
				
				View1 singleResponse = new View1();
				
				singleResponse.setId(i);
				singleResponse.setLoc(t.getLoc());
				singleResponse.setConfirmedCasesIndian(t.getConfirmedCasesIndian());
				singleResponse.setDischarged(t.getDischarged());
				singleResponse.setDeaths(t.getDeaths());
				singleResponse.setTotalConfirmed(t.getTotalConfirmed());
				
				long active = ((t.getTotalConfirmed() - t.getDischarged()) - t.getDeaths());
				singleResponse.setActive(active);
				
				returnResponse.add(singleResponse);
				i++;
				
			}
			model.addAttribute("summaryData",covidDataProvider.summaryData());
			model.addAttribute("allData",returnResponse);
			model.addAttribute("newDate", covidDataProvider.updatedDate());
			model.addAttribute("newTime", covidDataProvider.updatedTime());
		 }
		catch (Exception e) {
			System.out.println("Error is From Server side ...");
		}
		
		return "main";
		
	}
	
	@GetMapping("/State/{name}/Covid-Data")
	public String getThatDataOnly(@PathVariable String name, Model model) {
		
		Regional res = null;
		try {
			res = covidDataProvider.findState(name);
			
		} catch (Exception e) {
			System.out.println("Error is From Server side ...");
		}
		
		if(res == null) {
			throw new StateNotFoundException();
			
		}
		long active = ((res.getTotalConfirmed() - res.getDischarged()) - res.getDeaths());
		model.addAttribute("singleStateData", res);
		model.addAttribute("active", active);
		
		return "individual";
	}

}
