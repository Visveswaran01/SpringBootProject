package com.visveswaran.covidtrackerapp.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class View1 {
	public int id;
	public String loc;
    public long confirmedCasesIndian;
    public long discharged;
    public long deaths;
    public long totalConfirmed;
    public long active;
    
}
