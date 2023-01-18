package ec.edu.insteclrg.controller.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.insteclrg.domain.Usuario;
import ec.edu.insteclrg.dto.ApiResponseDTO;
import ec.edu.insteclrg.dto.UsuarioDTO;
import ec.edu.insteclrg.service.crud.UsuarioService;

@RestController
@RequestMapping("/api/v1.0/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@PostMapping("/registrar")
	public ResponseEntity<Object> registrar(@RequestBody UsuarioDTO dto) {
		service.save(dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, null), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UsuarioDTO dto) {
		Optional<Usuario> domain = service.login(dto);
		dto = service.mapToDto(domain.get());
		return new ResponseEntity<>(new ApiResponseDTO<>(true, dto), HttpStatus.OK);
	}

	@PutMapping(value = "/Usuario")
	public ResponseEntity<Object> actualizar(@RequestBody UsuarioDTO dto) {
		service.update(dto);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, null), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<UsuarioDTO> list = service.findAll(new UsuarioDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<UsuarioDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(id);
		Optional<Usuario> domain = service.find(dto);
		if (!domain.isEmpty()) {
			dto = service.mapToDto(domain.get());
			return new ResponseEntity<>(new ApiResponseDTO<>(true, dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/{id}/eliminar")
	public ResponseEntity<Object> eliminar(@PathVariable Long id) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(id);
		Optional<Usuario> domain = service.find(dto);
		if (!domain.isEmpty()) {
			service.delete(dto);
			return new ResponseEntity<>(new ApiResponseDTO<>(true, dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

}
