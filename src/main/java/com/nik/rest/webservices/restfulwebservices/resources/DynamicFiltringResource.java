package com.nik.rest.webservices.restfulwebservices.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nik.rest.webservices.restfulwebservices.model.SomeBean;

@RestController
public class DynamicFiltringResource {

	@GetMapping("/daynimicFiltring")
	public MappingJacksonValue getFilter() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1","feild2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("somebean", filter);
		MappingJacksonValue mappingValue = new MappingJacksonValue(someBean);
		mappingValue.setFilters(filterProvider);
		return mappingValue;
	}
	
	@GetMapping("/dynamicFiltring-list")
	public MappingJacksonValue getListOfFilters(){
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6") );
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1","feild3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("somebean", filter);
		MappingJacksonValue mappingValue = new MappingJacksonValue(someBeanList);
		mappingValue.setFilters(filterProvider);
		return mappingValue;
	}
}
