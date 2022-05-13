package in.sts.springgradleproject.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sts.springgradleproject.model.Address;

import in.sts.springgradleproject.repo.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public Iterable<Address> getAll(){
//		return this.users.values();
//		return userRepository.findAll();
		return addressRepository.findAll();
		
	}
}
