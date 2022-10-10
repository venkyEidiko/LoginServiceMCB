package customer.mbc.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer.mbc.dao.ILogin;
import customer.mbc.entity.CustomerDetails;
import customer.mbc.entity.LoginResponse;
import customer.mbc.entity.Response;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {

	@Autowired
	ILogin loginDao;
	@Autowired
	Response response;

	@Override
	public LoginResponse getUsernameByName(CustomerDetails customerDetails) {
		LoginResponse loginResponse = new LoginResponse();
		log.info("calling getUsernameByName in service::");

		try {

			CustomerDetails details = loginDao.findById(customerDetails.getName()).get();
			log.info("Customer Details for login status::" + details.toString());
			if (details != null) {
				if (details.getIsLocked() ) {
					loginResponse.setStatus(403);
					loginResponse.setMessage("OOOPS!! Your Account is Locked Please try after some time.");
				} else {
					if (details.getPassword().equalsIgnoreCase(customerDetails.getPassword())
							&& details.getName().equalsIgnoreCase(customerDetails.getName())) {
						log.info("name & Password are matched successfully");

						loginResponse.setStatus(200);
						loginResponse.setMessage("Horrey!! Login Successfully");
						
						details.setLoginAttempt(0);
						details.setIsLocked(false);
						details.setLoackedTime(null);
						loginDao.save(details);
					} else {
						loginResponse.setStatus(401);
						loginResponse.setMessage("OOOPS! Invalid Name or Password");
						// update login failed count
						details.setLoginAttempt(details.getLoginAttempt() + 1);
						loginDao.save(details);
						// get updated details from DB and check for account lock
						details = loginDao.getById(details.getName());
						if (details.getLoginAttempt() > 2) {
							// lock the account
							details.setIsLocked(true);
							details.setLoackedTime(new Timestamp(System.currentTimeMillis()));
							loginDao.save(details);
						}
					}
				}

			} else {
				loginResponse.setStatus(404);
				loginResponse.setMessage("Not Found User under Name.");
			}
		} catch (Exception e) {
			log.info("Exception oocured while ::" + e.getMessage());
			loginResponse.setStatus(500);
			loginResponse.setMessage("Login Failed! ");
		}
		return loginResponse;
	}

	@Override
	public Response saveCustomerDetails(CustomerDetails customerDetails) {
		try {
			log.info("calling saveCustomerDetails in service");
			loginDao.save(customerDetails);
			response.setStatus(200);
			response.setMessage("Customer Deatils  record added");
		} catch (Exception e) {
			log.error("Exception raised due to::" + e.getMessage());
			response.setStatus(500);
			response.setMessage("failed to add Customer Deatils");
		}
		return response;
	}

}
