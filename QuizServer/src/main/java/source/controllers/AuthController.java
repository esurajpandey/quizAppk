package source.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import source.dtos.request.CreateUser;
import source.dtos.request.LoginRequest;
import source.dtos.response.ApiResponse;
import source.models.User;
import source.services.UserService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/health")
	public ResponseEntity<ApiResponse> health(){
		return ResponseEntity.ok().body(new ApiResponse("Auth health is fine", null, 200));
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody User body){
		System.out.println(body.getEmail());
//		User createdUser = userService.createUser(body);
		return new ResponseEntity<>(new ApiResponse("User has been created",null, 201),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest body){
//		User createdUser = userService.createUser(body);
		return new ResponseEntity<>(new ApiResponse("User has been created",null, 201),HttpStatus.CREATED);
	}
	
	
}
