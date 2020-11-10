package fr.gondyb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ville {
	
	private String codeCommune;

	private String nomCommune;

	private String codePostal;

	private String libelleAcheminement;

	private String ligne;
}
