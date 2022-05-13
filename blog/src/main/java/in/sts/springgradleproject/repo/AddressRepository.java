package in.sts.springgradleproject.repo;

import org.springframework.data.repository.CrudRepository;

import in.sts.springgradleproject.model.Address;

public interface AddressRepository extends CrudRepository<Address,Long>{

}
