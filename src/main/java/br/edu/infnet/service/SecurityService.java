/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.service;

/**
 *
 * @author marcelo
 */
public interface SecurityService {

    boolean isAuthenticated();

    void autoLogin(String username, String password);

}
