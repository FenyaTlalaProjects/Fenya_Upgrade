package za.co.fenya.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="CustomerContactDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerContactDetails {

	@Id
	// ContactKey = Customer Name + Contact Type
	@Column(name = "Contact_Key", nullable = false) 
	private String contactKey;
	@Column(name="Contact_Email")
	private String contactEmail;
	@Column(name="First_Name")
	private String firstName;
	@Column(name="First_LastName")
	private String lastName;
	@Column(name="Contact_Telephone_Number")
	private String contactTelephoneNumber;
	@Column(name="Contact_Cell_Number")
	private String contactCellNumber;	
	@Column(name="Contact_Type")
	private String contactType;
	
	@ManyToOne
	@JoinColumn(name="Customer")
	private Customer customerContactDetails;
}
