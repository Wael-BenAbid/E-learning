package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.MatierDto;
import iset.bizerte.elearning.Dto.NiveauDto;

import java.util.Date;
import java.util.List;
public interface MatierService {

    public List<MatierDto> findAll();
    public MatierDto findById(Long id);
    MatierDto save(MatierDto request);
    public void deleteById(Long id);
    public List<MatierDto> findbyobjet(String key);
}

