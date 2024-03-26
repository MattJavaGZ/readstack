package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.visit.Visit;
import matt.pas.readstack.domain.visit.VisitDao;

public class VisitService {
    VisitDao visitDao = new VisitDao();

    public void saveVisit(int discoveryId) {
        Visit visit = new Visit(discoveryId);
        visitDao.saveVisit(visit);
    }
}
