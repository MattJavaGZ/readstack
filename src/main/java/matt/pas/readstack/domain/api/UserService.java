package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.user.User;
import matt.pas.readstack.domain.user.UserDao;
import org.apache.commons.codec.digest.DigestUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    UserDao userDao = new UserDao();
    UserMapper userMapper = new UserMapper();
    public void register (UserRegistration userRegistration) {
        User userToSave = userMapper.map(userRegistration);
        hashPasswordWithSha256(userToSave);
        userDao.save(userToSave);
    }

    private void hashPasswordWithSha256 (User user) {
        final String haszPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(haszPassword);
    }
    public List<UserToAdminList> allUsers() {
        return userDao.findAllUsers().stream()
                .map(userMapper::map)
                .filter(userToAdminList -> !userToAdminList.getRole().equals("ADMIN"))
                .collect(Collectors.toList());
    }
    public void blockUser(String userName) {
        userDao.blockUser(userName);
    }
    public void deleteUser(String userName, int userId) {
        userDao.deleteUser(userName, userId);
    }
    public void unlockUser(String userName) {
        userDao.unlockUser(userName);
    }

    public int userIdByUserName(String userName){
       return userDao.findByUsername(userName).get().getId();
    }
    private class UserMapper {
        UserDao userDao = new UserDao();
         User map(UserRegistration userRegistration) {
            return new User(userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    userRegistration.getPassword(),
                    LocalDateTime.now());
        }
         UserToAdminList map(User user) {
            return new UserToAdminList(
                    user.getId(),
                    user.getUsername(),
                    user.getRegistrationDate(),
                    userDao.roleByUserName(user.getUsername())
            );
        }
    }
}
