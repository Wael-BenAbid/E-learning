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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",length = 10)
public class Seance extends AbstractEntity{
    private String titre;
    private String urlvideo;
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "seance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Support> supports = new HashSet<>();
}
