package source.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import source.dtos.response.ApiResponse;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@GetMapping("/health")
	public ResponseEntity<ApiResponse> health(){
		return ResponseEntity.ok().body(new ApiResponse("User health is fine", null, 200));
	}
}
