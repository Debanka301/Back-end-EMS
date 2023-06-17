package com.backend.EmployeeService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.EmployeeService.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query("Select e from Employee e Where e.name =:n")
	public Employee getEmployeeByName(@Param("n") String name);

}
