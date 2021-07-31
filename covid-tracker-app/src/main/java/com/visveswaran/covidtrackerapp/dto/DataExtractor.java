package com.visveswaran.covidtrackerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DataExtractor {
	
	@JsonIgnore
	public boolean success;
    public CovidData data;
    public String lastRefreshed;
    public String lastOriginUpdate;
	
}
