package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Seance;
import iset.bizerte.elearning.Entity.Support;
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
public class SeanceDto {
    private String titre;
    private String urlvideo;
    private Boolean deleted;
    private Long sectionId;
    private Set<Long> supportIds;

    public static Seance toEntity(SeanceDto request) {
        return Seance.builder()
                .titre(request.getTitre())
                .urlvideo(request.getUrlvideo())
                .deleted(request.getDeleted())
                .build();
    }

    public static SeanceDto fromEntity(Seance request) {
        return SeanceDto.builder()
                .titre(request.getTitre())
                .urlvideo(request.getUrlvideo())
                .deleted(request.getDeleted())
                .sectionId(request.getSection() != null ? request.getSection().getId() : null)
                .supportIds(request.getSupports().stream().map(Support::getId).collect(Collectors.toSet()))
                .build();
    }

}
