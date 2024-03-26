package matt.pas.readstack.domain.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import matt.pas.readstack.client.favorite.FavoriteAddController;
import matt.pas.readstack.domain.comment.CommentDao;
import matt.pas.readstack.domain.discovery.Discovery;
import matt.pas.readstack.domain.discovery.DiscoveryDao;
import matt.pas.readstack.domain.user.UserDao;
import matt.pas.readstack.domain.visit.VisitDao;
import matt.pas.readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscoveryService {

    DiscoveryDao discoveryDao = new DiscoveryDao();
    VoteDao voteDao = new VoteDao();
    CommentDao commentDao = new CommentDao();
    VisitDao visitDao = new VisitDao();
    DiscoveryMapper discoveryMapper = new DiscoveryMapper();

    public void add(DiscoverySaveRequest discoverySaveRequest) {
        final Discovery discoveryToSave = discoveryMapper.map(discoverySaveRequest);
        discoveryDao.save(discoveryToSave);
    }

    public List<DiscoveryBasicInfo> findAll() {
        final List<Discovery> all = discoveryDao.findAll();
        Collections.reverse(all);
        return all.stream()
                .map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        final List<Discovery> byCategory = discoveryDao.findByCategory(categoryId);
        Collections.reverse(byCategory);
        return byCategory
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<DiscoveryBasicInfo> findById(int id) {
        return discoveryDao.findById(id)
                .map(discoveryMapper::map);
    }

    public List<DiscoveryBasicInfo> discoveriesByUserVote(int userId) {
        final List<Integer> idList = voteDao.discoveriesIdByUserVote(userId);
        Collections.reverse(idList);
        return idList
                .stream()
                .map(discoveryDao::findById)
                .map(Optional::orElseThrow)
                .map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> discoveriesByUserComment(int userId) {
        final List<Integer> idList = commentDao.discoveriesIdByUserComment(userId);
        Collections.reverse(idList);
        return idList
                .stream()
                .map(discoveryDao::findById)
                .map(Optional::orElseThrow)
                .map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public void favoriteCheck(HttpServletRequest request, List<DiscoveryBasicInfo> discoveryToPrint) {
        final HttpSession session = request.getSession();
        final List<DiscoveryBasicInfo> favoriteSessionDiscovery = (List<DiscoveryBasicInfo>) session.getAttribute("discoveries");
        if (favoriteSessionDiscovery != null) {
            for (DiscoveryBasicInfo discovery : discoveryToPrint) {
                for (DiscoveryBasicInfo discoverySession : favoriteSessionDiscovery) {
                    if (discovery.getId() == discoverySession.getId()) {
                        discovery.setIsFavorite(2);
                    }
                }
            }
        }
    }

    public int visitsForDiscoveryId(int discoveryId) {
        return visitDao.visitCounterByDiscoveryId(discoveryId).orElse(0);
    }

    private class DiscoveryMapper {

        private final UserDao userDao = new UserDao();
        VoteDao voteDao = new VoteDao();

        DiscoveryBasicInfo map(Discovery discovery) {
            return new DiscoveryBasicInfo
                    (
                            discovery.getId(),
                            discovery.getTitle(),
                            discovery.getUrl(),
                            discovery.getDescription(),
                            discovery.getDateAdded(),
                            voteDao.countByDiscoveryId(discovery.getId()),
                            userDao.findById(discovery.getUserId()).orElseThrow().getUsername(),
                            visitsForDiscoveryId(discovery.getId())
                    );
        }

        Discovery map(DiscoverySaveRequest discoverySaveRequest) {
            return new Discovery(
                    discoverySaveRequest.getTitle(),
                    discoverySaveRequest.getUrl(),
                    discoverySaveRequest.getDescription(),
                    LocalDateTime.now(),
                    discoverySaveRequest.getCategoryId(),
                    userDao.findByUsername(discoverySaveRequest.getAuthor())
                            .orElseThrow().getId()

            );
        }
    }
}
