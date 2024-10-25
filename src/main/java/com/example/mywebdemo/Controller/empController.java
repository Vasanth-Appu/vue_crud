package com.example.mywebdemo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mywebdemo.Service.UserService;
import com.example.mywebdemo.entity.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller
public class empController {
	@Autowired
	UserService us;
	
	//public String msg = addEmployee(null);

	@GetMapping("/getUser")
	@ResponseBody
	public List<User> getUser(Model model) {

		return us.getuser();
	}

	// @GetMapping("/addUser")
	// public String addUser( Model model) {
	// 	User  user  = new User();
	// 	model.addAttribute("addEmp",user);
	// 	return "adduser";
	// }
	
	
	// @PostMapping("/save")
	// public String save(@ModelAttribute ("addEmp")User user ,Integer id){
		
	// 	us.saveUser( user);
	// 	return "redirect:/getUser";
		
	// }

	@PostMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody User user) {
		try {
			us.saveUser(user);
	
			String msg = "Name: " + user.getName() + ", Address: " + user.getAddress() + ", Email: " + user.getEmail();
			String num = "+91" + user.getContact().trim();
	System.out.println("Num"+num);
	System.out.println("msg"+msg);
			if (numcheck(num)) {
				message(num,msg);
				return ResponseEntity.ok("Saved successfully and SMS sent!");
			} else {
				return ResponseEntity.badRequest().body("Invalid phone number");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee");
		}
	}
	
private boolean numcheck(String phoneNumber){
	return phoneNumber.matches("\\+91[6-9][0-9]{9}");
}
public String message( String to,String messagecontent) {
	try {
		Twilio.init(ssId, authToken);  // Corrected order

		Message msg = Message.creator(
				new PhoneNumber(to),  // To
				new PhoneNumber("+17815705508"),  // From
				messagecontent).create();
				

		return "Message Sent!";
	} catch (Exception e) {
		e.printStackTrace();
		return "Error: " + e.getMessage();
	}
}

	public empController(UserService us) {
		// TODO Auto-generated constructor stub
		super();
		this.us = us;
	}

	@GetMapping("/getId/{id}")
	@ResponseBody
	public Optional<User> getUserId(@PathVariable(value = "id") Integer ide) {
		return us.getrecord(ide);
	}

	// @RequestMapping("/updateId/{id}")
	// public String updateId(@PathVariable(value = "id") Integer ide, User user, Model model)// ,@RequestBody User
	// 																						// contact,@RequestBody User
	// 																						// email)
	// {
	// 	model.addAttribute("emp", us.updateId(ide, user));
	// 	return "update";
	// }


	 @PutMapping("/editEmployee/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ("id")Integer id, @RequestBody User user) {
        try {
            User updatedUser = us.updateId(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	// @GetMapping("/deleteid/{id}")
	// public String deleteId(@PathVariable(value = "id") Integer ide, Model model) {
	// 	us.delete(ide);
	// 	return "redirect:/getUser";
	// }

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable ("id") Integer id){
		try {
			us.delete(id);
			return ResponseEntity.ok("Deleted successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("deleting Employee Error");
		}
	}

	public static String authToken="f2305f7fa203bc1090ab1e8bb4590ea0";
	public static String ssId="AC2d87140879c03bf028fd212c2d2817ed";
   

    

}
