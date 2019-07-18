package com.cardina.schedular.event.repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cardinal.schedular.model.Event;
/**
 * @author Rakesh.Kumar
 *
 */
@Repository
@Transactional(readOnly = true)
public class EventRepositoryImpl implements EventRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Event> getEventNames(String firstName) {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM spring_data_jpa_example.employee as em " +
                "WHERE em.firstname LIKE ?", Event.class);
        query.setParameter(1, firstName + "%");
        return query.getResultList();
    }
}
