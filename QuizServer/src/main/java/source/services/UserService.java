package source.services;

import source.dtos.request.CreateUser;
import source.models.User;

public interface UserService {
	User getUser(String email);
	User getUserById(String id);
	User createUser(CreateUser body);
}
