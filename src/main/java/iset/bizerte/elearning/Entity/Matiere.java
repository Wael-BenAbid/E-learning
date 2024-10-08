package iset.bizerte.elearning.Entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Matiere extends AbstractEntity{
    private String libelle;
    private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matieres", cascade = CascadeType.ALL)
    private Set<Cours> cours = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Set<Niveau> niveaux = new HashSet<>();
}



