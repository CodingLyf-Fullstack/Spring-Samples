package com.filter.filtersample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	@GetMapping(path = "/log")
	public String getString() throws InterruptedException {
		System.out.println("Controller is invoked");
		Thread.sleep(1000);
		
		return "Hello world";
	}
}