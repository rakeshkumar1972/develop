package com.cardinal.schedular.emp.repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cardinal.schedular.model.Employee;

/**
 * @author Rakesh.Kumar
 *
 */
@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Employee> getFirstNamesLike(String firstName) {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM event_descption as em " +
                "WHERE em.description LIKE= ?");
        query.setParameter(1, firstName + "%");
        return query.getResultList();
    }
}
