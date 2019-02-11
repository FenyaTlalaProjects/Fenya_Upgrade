package za.co.fenya.demo.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="UserCredentials")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Credentials {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="RecordID")
	private int recordID;
	@Column(name="User_Password")
	private String password;
	@Column(name="Password_InsertedDate")
	private String passwordDateInserted;
	@Column(name="Status")
	private String status;
	
	
	@Column(name="User_Name")
	private String email;

}
