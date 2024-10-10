package iset.bizerte.elearning.Entity;

import java.security.KeyStore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Niveau extends AbstractEntity {
    private Long id;
    private String niveaustudent;
    private Boolean deleted;
    private String oriantation;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Set<Matiere> matieres = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}




