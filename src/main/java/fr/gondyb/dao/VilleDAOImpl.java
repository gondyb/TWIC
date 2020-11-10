package fr.gondyb.dao;

import java.util.ArrayList;

import fr.gondyb.dto.Ville;
import org.springframework.stereotype.Repository;

@Repository
public class VilleDAOImpl implements VilleDAO {

	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> listVille = new ArrayList<>();
		
		//TODO récupération et req sql
		
		return listVille;
	}
}
