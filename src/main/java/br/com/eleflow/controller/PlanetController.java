package br.com.eleflow.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.eleflow.entity.Planet;
import br.com.eleflow.service.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/planetas")
@Api(tags = "Planeta", value = "planeta", description = " ")
public class PlanetController {

	private static final Logger logger = LoggerFactory.getLogger(PlanetController.class);

	@Autowired
	private PlanetService planetaService;

	@ApiOperation(value = "Save a new planet.")
	@PostMapping
	public ResponseEntity<Planet> save(@RequestBody(required = true) Planet planeta) throws Exception {

		logger.info("save()");

		planeta.setId(null);
		planeta.setAparicoes(planetaService.TellsMovieAppearances(planeta.getNome()));
		planeta = planetaService.save(planeta);

		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).body(planeta);
	}

	@ApiOperation("Search by Id.")
	@GetMapping("/{id}")
	public ResponseEntity<Planet> one(@PathVariable(name = "id", required = true) Long id) {

		logger.info("one()");

		Planet planeta = planetaService.findById(id);
		return ResponseEntity.ok(planeta);
	}

	@ApiOperation("List planet.")
	@GetMapping
	public ResponseEntity<List<Planet>> all() {
		logger.info("All()");

		return ResponseEntity.status(HttpStatus.OK).body(planetaService.findAll());
	}

	@ApiOperation("Delete planet.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("Delete()");

		planetaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Update planet.")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Planet planeta, @PathVariable Long id) throws Exception {
		logger.info("Update()");
		
		planeta.setId(id);
		planetaService.update(id, planeta);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Search by Nome.")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Planet> searchByName(@PathVariable(name = "nome", required = true) String nome) {

		logger.info("nome()");

		Planet planeta = planetaService.findByNome(nome);
		return ResponseEntity.ok(planeta);
	}

}
