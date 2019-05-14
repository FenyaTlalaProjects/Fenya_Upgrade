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
@Table(name= "Invoice_Payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoicePayment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	@Column(name="Invoice_Date")
	private String invoiceDate;
	@Column(name="Invoice_Due_Date")
	private String invoiceDueDate;
	@Column(name="Invoice_Paid")
	private boolean isPaid;
	@Column(name="Actual_Pay_Date")
	private String actualPayDate;
	@Column(name="Currency")
	private String currency;
	@Column(name="Insert_Date")
	private String insertDate;
	@Column(name="Payment_Terms")
	private String paymentTerms;
	@Column(name="Pay_In_Days")
	private String payInNumberOfDays;
	@Column(name="Discount")
	private String discount;
	@Column(name="VAT")
	private String vat;
	@Column(name="VAT_Amount")
	private String vatAmount;
	@Column(name="Sub_Total")
	private String subTotal;   // Before adding VAT
	@Column(name="Total")
	private String total;   // After adding VAT
	@Column(name="Supplier_Vat_ID")
	private String supplierVatID;		  //Velaphanda VAT ID
	@Column(name="Buyer_Vat_ID")         
	private String buyerVatID;           //Customer VAT ID
	
	@Column(name="Buyer_Company_Number")         
	private String buyerCompanyNumber;
	
	@Column(name="Supplier_Company_Number")         
	private String SupplierCompanyNumber;
	
	
	@ManyToOne
	@JoinColumn(name="Invoice_Number")
	private InvoiceHeader invoiceNumber;
	
	
}
