package br.com.eleflow.dto;

import java.util.List;

public class PlanetStarWarsDto {
	
	private List<StarWarsApiDto> results;
	
	public List<StarWarsApiDto> getResults() {
		return results;
	}

	public void setResults(List<StarWarsApiDto> results) {
		this.results = results;
	}
	

}
