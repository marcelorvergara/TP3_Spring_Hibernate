/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.repository;

import br.edu.infnet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marcelo
 */
@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
