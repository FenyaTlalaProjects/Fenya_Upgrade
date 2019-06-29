package za.co.fenya.demo.bean;


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
	private String colorTotal;
	private String monoTotal;
	//private String readingNumber;
	
	

	
}
