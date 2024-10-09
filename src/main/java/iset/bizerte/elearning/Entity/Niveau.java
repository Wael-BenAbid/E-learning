package iset.bizerte.elearning.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Niveau extends AbstractEntity {
    private String niveaustudent;
    private Boolean deleted;
    private String oriantation;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Set<Matiere> matieres = new HashSet<>();
}




