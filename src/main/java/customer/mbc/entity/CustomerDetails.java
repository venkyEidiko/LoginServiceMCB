package customer.mbc.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customer_details")
public class CustomerDetails {
	@Id
	@Column(name = "Name")
	String name;
	@Column(name = "password")
	String password;
	@Column(name="isLocked")
	Boolean isLocked;
	@Column(name="login_attempt")
	Integer loginAttempt;
	@Column(name="locked_time")
	Timestamp loackedTime;
}
