package fr.gondyb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VilleController {
	
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public String appelGet() {
		System.out.println("Appel Get");
		
		// TODO BLO
		
		return "JSON";
	}
}
