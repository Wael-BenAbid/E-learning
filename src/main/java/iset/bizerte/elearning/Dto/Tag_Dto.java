package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Tag_;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag_Dto {
    private String libelle;
    private Boolean deleted;

    public static Tag_ toEntity(Tag_Dto request) {
        return Tag_.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .build();
    }

    public static Tag_Dto fromEntity(Tag_ request) {
        return Tag_Dto.builder()
                .libelle(request.getLibelle())
                .deleted(request.getDeleted())
                .build();
    }
}
