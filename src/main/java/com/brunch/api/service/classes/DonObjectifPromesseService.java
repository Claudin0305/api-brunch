package com.brunch.api.service.classes;

import com.brunch.api.entity.DonObjectifPromesse;
import com.brunch.api.repository.DonObjectifPromesseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DonObjectifPromesseService {
    @Autowired
    private DonObjectifPromesseRepository donObjectifPromesseRepository;
    public List<DonObjectifPromesse> getAll() {
        Sort sort = Sort.by("createdAt").descending();
        return donObjectifPromesseRepository.findAll(sort);
    }

    public DonObjectifPromesse create(DonObjectifPromesse donObjectifPromesse) {
        return donObjectifPromesseRepository.save(donObjectifPromesse);
    }

    public DonObjectifPromesse findOne(Long id) {
        return donObjectifPromesseRepository.findById(id).orElse(null);
    }

    public DonObjectifPromesse update(Long id, DonObjectifPromesse donObjectifPromesse) {
        DonObjectifPromesse donObjectifPromesse1 = findOne(id);
        if(donObjectifPromesse1 == null) {
            return null;
        }
        donObjectifPromesse1.setObjectif(donObjectifPromesse.getObjectif());
        donObjectifPromesse1.setPromesse(donObjectifPromesse.getPromesse());
        donObjectifPromesse1.setDon(donObjectifPromesse.getDon());
        donObjectifPromesse1.setUpdatedAt(new Date());
        return donObjectifPromesseRepository.save(donObjectifPromesse);
    }

    public void delete(Long id) {
        donObjectifPromesseRepository.deleteById(id);
    }
}
