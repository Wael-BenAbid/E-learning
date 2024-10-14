package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Repository.CourRepository;
import iset.bizerte.elearning.Service.CourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourServiceImpl implements CourService {

    private final CourRepository courRepository;

    @Override
    public List<CoursDto> findAll() {
        return courRepository.findAll().stream()
                .map(CoursDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CoursDto findById(Long id) {
        return courRepository.findById(id)
                .map(CoursDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'ID: " + id));
    }

    @Override
    public CoursDto save(CoursDto request) {
        Cours cours = CoursDto.toEntity(request);
        Cours savedCours = courRepository.save(cours);
        return CoursDto.fromEntity(savedCours);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Cours> cours = courRepository.findById(id);
        if (cours.isPresent()) {
            Cours foundCours = cours.get();
            foundCours.setDeleted(true);
            courRepository.save(foundCours);
        } else {
            throw new RuntimeException("Cours non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public List<CoursDto> findbyobjet(String key) {  // recherche sellon les caractaire dans niveau
        return courRepository.searchByObjetStartsWith(key)
                .stream()
                .map(CoursDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursDto> findDate(Date start, Date end) {
        return courRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(CoursDto::fromEntity)
                .collect(Collectors.toList());
    }
}
