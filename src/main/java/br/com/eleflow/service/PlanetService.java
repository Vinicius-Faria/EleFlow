package br.com.eleflow.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleflow.dto.StarWarsApiDto;
import br.com.eleflow.entity.Planet;
import br.com.eleflow.repository.PlanetRepository;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetaRepository;

	@Autowired
	private StarWarsApiService starWarsApiService;

	public Planet save(Planet e) {

		return planetaRepository.save(e);
	}

	public Planet update(Long id, Planet e) {

		Planet planeta = findById(id);
		BeanUtils.copyProperties(e, planeta);
		return save(planeta);

	}

	public void delete(Long id) {
		planetaRepository.delete(findById(id));
	}

	@Transactional(readOnly = true)
	public Planet findById(Long id) {
		return planetaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Planeta não encontrado " + id, 1));
	}

	@Transactional(readOnly = true)
	public List<Planet> findAll() {

		return planetaRepository.findAll();

	}

	public Planet findByNome(String nome) {

		return planetaRepository.findByNome(nome);
	}

	public Integer TellsMovieAppearances(String name) throws Exception {

		var planet = starWarsApiService.getPlanets();

		for (StarWarsApiDto planetDto : planet) {
			if (planetDto.getName().equalsIgnoreCase(name)) {
				return planetDto.getFilms().size();
			}
		}

		return 0;
	}
}
