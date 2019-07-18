package com.cardinal.schedular.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cardinal.schedular.model.Employee;

/**
 * @author Rakesh.Kumar
 *
 */
public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployee_id(rs.getInt("employee_id"));
		employee.setName(rs.getString("name"));
		employee.setEmail(rs.getString("email"));
		employee.setBirth_date(rs.getDate("birth_date"));
		employee.setTemplate(rs.getString("template"));
		employee.setSubject(rs.getString("subject"));
		employee.setDescription(rs.getString("description"));
		return employee;
	}

}
