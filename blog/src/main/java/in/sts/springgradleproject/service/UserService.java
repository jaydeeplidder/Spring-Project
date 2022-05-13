package in.sts.springgradleproject.service;




import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import in.sts.springgradleproject.model.Address;
import in.sts.springgradleproject.model.User;
import in.sts.springgradleproject.projections.UserWithoutPassword;
import in.sts.springgradleproject.repo.AddressRepository;
import in.sts.springgradleproject.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
//	private static Map<Long, User> users= new HashMap<>();
	private static AtomicLong atomic = new AtomicLong();
	
//	@SuppressWarnings("static-access")
	public List<UserWithoutPassword> getAll(){
//		return this.users.values();
//		return userRepository.findAll();
		return userRepository.findAllProjectedBy();
		
	}
	
//	@SuppressWarnings("static-access")
	public UserWithoutPassword getUserId(Long id) {
		
//		return this.users.get(id);
		return this.userRepository.findAllProjectedById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		});
	}
	
	public User create(User user) {
		user.setId(atomic.incrementAndGet());
//		users.put(user.getId(),user);
//		return user;
		return this.userRepository.save(user);
	}
	
	public User update(Long id,User user) {
		user.setId(id);
		this.userRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		});
//		return users.put(id,user);
		return this.userRepository.save(user);
	}
	
	public User getUserWithPasswordById(Long id) {
		return this.userRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		});
	}
	
	public Address addAddres(Long userId,Address address) {
		User foundUser=this.getUserWithPasswordById(userId);
		Address savedAddress= this.addressRepository.save(address); 
		foundUser.setAddress(savedAddress);
		
	
//		return users.put(id,user);
		this.userRepository.save(foundUser);
		return savedAddress;
		
	}
	
	public void delete(Long id) {
//		users.remove(id);
		this.userRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		});
		this.userRepository.deleteById(id);
	}
}
