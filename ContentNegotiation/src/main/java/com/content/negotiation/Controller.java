package com.content.negotiation;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	
//	@RequestMapping(path = "/content", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	public User getUserWithAcceptHeader() {
//
//		User user = new User();
//		user.setId("1");
//		user.setName("name");
//
//		return user;
//	}
	
	
	@GetMapping(path = "/content")
	public User getUserWithContentNegotiation() {

		User user = new User();
		user.setId("1");
		user.setName("name");

		return user;
	}
}
