package com.automacao.access_control.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.automacao.access_control.entities.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long>{

}