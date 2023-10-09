package FlightManagementSystem.FlightDetailsServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightManagementSystem.Model.User;
import FlightManagementSystem.Repository.UserRepository;

@Service
public class UserImpl {
	
	@Autowired
	private UserRepository userRepo;
	
	public void addUser(User user) {
		this.userRepo.save(user);
	}

	public Optional<User> getUserByEmail(String email) {
		return this.userRepo.findByuserEmail(email);
	}
	
	public Optional<User> getUserById(Integer userId) {
		Optional<User> userObj = userRepo.findById(userId);
		if(userObj.isPresent())
			return userObj;
		else
			return null;
		
	}
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public User deleteParticularUser(int userId) {
		userRepo.deleteById(userId);
		return null;
	}
}
