package by.htp.jd2.maksimkosmachev.testapp.entity;

import by.htp.jd2.maksimkosmachev.testapp.entity.enumpackage.Role;

import java.io.Serializable;
import java.util.Objects;

public class User extends Entity implements Serializable {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Role role;
    private int userDetailsId;

    public User() {
    }


    public User(String login, String password, String name, String surname, String email, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(int userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return userDetailsId == user.userDetailsId &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, email, name, surname, role, userDetailsId);
    }

    @Override
    public String toString() {
        return super.toString()+"User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", userDetailsId=" + userDetailsId +
                '}';
    }
}
