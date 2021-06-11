/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.service;

import br.edu.infnet.model.Role;
import java.util.List;

/**
 *
 * @author Marcelo Vergara <http://marcelo-vergara.codes/>
 */
public interface RoleService {

    List<Role> findAll();

    Role findRoleById(Long id);

}
