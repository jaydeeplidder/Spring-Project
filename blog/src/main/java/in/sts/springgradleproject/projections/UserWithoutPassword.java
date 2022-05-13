package in.sts.springgradleproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface UserWithoutPassword {

	@Value("#{target.id}")
	 Long getId();
	 
	@Value("#{target.name}")
	 String getName();
	 
	@Value("#{target.email}")
	 String getEmail();
	
	
	getUserAddress getAddress();
	
	interface getUserAddress {
		
		Long getId();
		String getCity();
		String getState();
		String getZipCode();
	}
}
