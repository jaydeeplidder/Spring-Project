package in.sts.springgradleproject.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	private Long id;
	
	@Size(min = 6 , max = 20 , message = "Name should be minimunm of 6 character and maximum of 20 character" )
	@Column
	private String name;
	
	@NotNull(message = "password should not be null")
	@Size(min = 6 , max = 20 , message = "password should be minimunm of 6 character and maximum of 20 character" )
	@Pattern(regexp = "[a-zA-Z0-9@#$]+", message = "Only alpha numeric character are allowed with @,#,$ special characters only ")
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Email(message = "please enter a proper email")
	@Column(nullable = false,unique = true)
	private String email;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	
}
