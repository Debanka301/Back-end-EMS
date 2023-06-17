package com.backend.LeavesService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.LeavesService.Entity.Leaves;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves, Integer>{
	
	@Query("select l From Leaves l WHERE l.id =:n")
	public List<Leaves> getLeavesByEmployeeId(@Param("n") Integer id);

}
