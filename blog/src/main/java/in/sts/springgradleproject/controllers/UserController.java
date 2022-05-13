package in.sts.springgradleproject.controllers;



import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.sts.springgradleproject.helper.ResponseWrapper;
import in.sts.springgradleproject.model.Address;
import in.sts.springgradleproject.model.User;
import in.sts.springgradleproject.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<?> getAllUsers(){
		
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User retrieved Successfully");
		responseWrapper.setData(this.userService.getAll());
		return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User retrieved by idSuccessfully");
		responseWrapper.setData(this.userService.getUserId(id));
		return new ResponseEntity<>(responseWrapper,HttpStatus.OK );
	}
	
//	@PostMapping("")
//	public ResponseEntity<?> createUser(@RequestBody @Valid User user){
//		User createdUser=this.userService.create(user);
//		ResponseWrapper responseWrapper= new ResponseWrapper();
//		responseWrapper.setMessage("User Created Successfully");
//		responseWrapper.setData(createdUser);
//		return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);
//	}
	
	/*For Custom Error Handling we can use BindingResult*/
	
	@PostMapping("")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			List<String> errors=bindingResult.getFieldErrors().stream().
					map(error->{
						return error.getField()+" -> "+error.getDefaultMessage();
					}).collect(Collectors.toList());
			String errorMessage = String.join(" , ",errors);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage);
		}
		
		User createdUser=this.userService.create(user);
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User Created Successfully");
		responseWrapper.setData(createdUser);
		return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);
	}
	
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody @Valid User user){
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User Updated Successfully");
		responseWrapper.setData(userService.update(id, user));
		return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		this.userService.delete(id);
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User Updated Successfully");
		responseWrapper.setData(null);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PostMapping("/{id}/addresses")
	public ResponseEntity<?> addAddress(@PathVariable("id") Long userId,@RequestBody @Valid Address address){
		ResponseWrapper responseWrapper= new ResponseWrapper();
		responseWrapper.setMessage("User Address add Successfully");
		responseWrapper.setData(userService.addAddres(userId, address));
		return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
	}
	}
