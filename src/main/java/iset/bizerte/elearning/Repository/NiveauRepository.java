package iset.bizerte.elearning.Repository;


import iset.bizerte.elearning.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface NiveauRepository extends JpaRepository<Niveau,Long> {
    List<Niveau> findByOriantationContainingIgnoreCase(String oriantation);
    List<Niveau> findByCreatedAtBetween(Date start, Date end);

}
