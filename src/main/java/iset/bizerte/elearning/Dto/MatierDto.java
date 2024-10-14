package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Matier;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.CourRepository;
import iset.bizerte.elearning.Repository.NiveauRepository;
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
public class MatierDto {
    private String libelle;
    private Boolean deleted;
    private String oriantation;
    private Set<Long> coursIds;
    private Set<Long> niveauIds;

    public static Matier toEntity(MatierDto request, NiveauRepository niveauRepository) {
        Matier matier = Matier.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .oriantation(request.getOriantation())
                .niveaux(new HashSet<>())  // Initialiser à un ensemble vide
                .cours(new HashSet<>())  // Initialiser les cours à un ensemble vide également
                .build();

        if (request.getNiveauIds() != null) {
            request.getNiveauIds().forEach(niveauId -> {
                Niveau niveau = niveauRepository.findById(niveauId).orElse(null);
                if (niveau != null) {
                    matier.getNiveaux().add(niveau);  // Ajouter le niveau à la matière
                    niveau.getMatieres().add(matier);  // Ajouter la matière au niveau pour maintenir la relation bidirectionnelle
                }
            });
        }

        // Gestion des cours de manière similaire
        if (request.getCoursIds() != null) {
            request.getCoursIds().forEach(coursId -> {
                Cours cours = new Cours();
                cours.setId(coursId);  // Ajouter l'ID des cours
                matier.getCours().add(cours);
            });
        }

        return matier;
    }



    public static MatierDto fromEntity(Matier request) {
        return MatierDto.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .niveauIds(request.getNiveaux() != null ?
                        request.getNiveaux().stream().map(Niveau::getId).collect(Collectors.toSet()) :
                        new HashSet<>())  // Assurez-vous que la collection est non null
                .coursIds(request.getCours() != null ?
                        request.getCours().stream().map(cours -> cours.getId()).collect(Collectors.toSet()) :
                        new HashSet<>())  // Gestion de null pour `cours`
                .build();
    }


}
