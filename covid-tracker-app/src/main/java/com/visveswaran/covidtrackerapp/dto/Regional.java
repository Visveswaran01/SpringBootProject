package com.visveswaran.covidtrackerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Regional {
	
	public String loc;
    public long confirmedCasesIndian;
    public long confirmedCasesForeign;
    public long discharged;
    public long deaths;
    public long totalConfirmed;
    
    
}
