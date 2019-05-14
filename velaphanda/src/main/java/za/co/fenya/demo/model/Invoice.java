
package za.co.fenya.demo.model;

import java.io.Serializable;
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
@Table(name="INVOICE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="Record_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long recordID;
	@Column(name="Invoice_Date")
	private String invoiceDate;
	@Column(name="Invoice_Due_Date")
	private String invoiceDueDate;
	@Column(name="Invoice_Payed")
	private String invoicePayed;
	@Column(name="Payment_Date")
	private String paymentDate;
	@Column(name="Insert_Date")
	private String insertDate;
	@Column(name="Approval_Required")
	private boolean approvalRequired;
	@Column(name="Approval_Date")
	private String approvalDate;
	
	@ManyToOne
	@JoinColumn(name="Serial_Number")
	private Device device;
	
	@ManyToOne
	@JoinColumn(name="Customer")
	private Customer customer;
    
	@ManyToOne
	@JoinColumn(name="Created_By")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="Approver")
	private Employee approver;
	
}
