package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.CoursDto;

import java.util.Date;
import java.util.List;

public interface CourService {
    List<CoursDto> findAll();
    CoursDto findById(Long id);
    CoursDto save(CoursDto request);
    void deleteById(Long id);
    List<CoursDto> findbyobjet(String key);
    List<CoursDto> findDate(Date start, Date end);
}