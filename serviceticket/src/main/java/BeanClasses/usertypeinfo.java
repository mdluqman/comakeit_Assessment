package BeanClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;

@Entity
@NamedNativeQueries({@NamedNativeQuery(name="selectusertype", query="SELECT * from usertypeinfo usertype", resultClass = usertypeinfo.class)})

public class usertypeinfo {
@Id
int userTypeId;
String typeOfUser;

public int getUserTypeId() {
	return userTypeId;
}
public void setUserTypeId(int userTypeId) {
	this.userTypeId = userTypeId;
}
public String getTypeOfUser() {
	return typeOfUser;
}
public void setTypeOfUser(String typeOfUser) {
	this.typeOfUser = typeOfUser;
}

}
