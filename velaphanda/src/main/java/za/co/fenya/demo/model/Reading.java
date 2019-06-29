
package za.co.fenya.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	//@GeneratedValue(generator="gen")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="Record_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long recordID;
	
	@Column(name="Mono_Reading")
	private String monoReading;
	
	@Column(name="Color_Reading")
	private String colorReading ;
	
	
	@Column(name="Previous_Color_Reading")
	private String previousColorReading;
	
	@Column(name="Previous_Mono_Reading")
	private String previousMonoReading;
	
	@Column(name="Reading_Period")
	private String readingPeriod;
	
	@Column(name="Reading_Status")
	private String readingStatus;
	
	@Column(name="Insert_Date")
	private String insertDate;
	
	@Column(name="Modified_Date")
	private String modifiedDate;
	
	@Column(name="Color_Total")
	private String colorTotal;
	
	@Column(name="Mono_Total")
	private String monoTotal;
	

	@ManyToOne
	@JoinColumn(name="Created_By")
	private Employee employee; 
	
	@ManyToOne
	@JoinColumn(name="Modify_Approver")
	private Employee modifyApprover;
	
	@ManyToOne
	@JoinColumn(name="Modified_By")
	private Employee modifiedBy;
	
	@ManyToOne
	@JoinColumn(name="Serial_Number")
	private Device serialNumber;
	
	@ManyToOne
	@JoinColumn(name="Customer_Name")
	private Customer customerName;
	
}
