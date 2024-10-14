package iset.bizerte.elearning.Service.IMPL;


import iset.bizerte.elearning.Dto.MatierDto;
import iset.bizerte.elearning.Entity.Matier;


import iset.bizerte.elearning.Repository.MatierRepository;
import iset.bizerte.elearning.Repository.NiveauRepository;
import iset.bizerte.elearning.Service.MatierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class MatierServiceImpl implements MatierService {

    private final MatierRepository matierRepository;
    private final NiveauRepository niveauRepository;

    @Override
    public List<MatierDto> findAll() {
        return matierRepository.findAll().stream()
                .map(MatierDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MatierDto findById(Long id) {
        return matierRepository.findById(id)
                .map(MatierDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Matier non trouvé avec l'ID: " + id));
    }

    @Override
    public MatierDto save(MatierDto request) {
        Matier matier = MatierDto.toEntity(request, niveauRepository);
        Matier savedMatier = matierRepository.save(matier);
        return MatierDto.fromEntity(savedMatier);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Matier> matier = matierRepository.findById(id);
        if (matier.isPresent()) {
            Matier foundMatier = matier.get();
            foundMatier.setDeleted(true);
            matierRepository.save(foundMatier);
        } else {
            throw new RuntimeException("Matier non trouvé avec l'ID: " + id);
        }
    }
    @Override
    public List<MatierDto> findbyobjet(String key) {
        return matierRepository.searchByObjetStartsWith(key)
                .stream()
                .map(MatierDto::fromEntity)
                .collect(Collectors.toList());
    }

}
