package za.co.fenya.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InvoiceBean {
	
	private String invoiceNumber;
	private String invoiceDate;
	private String invoiceDueDate;
	private String invoicePeriod; // example April 2019
	
	//role = "Bill To" , role = "Bill From" role = "Remit To" role = "Sold To"
	private String []contactRole;
	private String [] contactName;
	private String [] street;
	private String [] city;
	private String [] state;
	private String [] postalCode;
	
	
	
	private boolean invoicePayed;
	
	
	
	private String insertDate;
	private String serialNUmber;
	private String customerName;
	
}
