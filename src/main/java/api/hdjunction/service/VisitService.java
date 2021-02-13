package api.hdjunction.service;

import api.hdjunction.domain.Visit;

import java.util.List;

public interface VisitService {
    List<Visit> getVisitList();

    void insertVisit(Visit visit);

    Visit getVisit(Long id);

    void updateVisit(Visit visit);

    Visit deleteVisit(Long id);

}
