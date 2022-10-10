package customer.mbc.configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import customer.mbc.dao.ILogin;
import customer.mbc.entity.CustomerDetails;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	ILogin loginDao;

	@Scheduled(fixedRate = 60000)
	public void performTask() {
		try {
			List<CustomerDetails> accountList = loginDao.getLoackedAccounts();
			log.info("Locked Accounts: " + accountList);
			if (accountList.isEmpty()) {
				log.info("No locked account found");
			} else {
				for (CustomerDetails customer : accountList) {
					// Long lockedTime = customer.getLoackedTime().getTime() + 86400000;
					 Long lockedTime = customer.getLoackedTime().getTime() + 60000;
					Long currentTime = System.currentTimeMillis();
					if (lockedTime < currentTime) {
						// unlock the account
						customer.setIsLocked(false);
						customer.setLoginAttempt(0);
						customer.setLoackedTime(null);
						loginDao.save(customer);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error: ", e.getMessage());
		}
	}

}