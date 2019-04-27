package za.co.fenya.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name= "Invoice_Header")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="RecordID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long recordID;
	@Column(name="Invoice_Date")
	private String invoiceDate;
	@Column(name="Insert_Date")
	private String insertDate;
	
		
	@ManyToOne
	@JoinColumn(name="Serial_Number")
	private Device device;
	
	@ManyToOne
	@JoinColumn(name="Created_By")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="Customer_Name")
	private Customer customerName;
	
	
}
