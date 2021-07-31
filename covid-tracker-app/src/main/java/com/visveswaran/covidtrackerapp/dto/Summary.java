package com.visveswaran.covidtrackerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Summary {
	
	 	public long total;
	    public long confirmedCasesIndian;
	    
	    @JsonIgnore
	    public long confirmedCasesForeign;
	    
	    public long discharged;
	    public long deaths;
	    @JsonIgnore
	    public long confirmedButLocationUnidentified;
}
