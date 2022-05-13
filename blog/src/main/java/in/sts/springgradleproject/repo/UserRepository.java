package in.sts.springgradleproject.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.sts.springgradleproject.model.User;
import in.sts.springgradleproject.projections.UserWithoutPassword;

@Repository
public  interface UserRepository extends CrudRepository<User, Long>{

	List<UserWithoutPassword> findAllProjectedBy();
	
	Optional<UserWithoutPassword> findAllProjectedById(Long id);
}
