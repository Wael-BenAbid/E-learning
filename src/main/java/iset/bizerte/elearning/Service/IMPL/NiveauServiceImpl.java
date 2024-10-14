package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Entity.Matier;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.MatierRepository;
import iset.bizerte.elearning.Repository.NiveauRepository;
import iset.bizerte.elearning.Service.NiveauService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;
    private final MatierRepository matierRepository;
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
        Niveau niveau = NiveauDto.toEntity(request,matierRepository);
        Set<Matier> matiers_To_add;

        if (request.getMatiereIds().isEmpty()) {
            throw new IllegalArgumentException("Vous devez ajouter des matières.");
        } else {
            matiers_To_add = new HashSet<>();
            for (Long IDmatiers : request.getMatiereIds()) {
                Optional<Matier> matierExisit = matierRepository.findById(IDmatiers);
                matierExisit.ifPresent(matiers_To_add::add);
            }
            niveau.setMatieres(matiers_To_add);
            Niveau savedNiveau = niveauRepository.save(niveau);// Associe les matières au niveau
            return NiveauDto.fromEntity(savedNiveau);
        }
    }
    @Override
    public NiveauDto update(Long id, NiveauDto request) {

        Optional<Niveau> niveau = niveauRepository.findById(id);

        if (niveau.isPresent()) {
            Niveau niveauConverted = NiveauDto.toEntity(request, matierRepository);
            Niveau existingNiveau = niveau.get();
            existingNiveau.setNiveaustudent(niveauConverted.getNiveaustudent());
            existingNiveau.setDeleted(niveauConverted.getDeleted());
            existingNiveau.setOriantation(niveauConverted.getOriantation());

            Set<Matier> matieresToAdd = new HashSet<>();
            if (request.getMatiereIds() == null || request.getMatiereIds().isEmpty()) {
                throw new IllegalArgumentException("Vous devez ajouter des matières.");
            } else {
                for (Long idMatieres : request.getMatiereIds()) {
                    Optional<Matier> matiereFound = matierRepository.findById(idMatieres);
                    matiereFound.ifPresent(matieresToAdd::add);
                }
            }
            existingNiveau.setMatieres(matieresToAdd);
            Niveau updatedNiveau = niveauRepository.save(existingNiveau);
            return NiveauDto.fromEntity(updatedNiveau);
        } else {
            throw new RuntimeException("Niveau non trouvé avec l'ID: " + id);
        }
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
    public List<NiveauDto> findbyobjet(String key) {
        return niveauRepository.searchByObjetStartsWith(key)
                .stream()
                .map(NiveauDto::fromEntity)
                .collect(Collectors.toList());
    }
}
