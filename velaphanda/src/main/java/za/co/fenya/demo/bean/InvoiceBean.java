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
	
	//Invoice Header
	
	private String invoiceNumber;
	private String invoiceDate;
	private String invoiceDueDate;
	private String invoicePeriod; // example April 2019
	private String period;
	private String orderNumber;
	private String status;
	private boolean invoicePayed;
	private String insertDate;
	private String serialNumber;
	private String customerName;
	private String createdBy;
	private boolean approvalRequired;
	private String approvalDate;
	private String approver;
	
	//Invoice Contact Address
	
	private String [] role;
	private String [] name;
	private String [] street;
	private String [] city;
	private String [] Province;
	private String [] areaCode;
	private String [] country;
	private String [] telephoneNumber;
	private String [] faxNumber;
	private String [] emailAdress;
	private String [] contactPerson;
	private String [] cellNumber;
	private String [] contactTelephone;
	
	//Payment Details
		
	private String actualPayDate;
	private String currency;
	private String paymentTerms;
	private String payInNumberOfDays;
	private String discount;
	private String vat;
	private String vatAmount;
	private String subTotal;   // Before adding VAT
	private String total;   // After adding VAT
	private String supplierVatID;		  //Velaphanda VAT ID       
	private String buyerVatID;           //Customer VAT ID        
	private String buyerCompanyNumber;     
	private String SupplierCompanyNumber;
	
	// Line Item
	private String lineNumber;
	private String itemDescription;
	private String startReading;
	private String endReading;
	private String unitOfMeasure;
	private String quantity;
	private String price;
	private String lineCurrency;
	private String lineTotal;
	private String lineStatus;
	
	
}
