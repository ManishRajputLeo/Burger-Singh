package com.manish.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manish.entity.MenuItem;
import com.manish.entity.UserEntity;
import com.manish.service.OrderService;
import com.manish.service.UserService;



@Controller
public class BkController {

	@Autowired
	private OrderService oService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/*Displaying Home Page*/
	
	@GetMapping("/")
	public String homePage(Model model) {
		return "home";
	}
	
	/*Place Your Order Page*/
	
	@GetMapping("/place-order")
	public String orderPlace(Model model){
		model.addAttribute("orders", new MenuItem());
		return "placeOrder";
	}
	
	/* Get The Order List */
	
//	@GetMapping("/order-list")
//	public String orderListing(){
//		return "OrderLists";
//	}
	
	/* Get the Data From Form */
	
	@PostMapping("/save")
	public String saveOrder(@ModelAttribute MenuItem mItem) {
		oService.placeOrder(mItem);
		return "redirect:/order-list";
	}
	
	
	/* Print All Orders */
	
	@GetMapping("/order-list")
	public String getAllOrders(Model model) {
		List<MenuItem> orders = oService.getAllOrder();
		model.addAttribute("orders", orders);
		return "OrderLists";
	}
	
		@GetMapping("/update/{id}")
		public String updateById(@PathVariable ("id")int id, Model model) {
			model.addAttribute("order", oService.getById(id));		
			return"update_employee";
		}
	
		@PostMapping("/updateById/{id}")
		public String updateOrderById(@PathVariable ("id") int id, @ModelAttribute ("order") MenuItem menuItem, Model model) {
			MenuItem menu =	oService.getById(id);
			menu.setId(id);
			menu.setName(menuItem.getName());
			menu.setQuantity(menuItem.getQuantity());
			menu.setPrice(menuItem.getPrice());
			oService.updateById(menu);
			return "redirect:/order-list";
		}
	
	@GetMapping("/deleteById/{id}")
	public String deleteEmployeeById(@PathVariable ("id") int id, Model model) {
		oService.deleteById(id);
		return "redirect:/order-list";
	}
	
	
	@GetMapping("/about")
		public String aboutPage() {
			return"about_us";
		}
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	
	@PostMapping("/register")
	public String registerNewUser(@RequestParam String userName,
			@RequestParam String email, 
			@RequestParam String password,
			Model model ) {
		
		Optional<UserEntity> user = userService.findByEmail(email);
		if (user.isPresent()) {
			 String errorMessage = "This User is already Registered";
			model.addAttribute("error", errorMessage);
			System.out.println(errorMessage);
			return "register";
			
		}
		UserEntity newUser = new UserEntity();	
		newUser.setUserName(userName);
		newUser.setEmail(email);
		newUser.setRole("ROLE_USER");
		newUser.setPassword(encoder.encode(password));
		
		userService.saveUser(newUser);
		return "redirect:/";
	}

	
}
