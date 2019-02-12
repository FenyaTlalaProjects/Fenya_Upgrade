package za.co.fenya.demo.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;


@Entity
@Table(name="CustomerDeviceHistory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CustomerDeviceHistory implements  Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="History_ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long historyID;
	@Column(name="Classification")
	private String classification;	
	@Column(name="ObjectId")
	private String objectId;
	@Column(name="UserEmail")
	private String userEmail;
	@Column(name="UserName")
	private String userName;
	@Column(name="Action")
	private String action;
	@Column(name="DateTime")
	private String dateTime;
	@Column(name="Description")
	private String description;		
}

