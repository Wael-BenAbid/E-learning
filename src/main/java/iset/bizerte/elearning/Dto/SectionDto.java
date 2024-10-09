package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Seance;
import iset.bizerte.elearning.Entity.Section;
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
public class SectionDto {
    private String titre;
    private Long ordre;
    private String discription;
    private Boolean deleted;
    private Long coursId;
    private Set<Long> seanceIds;

    public static Section toEntity(SectionDto request) {
        return Section.builder()
                .titre(request.getTitre())
                .ordre(request.getOrdre())
                .discription(request.getDiscription())
                .deleted(request.getDeleted())
                .build();
    }

    public static SectionDto fromEntity(Section request) {
        return SectionDto.builder()
                .titre(request.getTitre())
                .ordre(request.getOrdre())
                .discription(request.getDiscription())
                .deleted(request.getDeleted())
                .coursId(request.getCours() != null ? request.getCours().getId() : null)
                .seanceIds(request.getSeances().stream().map(Seance::getId).collect(Collectors.toSet()))
                .build();
    }

}
