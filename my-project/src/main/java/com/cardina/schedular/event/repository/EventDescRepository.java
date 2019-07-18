package com.cardina.schedular.event.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cardinal.schedular.model.EventDesc;

/**This repository is responsible for handling the data for the template description and the 
 * one description will be retrived. one at a time.so there are alot of template but only one will be active.
 *  only the basic function will be available.
 * @author Rakesh.Kumar
 * 
 */
@Repository
public interface EventDescRepository extends JpaRepository<EventDesc, Integer> {
	

}
