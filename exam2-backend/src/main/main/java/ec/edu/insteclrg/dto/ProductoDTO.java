package ec.edu.insteclrg.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private CategoriaDTO category;
}
