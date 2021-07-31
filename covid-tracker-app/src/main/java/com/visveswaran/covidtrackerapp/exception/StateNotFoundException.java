package com.visveswaran.covidtrackerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Given Sate is not found in our Data")
public class StateNotFoundException extends RuntimeException {

}
