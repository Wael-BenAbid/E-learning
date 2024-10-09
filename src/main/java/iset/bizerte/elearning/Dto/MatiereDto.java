package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Matiere;
import iset.bizerte.elearning.Entity.Niveau;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatiereDto {
    private String libelle;
    private Boolean deleted;
    private Set<Long> coursIds;
    private Set<Long> niveauIds;

    public static Matiere toEntity(MatiereDto request) {
        return Matiere.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .build();
    }

    public static MatiereDto fromEntity(Matiere request) {
        return MatiereDto.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .niveauIds(request.getNiveaux().stream().map(Niveau::getId).collect(Collectors.toSet()))
                .coursIds(request.getCours().stream().map(cours -> cours.getId()).collect(Collectors.toSet()))
                .build();
    }

}
