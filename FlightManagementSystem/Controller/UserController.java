package FlightManagementSystem.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.FlightDetailsServiceImpl.UserImpl;
import FlightManagementSystem.Model.FlightBooking;
import FlightManagementSystem.Model.User;
import FlightManagementSystem.Repository.FlightBookingRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/FlightServ.com")
public class UserController {
	@Autowired
	private UserImpl userService;
	
	@Autowired
	private FlightBookingRepository bookingRepository;

	@GetMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		Optional<User> existingUser=this.userService.getUserByEmail(email);
		Map<String,String> response=new HashMap<String,String>();
		if(existingUser.isPresent())
		{
			if(existingUser.get().getUserPassword().equals(password))
			{
				response.put("status", "success");
				response.put("message", "User authenticated");
				response.put("userId",String.valueOf( existingUser.get().getUserId()) );
				response.put("userRole", existingUser.get().getRole());
				response.put("userName", existingUser.get().getUserName());
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
			}
			else
			{
				response.put("status", "Failed");
				response.put("message", "User password inncorrect");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}
		}		
		else
		{
			response.put("status", "Failed");
			response.put("message", "User email does not exist");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String,String>> signup(@RequestBody User user)
	{
		this.userService.addUser(user);
		Map<String,String> response=new HashMap<String,String>();
		response.put("status", "success");
		response.put("message", "User registered!!");
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.CREATED);
	}
	@DeleteMapping("/deleteUserById/{userId}")
	public ResponseEntity<User> removeUserById(@PathVariable("userId") int userId){
		return new ResponseEntity<User>(userService.deleteParticularUser(userId), HttpStatus.OK);
	}
	
	@GetMapping("/readUserById/{userId}")
	public ResponseEntity<Optional<User>> readUserById(@PathVariable("userId") int userId){
		return new ResponseEntity<Optional<User>>(userService.getUserById(userId), HttpStatus.OK);
	}
	@GetMapping("/readBookingDetails/{userId}")
	public ResponseEntity<List<FlightBooking>> readBookingsById(@PathVariable("userId") int userId){
		return new ResponseEntity<List<FlightBooking>>(bookingRepository.getBookingDetails(userId), HttpStatus.OK);
	}
	@GetMapping("/readAllUsers")
	public ResponseEntity<List<User>> readAllTheUsers(){
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}
}
