package matt.pas.readstack.domain.api;

import java.time.LocalDateTime;

public class UserToAdminList {

    private Integer id;
    private String username;
    private LocalDateTime registrationDate;
    private String role;


    public UserToAdminList(Integer id, String username, LocalDateTime registrationDate, String role) {
        this.id = id;
        this.username = username;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getRole() {
        return role;
    }
}
