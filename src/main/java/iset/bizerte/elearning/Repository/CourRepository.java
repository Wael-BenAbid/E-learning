package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Cours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CourRepository extends JpaRepository<Cours, Long> {

    @Query("SELECT m FROM Cours m WHERE m.title LIKE %?1%")
    List<Cours> searchByObjetStartsWith(String key);
    List<Cours> findByCreatedAtBetween(Date start, Date end);
}
