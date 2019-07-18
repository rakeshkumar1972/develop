package com.cardinal.schedular.emp.repository;

import com.cardinal.schedular.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**Repository is responsible to retive the employee record 
 * @author Rakesh.Kumar
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> , EmployeeRepositoryCustom {
}
