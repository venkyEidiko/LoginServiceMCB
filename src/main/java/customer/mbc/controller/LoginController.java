package customer.mbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customer.mbc.entity.CustomerDetails;
import customer.mbc.entity.LoginResponse;
import customer.mbc.entity.Response;
import customer.mbc.service.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Authenticate")
@Slf4j
public class LoginController {

	@Autowired
	LoginServiceImpl loginServiceImpl;

	@PostMapping("/saveCustomerDetails")
	public Response saveCustomerDetails(@RequestBody  CustomerDetails customerDetails) {
		log.info("calling saveCustomerDetails in controler::");

		return loginServiceImpl.saveCustomerDetails(customerDetails);

	}

	@PostMapping("/login")
	public LoginResponse validation(@RequestBody CustomerDetails customerDetails) {
		log.info("REquest Body::"+customerDetails.toString());

		return loginServiceImpl.getUsernameByName(customerDetails);

	}

}
