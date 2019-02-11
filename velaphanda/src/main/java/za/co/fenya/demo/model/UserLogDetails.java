package za.co.fenya.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="UserLogDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLogDetails {

	@Id
	@Column(name="LogID")
	private String sessionId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDateTime;
	@ManyToOne
	@JoinColumn(name="User")
	private Employee employee;
}
