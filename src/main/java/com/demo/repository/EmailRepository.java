package com.demo.repository;


import com.demo.entity.CorreoElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<CorreoElectronico, Long> {

}
