package com.backend.TaxService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.TaxService.Entity.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{
	
	@Query("select t from Tax t WHERE t.empId =:n")
	public Tax getTaxByEmpId(@Param("n") Integer empId);

}
