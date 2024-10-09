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
public class NiveauDto {
    private String niveaustudent;
    private Boolean deleted;
    private String oriantation;
    private Set<Long> matiereIds;

    public static Niveau toEntity(NiveauDto request) {
        return Niveau.builder()
                .niveaustudent(request.getNiveaustudent())
                .deleted(request.getDeleted())
                .oriantation(request.getOriantation())
                .build();
    }

    public static NiveauDto fromEntity(Niveau request) {
        return NiveauDto.builder()
                .niveaustudent(request.getNiveaustudent())
                .deleted(request.getDeleted())
                .oriantation(request.getOriantation())
                .matiereIds(request.getMatieres().stream().map(Matiere::getId).collect(Collectors.toSet()))
                .build();
    }

}
