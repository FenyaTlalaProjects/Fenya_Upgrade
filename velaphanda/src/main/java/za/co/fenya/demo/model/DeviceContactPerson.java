package za.co.fenya.demo.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="DeviceContactPerson")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceContactPerson {

	@Id
	@Column(name="Email")
	private String email;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Telephone")
	private String telephone;
	@Column(name="Cellphone")
	private String cellphone;
	
	@OneToMany(mappedBy ="contactPerson", cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Device> devices;
}
