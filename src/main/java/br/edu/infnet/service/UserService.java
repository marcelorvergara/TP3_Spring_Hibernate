/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.service;

import br.edu.infnet.model.User;

/**
 *
 * @author marcelo
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
