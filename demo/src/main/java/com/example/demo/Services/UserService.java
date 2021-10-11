package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Bill;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private EmailService eServ;
	
	// Register and Hash Password
    public User registerUser(User user) {
        String hashedPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPw);
        return uRepo.save(user);
    }
    
    public User findByEmail(String email) {
        return uRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = uRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    public List<User> findAll(){
    	return this.findAll();
    }
    
 // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = uRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
        	// check if there is a match
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
	public void checkPayment() {
		
		List <User> allUser= (List<User>) uRepo.findAll();
		
		for (User oneUser :allUser){
			System.out.println(oneUser);
			for(Bill bill: oneUser.getBills()) {
				if(bill.isPayed()==false) {
					eServ.sendSimpleMail(oneUser.getEmail(), "Please Take care of this Bill "+bill.getName(), "Bill Reminder ");
				}
				eServ.sendSimpleMail(oneUser.getEmail(), "Thank you for paying this Bill "+bill.getName(), "Bill Reminder ");
			}
		}
	}
}
