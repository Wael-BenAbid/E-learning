package iset.bizerte.elearning.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cours extends AbstractEntity {
    private String title;
    private String description;
    private Boolean deleted;
    private String urlimage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cours", cascade = CascadeType.ALL)
    private Set<Tag_> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matere_id")
    private Matiere matieres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cours", cascade = CascadeType.ALL)
    private Set<Section> sections = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

}





