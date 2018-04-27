package com.nik.rest.webservices.restfulwebservices.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.rest.webservices.restfulwebservices.model.SomeBean;

@RestController
public class StaicFiltringResource {

	@GetMapping("/filtring")
	public SomeBean getFilter() {
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtring-list")
	public List<SomeBean> getListOfFilters(){
		return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6") );
	}
}
