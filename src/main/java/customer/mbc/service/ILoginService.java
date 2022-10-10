package customer.mbc.service;

import customer.mbc.entity.CustomerDetails;
import customer.mbc.entity.Response;

public interface ILoginService {

	public Response getUsernameByName(CustomerDetails customerDetails);

	public Response saveCustomerDetails(CustomerDetails customerDetails);

}
