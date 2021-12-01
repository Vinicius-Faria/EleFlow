package br.com.eleflow.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eleflow.dto.StarWarsApiDto;
import br.com.eleflow.service.StarWarsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dev/api")
@Api(tags = "StarWars", value = "starWars", description = " ")
public class StarWarsApiController {

	private static final Logger logger = LoggerFactory.getLogger(StarWarsApiController.class);

	@Autowired
	private StarWarsApiService starWarsApiService;

	@ApiOperation(value = "List all planet's of API StarWars.")
	@GetMapping
	public List<StarWarsApiDto> planets() throws Exception {

		logger.info("All()");

		return starWarsApiService.getPlanets();
	}

}
