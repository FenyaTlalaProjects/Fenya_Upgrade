package za.co.fenya.demo.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Entity  
@DiscriminatorValue("User") 
@Getter
@Setter
public class User extends Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
