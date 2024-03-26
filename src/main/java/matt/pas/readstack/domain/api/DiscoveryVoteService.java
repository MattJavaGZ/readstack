package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.user.User;
import matt.pas.readstack.domain.user.UserDao;
import matt.pas.readstack.domain.vote.Vote;
import matt.pas.readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class DiscoveryVoteService {

    VoteDao voteDao = new VoteDao();
    DiscoveryVoteMapper discoveryVoteMapper = new DiscoveryVoteMapper();

    public void addVote(DiscoveryVote discoveryVote) {
        final Vote vote = discoveryVoteMapper.map(discoveryVote);
        voteDao.save(vote);
    }

    private class DiscoveryVoteMapper{
        UserDao userDao = new UserDao();
        Vote map(DiscoveryVote discoveryVote) {
            final Optional<User> user = userDao.findByUsername(discoveryVote.getUserName());
            return new Vote(
                    user.orElseThrow().getId(),
                    discoveryVote.getDiscoveryId(),
                    Vote.Type.valueOf(discoveryVote.getType()),
                    LocalDateTime.now()
            );
        }
    }
}
