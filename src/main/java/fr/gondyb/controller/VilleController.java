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

	@GetMapping("/distanceBetween/{id1}/{id2}")
	public Double distanceBetween(@PathVariable String id1, @PathVariable String id2) {
		Ville v1 = villeRepository.findOne(id1);
		Ville v2 = villeRepository.findOne(id2);

		double lat1 = v1.getAttitude();
		double lat2 = v2.getAttitude();
		double lon1 = v1.getLongitude();
		double lon2 = v2.getLongitude();

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515; // Miles
		dist = dist * 1.609344; // Km

		return dist;
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
