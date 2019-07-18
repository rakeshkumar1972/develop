package com.cardina.schedular.event.repository;

import java.util.List;
import com.cardinal.schedular.model.Event;

/**
 * @author Rakesh.Kumar
 *
 ***This repository is responsible for handling the data for the template description and the 
 * one description will be retrived. one at a time.so there are a lot of template but only one will be active.
 *  only the basic function will be available.
 *   
 * 
 */
 

public interface EventRepositoryCustom {

	List<Event> getEventNames(String firstName);
}
