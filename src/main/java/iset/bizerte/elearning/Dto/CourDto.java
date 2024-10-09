package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Tag_;
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
public class CourDto {
    private String title;
    private String description;
    private Boolean deleted;
    private String urlimage;
    private Long matiereId;
    private Long enseignantId;
    private Set<Long> tagIds;
    private Set<Long> sectionIds;


    public static Cours toEntity(CourDto request) {
        return Cours.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deleted(request.getDeleted())
                .urlimage(request.getUrlimage())
                .build();
    }
    public static CourDto fromEntity(Cours request) {
        return CourDto.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deleted(request.getDeleted())
                .urlimage(request.getUrlimage())
                .matiereId(request.getMatieres() != null ? request.getMatieres().getId() : null)
                .enseignantId(request.getEnseignant() != null ? request.getEnseignant().getId() : null)
                .tagIds(request.getTags().stream().map(Tag_::getId).collect(Collectors.toSet()))
                .sectionIds(request.getSections().stream().map(Section::getId).collect(Collectors.toSet()))
                .build();
    }

}
