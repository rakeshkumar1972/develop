package com.cardinal.schedular.emp.repository;

import java.util.List;
import com.cardinal.schedular.model.Employee;

/** /**Repository is responsible to retrieve the employee record which look like first name of the employee record.
 * @author Rakesh.Kumar
 *
 */
public interface EmployeeRepositoryCustom {

	List<Employee> getFirstNamesLike(String firstName);
}
