
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="READING")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reading implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="Record_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long recordID;
	@Column(name="A3_Mono_P_Start")
	private String a3MonoPrintStart;
	
	@Column(name="A3_Mono_P_Close")
	private String a3MonoPrintClose;
	
	@Column(name="A3_Mono_C_Start")
	private String a3MonoCopyStart;
	
	@Column(name="A3_Mono_C_Close")
	private String a3MonoCopyClose;
	
	@Column(name="A3_Color_P_Start")
	private String a3ColorPrintStart;
	
	@Column(name="A3_Color_P_Close")
	private String a3ColorPrintClose;
	
	@Column(name="A3_Color_C_Start")
	private String a3ColorCopyStart;
	
	@Column(name="A3_Color_C_Close")
	private String a3ColorCopyClose;
	
	
	@Column(name="A4_Mono_P_Start")
	private String a4MonoPrintStart;
	
	@Column(name="A4_Mono_P_Close")
	private String a4MonoPrintClose;
	
	@Column(name="A4_Mono_C_Start")
	private String a4MonoCopyStart;
	
	@Column(name="A4_Mono_C_Close")
	private String a4MonoCopyClose;
	
	@Column(name="A4_Color_P_Start")
	private String a4ColorPrintStart;
	
	@Column(name="A4_Color_P_Close")
	private String a4ColorPrintClose;
	
	@Column(name="A4_Color_C_Start")
	private String a4ColorCopyStart;
	
	@Column(name="A4_Color_C_Close")
	private String a4ColorCopyClose;
	
	@Column(name="Start_Mono_Total")
	private String startMonoTotal;
	
	@Column(name="Close_Mono_Total")
	private String closeMonoTotal;
	
	@Column(name="Start_Color_Total")
	private String startColorTotal;
	
	@Column(name="Close_Color_Total")
	private String closeColorTotal;
	
	@Column(name="Reading_Month")
	private String readingMonth;
	
	@Column(name="Reading_Year")
	private String readingYear;
	
	@Column(name="Insert_Date")
	private String insertDate;
	
	@Column(name="Invoiced_Flag")
	private String invoicedFlag;
	
	@OneToOne
	@JoinColumn(name="Invoice")
	private Invoice invoiceNumber;
	
	@ManyToOne
	@JoinColumn(name="Created_By")
	private Employee employee;
	
}
