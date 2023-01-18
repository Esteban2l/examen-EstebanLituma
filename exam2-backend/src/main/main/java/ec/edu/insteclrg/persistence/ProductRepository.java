package ec.edu.insteclrg.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.insteclrg.domain.Product;
import ec.edu.insteclrg.dto.CategoriaDTO;
import ec.edu.insteclrg.dto.ProductoDTO;
import ec.edu.insteclrg.service.GenericCrudServiceImpl;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
