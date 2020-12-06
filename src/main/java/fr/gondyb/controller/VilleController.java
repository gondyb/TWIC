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
		return villeRepository.findAllByDeletedFalseOrderByNomCommuneAsc();
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

		ville.setLatitude(nouvelleVille.getLatitude());
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

		double lat1 = v1.getLatitude();
		double lat2 = v2.getLatitude();
		double lon1 = v1.getLongitude();
		double lon2 = v2.getLongitude();

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = 0;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
