package com.company.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.registration.model.RegisterElements;
import com.company.registration.model.SignInElements;
import com.company.registration.services.RegisterService;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
import com.stripe.model.Application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegistrationController {
	
	 private static final Logger logger = LogManager.getLogger(RegistrationController.class.getName());

	
	 @Value("#{systemEnvironment['LOG_PATTERN'] ?: 'DEFAULT_VALUE'}")
	 private String COMPONENT_PARAM_CORS;
	 
	 @Autowired
	 RegisterService registerService;
	
	@PostMapping(value = "/addregister")//@RequestBody @Valid
	@ResponseStatus
	public ResponseEntity Register( @RequestBody RegisterElements registerElements)  {
		
	return registerService.registerAccount(registerElements);
	}
	
	@GetMapping(value = "/activateRegister")//@RequestBody @Valid
//	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity activateRegister(@RequestParam String token){
		return registerService.activateAccount(token);
	}
	
	@PostMapping(value = "/signin")//@RequestBody @Valid
	public ResponseEntity SignIn(@RequestBody SignInElements signinElements) throws Exception   {
		System.out.println(COMPONENT_PARAM_CORS+"@@@@");
		 
		 System.out.println("daiz");
	        logger.info("Info log");
	        logger.warn("Hey, This is a warning!");
	        logger.error("hahahahhaha");
	        //logger.fatal("Damn! Fatal error. Please fix me.");
	return	registerService.SignInAccount(signinElements);
	}
	
	@PostMapping(value = "/adminsignin")//@RequestBody @Valid
	public ResponseEntity adminSignIn(@RequestBody SignInElements signinElements) throws Exception   {
	return	registerService.adminSignInAccount(signinElements);
	}
}