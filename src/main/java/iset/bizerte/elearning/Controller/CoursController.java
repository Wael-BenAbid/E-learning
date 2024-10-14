package iset.bizerte.elearning.Controller;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Service.CourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cours")
@RequiredArgsConstructor
public class CoursController {

    private final CourService courService;

    @GetMapping("/List_All")
    public ResponseEntity<List<CoursDto>> getAllCours() {
        List<CoursDto> cours = courService.findAll();
        return ResponseEntity.ok(cours);
    }

    @GetMapping("/List/{id}")
    public ResponseEntity<CoursDto> getCoursById(@PathVariable Long id) {
        CoursDto cours = courService.findById(id);
        return ResponseEntity.ok(cours);
    }

    @PostMapping("Addcours")
    public ResponseEntity<CoursDto> createCours(@RequestBody CoursDto coursDto) {
        CoursDto createdCours = courService.save(coursDto);
        return ResponseEntity.ok(createdCours);
    }

    @PutMapping("/Modifier/{id}")
    public ResponseEntity<CoursDto> updateCours(@PathVariable Long id, @RequestBody CoursDto coursDto) {
        CoursDto existingCours = courService.findById(id); // On vérifie d'abord si le cours existe
        existingCours.setTitle(coursDto.getTitle());
        existingCours.setDescription(coursDto.getDescription());
        existingCours.setUrlimage(coursDto.getUrlimage());
        // Vous pouvez ajouter d'autres champs ici si nécessaire
        CoursDto updatedCours = courService.save(existingCours);
        return ResponseEntity.ok(updatedCours);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        courService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")    // Recherche par mot-clé dans l'orientation
    public ResponseEntity<List<CoursDto>> searchByObjet(@RequestParam String key) {
        List<CoursDto> result = courService.findbyobjet(key);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filterByDate")
    public ResponseEntity<List<CoursDto>> filterByDate(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<CoursDto> result = courService.findDate(startDate, endDate);
        return ResponseEntity.ok(result);
    }
}
