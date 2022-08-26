/**
 * 
 */
package com.niral.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niral.flightreservation.entities.Role;

/**
 * @author Niral Maruda
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
