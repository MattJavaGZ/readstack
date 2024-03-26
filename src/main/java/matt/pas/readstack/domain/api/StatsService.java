package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.stats.StatsDao;

public class StatsService {

    StatsDao statsDao = new StatsDao();

    public StatsFullInfo statsToPrint(){
        return new StatsFullInfo(statsDao.userCount(), statsDao.discoveryCount(), statsDao.commentCount(), statsDao.voteCount());
    }
}
