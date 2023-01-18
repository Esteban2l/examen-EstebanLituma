package ec.edu.insteclrg.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.insteclrg.domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>{
	
}
