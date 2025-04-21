package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserManagement {
    private String id;
    private String login;
    private String email;
    private boolean activated;

    public UserManagement(String id,String login,String email ,boolean activated) {
        this.login = login;
        this.activated = activated;
        this.email = email;
        this.id=id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
