/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.binomeproject.presentation.controller;

import com.mycompany.binomeproject.business.entity.User;
import com.mycompany.binomeproject.business.service.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Amira BOULFOUL
 */
@Named
@SessionScoped
public class AuthController implements Serializable {

    private String username;
    private String password;
    private User loggedInUser;

    @Inject
    private UserService userService;

    public String login() {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return "home.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credentials"));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    // Getters et Setters
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
}
