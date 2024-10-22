package com.automacao.access_control.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automacao.access_control.entities.AccessLog;
import com.automacao.access_control.repositories.AccessLogRepository;

@Service
public class AccessLogService {

	@Autowired
	private AccessLogRepository repository;
	
	public AccessLog findById(Long id) {
		Optional<AccessLog> obj = repository.findById(id);
		return obj.get();
	}
}
