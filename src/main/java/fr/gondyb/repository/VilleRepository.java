package fr.gondyb.repository;

import fr.gondyb.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String> {

}
