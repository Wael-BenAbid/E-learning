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
public class Section extends AbstractEntity{

    private String titre;
    private Long ordre;
    private String discription;

    private Boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id")
    private Cours cours;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Seance> seances = new HashSet<>();
}

