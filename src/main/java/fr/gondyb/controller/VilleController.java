package fr.gondyb.controller;

import fr.gondyb.model.Ville;
import fr.gondyb.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VilleController {

	private final VilleRepository villeRepository;

	public VilleController(VilleRepository villeRepository) {
		this.villeRepository = villeRepository;
	}

	@GetMapping("/villes/{id}")
	public Ville getVille(@PathVariable String id) {
		return villeRepository.findOne(id);
	}

	@GetMapping("/villes")
	public List<Ville> appelGet() {
		return villeRepository.findAllByDeletedFalse();
	}

	@PostMapping("/villes")
	public Ville appelPost(@RequestBody Ville nouvelleVille) {
		return villeRepository.save(nouvelleVille);
	}

	@PutMapping("/villes/{id}")
	public Ville appelPut(@RequestBody Ville nouvelleVille, @PathVariable String id) {
		Ville ville = villeRepository.findOne(id);

		if (ville == null) {
			return villeRepository.save(nouvelleVille);
		}

		ville.setAttitude(nouvelleVille.getAttitude());
		ville.setCodePostal(nouvelleVille.getCodePostal());
		ville.setLibelleAcheminement(nouvelleVille.getLibelleAcheminement());
		ville.setLigne(nouvelleVille.getLigne());
		ville.setLongitude(nouvelleVille.getLongitude());
		ville.setNomCommune(nouvelleVille.getNomCommune());
		return villeRepository.save(ville);
	}

	@DeleteMapping("/villes/{id}")
	public void deleteVille(@PathVariable String id) {
		Ville ville = villeRepository.findOne(id);

		ville.setDeleted(true);

		villeRepository.save(ville);
	}
}
