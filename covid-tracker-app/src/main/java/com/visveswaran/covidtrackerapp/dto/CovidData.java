package com.visveswaran.covidtrackerapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CovidData {
	public Summary summary;
    @JsonProperty("unofficial-summary") 
    public List<UnofficialSummary> unofficialSummary;
    public List<Regional> regional;
    
}
