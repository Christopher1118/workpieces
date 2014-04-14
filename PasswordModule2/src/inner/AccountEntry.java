package inner;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="password")
public class AccountEntry {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String context;
	private String domain;
	@Column(name ="userID")
	private String username;
	private String accountName;
	@Column(name ="psd")
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="date")
	private Date modifyTime;
	
	
	public AccountEntry(String username, String context, String domain, String accountName, String password, Date modifyTime){
		this.username = username;
		this.context = context;
		this.domain = domain;
		this.accountName=accountName;
		this.password=password;
		this.modifyTime = modifyTime;
	}
	
	public AccountEntry(String username, String context, String domain, String accountName, String password){
		this.username = username;
		this.context = context;
		this.domain = domain;
		this.accountName=accountName;
		this.password=password;
	}
	
	public AccountEntry() {
		
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
