package iset.bizerte.elearning.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Support extends AbstractEntity{
    private Boolean deleted;
    private Long ordre;
    private String name;
    private String paragraphe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private Seance seance;
}
