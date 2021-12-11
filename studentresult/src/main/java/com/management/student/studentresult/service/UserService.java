package com.management.student.studentresult.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.utils.UserDetailsSecurity;
import com.management.student.studentresult.utils.ValidatorUtils;
import com.management.student.studentresult.utils.ValidatorUtils.ValidationFields;
import com.management.student.studentresult.validator.Validator;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;

@Service
@Transactional
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ValidatorUtils validatorUtils;

    public User saveUser(User user){
        return repository.save(user);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByExtId(String extId){
        return repository.findByExtId(extId);
    }

    public UserDetails getUserDetailsByExtId(String extId) throws ParseException {

        User user = getUserByExtId(extId);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(user.getName());
        userDetails.setExtId(user.getExtId());
        userDetails.setAddress(user.getAddress());
        userDetails.setContactno(user.getPhone());
        userDetails.setEmail(user.getAuth().getEmail());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        userDetails.setDob(format.format(user.getDob()));
        return userDetails;
    }
    
    public ResponseEntity<ResponseMessage> registrationService(UserDetails userDetails){
    	
    	try {
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		System.out.println(userDetails.getDob()); 
    		ValidatorUtils.ValidationFields vf= new ValidatorUtils.ValidationFields(userDetails.getExtId(), userDetails.getEmail(), userDetails.getContactno(), format.parse(userDetails.getDob()));
			Validator validator= validatorUtils.validateChain("REGISTRATION_VALIDATIONS",vf);
			validator.validate();
			System.out.println("UserDetails validation successful!");
			Auth auth = new Auth(userDetails.getEmail(), userDetails.getPassword());
			auth=authService.saveAuth(auth);
			Role role = roleService.getRoleByName(userDetails.getRole());			
			User user = new User(auth, role, userDetails.getExtId(), userDetails.getName(), userDetails.getAddress(), userDetails.getContactno(), format.parse(userDetails.getDob()));
			saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
		}
    	String message="Registration Successful!";
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    }

	@Override
	public UserDetailsSecurity loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			User user=repository.findByExtId(username);
			if(user.equals(null))
				throw new UsernameNotFoundException("The username"+username+"does not exist!");
			return new UserDetailsSecurity(user);
	}

}
