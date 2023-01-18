package ec.edu.insteclrg.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String lastname;
	private String email;
	private String password;
}
