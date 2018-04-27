package com.nik.rest.webservices.restfulwebservices.resources;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nik.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(value="/hello-world")
	public String helloWorld() {
		return "Hello-World";
	}
	
	@GetMapping(value="/hello-world-bean")
	public Object helloWorldBean() {
		
		return new HelloWorldBean("Hi I am Hello World Bean");
	}
	
	@GetMapping(value="/hello-world-bean/path-variable/{name}")
	public Object helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hi..%s", name));
	}
	
	@GetMapping(value="/hello-world-internationalize")
	public Object helloWorldInternationalization(@RequestHeader(name="Accept-language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
