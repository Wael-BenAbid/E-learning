package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.NiveauDto;

import java.util.Date;
import java.util.List;

public interface NiveauService {

    public List<NiveauDto> findAll();
    public NiveauDto findById(Long id);
    NiveauDto save(NiveauDto  request);
    NiveauDto update(Long id, NiveauDto request);
    public void deleteById(Long id);
    public List<NiveauDto> findbyobjet(String kye);
}
