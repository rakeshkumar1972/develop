package com.cardina.schedular.event.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cardinal.schedular.model.EventDesc;

/**
 * @author Rakesh.Kumar
 *
 */
@Repository
@Transactional(readOnly = true)
public class EventDescRepositoryImpl implements EventDescRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<EventDesc> getEventDesc() {
		Query query = entityManager.createNativeQuery(
				"SELECT em.* FROM event_desc as em ",
				EventDesc.class);
		//query.setParameter(1, firstName + "%");
		return query.getResultList();
	}
}

