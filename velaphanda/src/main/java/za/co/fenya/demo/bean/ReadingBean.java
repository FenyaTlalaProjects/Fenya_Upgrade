package za.co.fenya.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReadingBean {
	
	private String monoReading;
	private String colorReading ;
	private String previousColorReading;
	private String previousMonoReading;
	private String readingMonth;
	private String readingYear;
	private String insertDate;
	private String modifiedDate;
	private String employee;
	private String modifyApprover;
	private String modifiedBy;
	private String serialNumber;
	private String customerName;
	private String readingPeriod;
	private String readingStatus;
	private Long recordID;
	

	
}
