package br.com.eleflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eleflow.entity.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long>{
	
	Planet findByNome(String nome);

}
