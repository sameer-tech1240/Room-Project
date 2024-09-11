package com.room.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.room.api.model.Employee;

@Repository
public interface IEmpRepository extends JpaRepository<Employee, Integer> {

}
