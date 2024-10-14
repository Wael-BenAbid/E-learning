package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Matier;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.MatierRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NiveauDto {
    public Long id;
    private String niveaustudent;
    private Boolean deleted;
    private String oriantation;
    private Set<Long> matiereIds;

    public static Niveau toEntity(NiveauDto request, MatierRepository matierRepository) {
        Niveau niveau = Niveau.builder()
                .id(request.getId())
                .niveaustudent(request.getNiveaustudent())
                .deleted(request.getDeleted())
                .oriantation(request.getOriantation())
                .matieres(new HashSet<>())  // Initialiser à un ensemble vide
                .build();

        if (request.getMatiereIds() != null) {
            request.getMatiereIds().forEach(matiereId -> {
                Matier matier = matierRepository.findById(matiereId).orElse(null);
                if (matier != null) {
                    niveau.getMatieres().add(matier);  // Ajouter la matière au niveau
                    matier.getNiveaux().add(niveau);  // Ajouter le niveau à la matière pour maintenir la relation bidirectionnelle
                }
            });
        }

        return niveau;
    }

    public static NiveauDto fromEntity(Niveau request) {
        return NiveauDto.builder()
                .id(request.getId())
                .niveaustudent(request.getNiveaustudent())
                .deleted(request.getDeleted())
                .oriantation(request.getOriantation())
                .matiereIds(request.getMatieres().stream().map(Matier::getId).collect(Collectors.toSet()))
                .build();
    }
}
