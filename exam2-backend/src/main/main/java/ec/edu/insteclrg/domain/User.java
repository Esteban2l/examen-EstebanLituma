package ec.edu.insteclrg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	@Column
	private String name;
	@Column
	private String lastname;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String password;

}
