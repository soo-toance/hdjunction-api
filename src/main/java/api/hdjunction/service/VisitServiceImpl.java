package api.hdjunction.service;

import api.hdjunction.domain.Visit;
import api.hdjunction.persistence.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepo;

    public List<Visit> getVisitList() {
        return (List<Visit>) visitRepo.findAll();
    }

    public void insertVisit(Visit visit) {
        visitRepo.save(visit);
    }

    public Visit getVisit(Long id) {
        return visitRepo.findById(id).orElse(null);
    }

    public void updateVisit(Visit visit) {
        if (getVisit(visit.getId()) == null) {
            return;
        }

        visitRepo.save(visit);
    }

    public Visit deleteVisit(Long id) {
        Visit exist = getVisit(id);
        if (exist == null) {
            return null;
        }

        visitRepo.deleteById(id);
        return exist;
    }
}
