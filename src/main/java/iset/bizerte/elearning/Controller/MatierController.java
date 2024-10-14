package iset.bizerte.elearning.Controller;

import iset.bizerte.elearning.Dto.MatierDto;
import iset.bizerte.elearning.Service.MatierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matier")
@RequiredArgsConstructor
public class MatierController {

    private final MatierService matierService;

    @GetMapping("/List_All")
    public ResponseEntity<List<MatierDto>> getAllMatier() {
        List<MatierDto> matieres = matierService.findAll();
        return ResponseEntity.ok(matieres);
    }
    @GetMapping("/List/{id}")
    public ResponseEntity<MatierDto> getMatierById(@PathVariable Long id) {
        MatierDto matiere = matierService.findById(id);
        return ResponseEntity.ok(matiere);
    }
    @PostMapping("/Add_matier")
    public ResponseEntity<MatierDto> createMatier(@RequestBody MatierDto matierDto) {
        MatierDto createdMatier = matierService.save(matierDto);
        return ResponseEntity.ok(createdMatier);
    }
    @PutMapping("/Modifier/{id}")
    public ResponseEntity<MatierDto> updateMatier(@PathVariable Long id, @RequestBody MatierDto matierDto) {
        MatierDto existingMatier = matierService.findById(id);
        existingMatier.setLibelle(matierDto.getLibelle());
        existingMatier.setDeleted(matierDto.getDeleted());
        existingMatier.setOriantation(matierDto.getOriantation());
        if (matierDto.getNiveauIds() != null) {
            existingMatier.setNiveauIds(matierDto.getNiveauIds());
        }

        MatierDto updatedMatier = matierService.save(existingMatier);
        return ResponseEntity.ok(updatedMatier);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> deleteMatier(@PathVariable Long id) {
        matierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<MatierDto>> searchByLibelle(@RequestParam String key) {
        List<MatierDto> result = matierService.findbyobjet(key);
        return ResponseEntity.ok(result);
    }
}
