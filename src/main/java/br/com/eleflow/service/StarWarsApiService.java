package br.com.eleflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.eleflow.dto.PlanetStarWarsDto;
import br.com.eleflow.dto.StarWarsApiDto;

@Service
public class StarWarsApiService {

	private String baseUrl = "https://swapi.dev/api/";

	private RestTemplate restTemplate;

	public StarWarsApiService() {
		restTemplate = new RestTemplate();
	}

	public List<StarWarsApiDto> getPlanets() throws Exception {

		var list = new ArrayList<StarWarsApiDto>();

			ResponseEntity<PlanetStarWarsDto> response = 
					restTemplate.exchange(baseUrl + "planets/?page=" + 1, HttpMethod.GET, null, PlanetStarWarsDto.class);
			
			list.addAll(response.getBody().getResults());

		return list;

	}
}
