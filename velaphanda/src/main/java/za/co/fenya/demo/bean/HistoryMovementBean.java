package za.co.fenya.demo.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class HistoryMovementBean {

	private Long historyMovementID;
	private String classification;	
	private String objectId;
	private String userEmail;
	private String userName;
	private String action;
	private String dateTimeMoved;
	private int quantityMoved;
	private String movedFrom;
	private String movedTo;
	private String description;
	
}
