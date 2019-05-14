package za.co.fenya.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name= "Invoice_Address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceContactAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	@Column(name="Role")
	private String role;
	@Column(name="Name")
	private String name;
	@Column(name="Street")
	private String street;
	@Column(name="City")
	private String city;
	@Column(name="Province")
	private String Province;
	@Column(name="Area_Code")
	private String areaCode;
	@Column(name="Country")
	private String country;
	@Column(name="Telephone_Number")
	private String telephoneNumber;
	@Column(name="Fax_Number")
	private String faxNumber;
	@Column(name="Email_Address")
	private String emailAdress;
	
	@Column(name="Contact_Person")
	private String contactPerson;
	@Column(name="Cell_Number")
	private String cellNumber;
	@Column(name="Contact_Telephone")
	private String contactTelephone;
	
	

		
	@ManyToOne
	@JoinColumn(name="Invoice_Number")
	private InvoiceHeader invoiceNumber;
	
	
}
