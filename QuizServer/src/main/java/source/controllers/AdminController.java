package source.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;
import source.dtos.response.ApiResponse;

@RestController
@RequestMapping("/api/admin")
@RolesAllowed({"ADMIN"})
public class AdminController {
	@GetMapping("/health")
	public ResponseEntity<ApiResponse> health(){
		return ResponseEntity.ok().body(new ApiResponse("Admin health is fine", null, 200));
	}
}
