package ec.edu.insteclrg.service.crud;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.edu.insteclrg.domain.Usuario;
import ec.edu.insteclrg.domain.Product;
import ec.edu.insteclrg.dto.UsuarioDTO;
import ec.edu.insteclrg.persistence.UsuarioRepository;
import ec.edu.insteclrg.dto.ProductoDTO;
import ec.edu.insteclrg.service.GenericCrudServiceImpl;

@Service
public class UsuarioService extends GenericCrudServiceImpl<Usuario, UsuarioDTO> {

	@Autowired
	private UsuarioRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Usuario> find(UsuarioDTO dto) {
		return repository.findById(dto.getId()); 
	}

	@Override
	public UsuarioDTO mapToDto(Usuario domain) {
		return modelMapper.map(domain, UsuarioDTO.class);
	}

	@Override
	public Usuario mapToDomain(UsuarioDTO dto) {
		return modelMapper.map(dto, Usuario.class);
	}	
	
	public Optional<Usuario> login(UsuarioDTO dto) {
	  Optional<Usuario> optional=	this.repository.findByEmail(dto.getEmail());
	  if(!optional.isPresent()) {
		  throw new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "No se encuentra el usuario registrado"
				);
	  }
	  
	  if(dto.getPassword().equals(optional.get().getPassword())) {
		  return optional;
	  }else {
		  throw new ResponseStatusException(
				 HttpStatus.INTERNAL_SERVER_ERROR, "No se encuentra el usuario registrado"
				);
	  }

}
}
