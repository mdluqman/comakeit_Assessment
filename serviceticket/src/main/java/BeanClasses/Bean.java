package BeanClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@NamedNativeQueries({@NamedNativeQuery(name="selectusers", query="SELECT * from authentication ", resultClass = Bean.class)})

@Table(name="authentication")
public class Bean {
	@Id
	private String username;
	@Column(name="passwords")
	private String password;
	@ManyToOne
	@JoinColumn(name = "userTypeId")
	private usertypeinfo usertype;

	
	public usertypeinfo getUsertype() {
		return usertype;
	}
	public void setUsertype(usertypeinfo usertype) {
		this.usertype = usertype;
	}
	public Bean(String username,String password)
	{
		this.username = username;
		this.password = password;
		//this.usertype. = type;
	}
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password= password;
	}
}

