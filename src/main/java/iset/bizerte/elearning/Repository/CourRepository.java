package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourRepository extends JpaRepository<Enseignant,Long> {
}
