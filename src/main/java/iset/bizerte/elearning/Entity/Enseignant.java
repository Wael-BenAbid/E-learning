package iset.bizerte.elearning.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("Enseignant")

public class Enseignant extends User{

    private String diplome;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Administrateur administrateur  ;

}
