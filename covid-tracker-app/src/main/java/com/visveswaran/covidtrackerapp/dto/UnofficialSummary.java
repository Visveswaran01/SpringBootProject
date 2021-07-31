package com.visveswaran.covidtrackerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UnofficialSummary {
	
	public String source;
    public int total;
    public int recovered;
    public int deaths;
    public int active;
    
}
