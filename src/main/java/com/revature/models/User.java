package com.revature.models;

import java.util.Objects;

public class User {

    private enum UserRole {
        Employee, Manager
    }
    // enum.values() is expensive in performance, so we cache the values once across instances
    private static final UserRole UserRoleValues[] = UserRole.values();

    private int Id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private int userRoleId;

    public User() {}

    public User(int id, String username, String password, String firstName, String lastName, String email, int userRoleId) {
        Id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = UserRoleValues[userRoleId];
        this.userRoleId = userRoleId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
        this.userRoleId = userRole.ordinal();
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
        this.userRole = UserRoleValues[userRoleId];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id == user.Id && userRoleId == user.userRoleId && username.equals(user.username) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && email.equals(user.email) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, username, password, firstName, lastName, email, userRole, userRoleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
