package com.nik.rest.webservices.restfulwebservices.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.rest.webservices.restfulwebservices.model.DisplayFirstNLastName;
import com.nik.rest.webservices.restfulwebservices.model.DisplayName;

@RestController
public class VersioningResource {

	@GetMapping("/V1/displayName")
	public DisplayName getName() {
		return new DisplayName("Nikhil Dubey");
	}
	
	@GetMapping("/V2/displayName")
	public DisplayFirstNLastName getFirstNLastName() {
		return new DisplayFirstNLastName("Nikhil", "Dubey");
	}
	
	//params versioning
	@GetMapping(value="/params/displayName",params="Version=1")
	public DisplayName getParamsVersioningName() {
		return new DisplayName("Nikhil Dubey");
	}
	
	@GetMapping(value="/params/displayName", params="Version=2")
	public DisplayFirstNLastName getParamsVersioningFnLName() {
		return new DisplayFirstNLastName("Nikhil", "Dubey");
	}
	//header versioning
	@GetMapping(value="/headers/displayName", headers="X-API-VERSION=1")
	public DisplayName getHeaderVersioningName() {
		return new DisplayName("Nikhil Dubey");
	}
	
	@GetMapping(value="/headers/displayName", headers="X-API-VERSION=2")
	public DisplayFirstNLastName getHeaderVersioningFnLName() {
		return new DisplayFirstNLastName("Nikhil", "Dubey");
	}
	
	//produces versioning
	@GetMapping(value="/headers/displayName", produces="application/v1-produces+json")
	public DisplayName getProducesVersioningName() {
		return new DisplayName("Nikhil Dubey");
	}
	
	@GetMapping(value="/headers/displayName", produces="application/v2-produces+json")
	public DisplayFirstNLastName getProducesVersioningFnLName() {
		return new DisplayFirstNLastName("Nikhil", "Dubey");
	}
}
