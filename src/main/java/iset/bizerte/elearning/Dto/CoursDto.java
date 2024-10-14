package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Tag_;
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
public class CoursDto {
    private String title;
    private String description;
    private Boolean deleted;
    private String urlimage;
    private Long matiereId;
    private Long enseignantId;
    private Set<Long> tagIds;
    private Set<Long> sectionIds;


    public static Cours toEntity(CoursDto request) {
        return Cours.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deleted(request.getDeleted())
                .urlimage(request.getUrlimage())
                .build();
    }
    public static CoursDto fromEntity(Cours request) {
        return CoursDto.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deleted(request.getDeleted())
                .urlimage(request.getUrlimage())
                .matiereId(request.getMatieres() != null ? request.getMatieres().getId() : null)
                .enseignantId(request.getEnseignant() != null ? request.getEnseignant().getId() : null)
                .tagIds(request.getTags() != null ?
                        request.getTags().stream().map(Tag_::getId).collect(Collectors.toSet()) :
                        new HashSet<>()) // Gestion du cas où tags est null
                .sectionIds(request.getSections() != null ?
                        request.getSections().stream().map(Section::getId).collect(Collectors.toSet()) :
                        new HashSet<>()) // Gestion du cas où sections est null
                .build();
    }


}
