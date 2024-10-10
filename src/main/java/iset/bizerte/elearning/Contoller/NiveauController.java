package iset.bizerte.elearning.Contoller;

import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Service.NiveauService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/niveaux")
@RequiredArgsConstructor // injiction des dépendences
public class NiveauController {

    private final NiveauService niveauService;

    @GetMapping("/Liste_all")
    public ResponseEntity<List<NiveauDto>> getAllNiveaux() {
        List<NiveauDto> niveaux = niveauService.findAll();
        return ResponseEntity.ok(niveaux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NiveauDto> getNiveauById(@PathVariable Long id) {
        NiveauDto niveau = niveauService.findById(id);
        return ResponseEntity.ok(niveau);
    }
    @PostMapping("/Add_niveau")
    public ResponseEntity<NiveauDto> createNiveau(@RequestBody NiveauDto niveauDto) {
        NiveauDto newNiveau = niveauService.save(niveauDto);
        return ResponseEntity.ok(newNiveau);
    }
    @PutMapping("/Update{id}")
    public ResponseEntity<NiveauDto> updateNiveau(@PathVariable Long id, @RequestBody NiveauDto niveauDto) {
        niveauDto.setId(id);
        NiveauDto updatedNiveau = niveauService.save(niveauDto);
        return ResponseEntity.ok(updatedNiveau);
    }
    @DeleteMapping("/Delete{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        niveauService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<NiveauDto>> searchByOrientation(@RequestParam String key) {
        List<NiveauDto> niveaux = niveauService.findbyobjet(key);
        return ResponseEntity.ok(niveaux);
    }
    @GetMapping("/date")
    public ResponseEntity<List<NiveauDto>> getNiveauxByDate(@RequestParam Date start, @RequestParam Date end) {
        List<NiveauDto> niveaux = niveauService.findDate(start, end);
        return ResponseEntity.ok(niveaux);
    }
}
