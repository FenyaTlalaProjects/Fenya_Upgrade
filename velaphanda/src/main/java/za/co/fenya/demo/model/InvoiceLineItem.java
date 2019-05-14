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
@Table(name= "Invoice_Line_Item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceLineItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	@Column(name="Line_Number")
	private String lineNumber;
	@Column(name="Item_Description")
	private String itemDescription;
	@Column(name="Start_Reading")
	private String startReading;
	@Column(name="End_Reading")
	private String endReading;
	@Column(name="Unit_Of_Measure")
	private String unitOfMeasure;
	@Column(name="Quantity")
	private String quantity;
	@Column(name="Price")
	private String price;
	@Column(name="Currency")
	private String currency;
	@Column(name="Total")
	private String total;
	@Column(name="Line_Status")
	private String lineStatus;	   //Active / Inactive - only active lines will be considered
	
		
	@ManyToOne
	@JoinColumn(name="Invoice_Number")
	private InvoiceHeader invoiceNumber;
	
	
}
