package customer.mbc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import customer.mbc.entity.CustomerDetails;
import customer.mbc.entity.Response;

@Repository
public interface ILogin extends JpaRepository<CustomerDetails, String> {
	public CustomerDetails getLoginAttemptByName(String name); 
	
	@Query(value = "select * from customer_details where is_locked=1",nativeQuery = true)
	public List<CustomerDetails> getLoackedAccounts();
}
