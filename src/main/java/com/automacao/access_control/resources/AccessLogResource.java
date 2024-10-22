package com.automacao.access_control.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.automacao.access_control.dto.RfidDTO;
import com.automacao.access_control.entities.AccessLog;
import com.automacao.access_control.entities.User;
import com.automacao.access_control.repositories.AccessLogRepository;
import com.automacao.access_control.repositories.UserRepository;

@Controller
@RequestMapping(value = "/access")
public class AccessLogResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccessLogRepository accessLogRepository;
	
	@PostMapping("/")
	public ResponseEntity<Void> saveAccessLog(@RequestBody RfidDTO rfidDTO) {
		String rfid = rfidDTO.rfid();
	    User user = userRepository.findByRfid(rfid);
	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    AccessLog obj = new AccessLog(user);
	    accessLogRepository.save(obj);
	    return ResponseEntity.ok().build();
	}
}
