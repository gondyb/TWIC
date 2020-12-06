package fr.gondyb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "ville_france")
@Data
public class Ville {

	@Id
	@Column(name = "Code_commune_INSEE")
	private String codeCommune;

	@Column(name = "Nom_commune")
	private String nomCommune;

	@Column(name = "Code_postal")
	private String codePostal;

	@Column(name = "Libelle_acheminement")
	private String libelleAcheminement;

	@Column(name = "Ligne_5")
	private String ligne;

	@Column(name = "Latitude")
	private Double latitude;

	@Column(name = "Longitude")
	private Double longitude;

	@Column(name = "Supprime")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Boolean deleted = false;

	public Ville() {
		
	}
}
