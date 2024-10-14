package iset.bizerte.elearning.Repository;


import iset.bizerte.elearning.Entity.Matier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MatierRepository extends JpaRepository<Matier, Long> {

    @Query("SELECT m FROM Matier m WHERE m.libelle LIKE %?1%")
    List<Matier> searchByObjetStartsWith(String key);
}
