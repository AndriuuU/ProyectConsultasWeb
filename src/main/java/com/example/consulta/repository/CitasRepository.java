package com.example.consulta.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consulta.entity.Citas;

@Repository("citasRepository")
public interface CitasRepository extends JpaRepository<Citas,Serializable> {
	
	public abstract Citas findByIdCitas(long id);
	
}