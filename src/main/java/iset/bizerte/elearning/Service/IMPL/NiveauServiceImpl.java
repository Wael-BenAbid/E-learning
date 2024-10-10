package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.NiveauRepository;
import iset.bizerte.elearning.Service.NiveauService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;
    @Override
    public List<NiveauDto> findAll() {
        return niveauRepository.findAll().stream()
                .map(NiveauDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public NiveauDto findById(Long id) {
        return niveauRepository.findById(id)
                .map(NiveauDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Niveau non trouver"));
    }
    @Override
    public NiveauDto save(NiveauDto request) {
        Niveau niveau = NiveauDto.toEntity(request);
        Niveau savedNiveau = niveauRepository.save(niveau);
        return NiveauDto.fromEntity(savedNiveau);
    }
    @Override
    public void deleteById(Long id) {
        Optional<Niveau> niveau = niveauRepository.findById(id);
        niveau.ifPresent(niv -> {
            niv.setDeleted(true);
            niveauRepository.save(niv);
        });
    }
    @Override
    public List<NiveauDto> findbyobjet(String key) { // recherche sellon les caractaire dans niveau
        return niveauRepository.findByOriantationContainingIgnoreCase(key)
                .stream()
                .map(NiveauDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public List<NiveauDto> findDate(Date start, Date end) {
        return niveauRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(NiveauDto::fromEntity)
                .collect(Collectors.toList());
    }
}
