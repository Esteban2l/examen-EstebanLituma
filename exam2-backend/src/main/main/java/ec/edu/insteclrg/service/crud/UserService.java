package ec.edu.insteclrg.service.crud;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.edu.insteclrg.domain.User;
import ec.edu.insteclrg.domain.Product;
import ec.edu.insteclrg.dto.UserDTO;
import ec.edu.insteclrg.dto.ProductoDTO;
import ec.edu.insteclrg.persistence.UserRepository;
import ec.edu.insteclrg.service.GenericCrudServiceImpl;

@Service
public class UserService extends GenericCrudServiceImpl<User, UserDTO> {

	@Autowired
	private UserRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<User> find(UserDTO dto) {
		return repository.findById(dto.getId()); 
	}

	@Override
	public UserDTO mapToDto(User domain) {
		return modelMapper.map(domain, UserDTO.class);
	}

	@Override
	public User mapToDomain(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}	
	
	public Optional<User> login(UserDTO dto) {
	  Optional<User> optional=	this.repository.findByEmail(dto.getEmail());
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
