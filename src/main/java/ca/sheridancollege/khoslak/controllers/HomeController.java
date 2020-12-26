package ca.sheridancollege.khoslak.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.khoslak.beans.Comment;
import ca.sheridancollege.khoslak.beans.Threadiscussion;
import ca.sheridancollege.khoslak.beans.User;
import ca.sheridancollege.khoslak.repositories.CommentRepository;
import ca.sheridancollege.khoslak.repositories.RoleRepository;
import ca.sheridancollege.khoslak.repositories.ThreadRepository;
import ca.sheridancollege.khoslak.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ThreadRepository threadRepository;
	private CommentRepository commentRepository;
	
	private String encodePassword(String password) 
	{ 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		return encoder.encode(password); 
	} 
	@GetMapping("/")
	public String index(Model model)
	{
		return "redirect:/securedDocs";
	}

	
	@GetMapping("/securedDocs")
	public String securedIndex(Model model,Authentication authentication)
	{
		String username = authentication.getName();
		
		User user=userRepository.findByUsername(username);
		List<Threadiscussion> thd=threadRepository.findAll();
		
		
		
		model.addAttribute("threadlist",thd);
		
	
		
	
		model.addAttribute("firstname", user.getFirstname());
		model.addAttribute("empId",user.getEmpId());
		return "securedDocs/index";
	}
	
	@PostMapping("/createThread")
	public String createThread(Model model,@RequestParam String subject,@RequestParam String content,Authentication authentication)
	{
		LocalDate dt=LocalDate.now();
		
		Threadiscussion thd=new Threadiscussion();
		thd.setSubject(subject);
		thd.setContent(content);
		thd.setCreateDate(dt);
	
		User user=userRepository.findByUsername(authentication.getName());
		thd.setUser(user);
		user.getThreads().add(thd);
	
		userRepository.save(user);
          List<Threadiscussion> threadlist=threadRepository.findAll();
		
		
		
		model.addAttribute("threadlist",threadlist);
		model.addAttribute("firstname", user.getFirstname());
		model.addAttribute("empId",user.getEmpId());
	
		
		return "securedDocs/index";
	}
	
	@GetMapping("/login")
	public String login(Model model)
	{
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model)
	{
		return "error/access-denied";
	}
	@GetMapping("/register")
	public String goRegistration (Model model) 
	{
		model.addAttribute("paramUerror",null);
		model.addAttribute("user",new User());
		return "register"; 
	} 
	
	
	@PostMapping("/register")
	public String doRegistration(Model model,@RequestParam String firstname,@RequestParam String lastname,@RequestParam Integer empId,@RequestParam String username, @RequestParam String password,@ModelAttribute User myUser) 
	{
		
		User u=userRepository.findByUsername(username);
		if(u==null)
		{
			User user = new User();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmpId(empId);
			
			user.setUsername(username);
			user.setEncryptedPassword(encodePassword(password));
			user.setEnabled(Byte.valueOf("1"));
			user.getRoles().add(roleRepository.findByRolename("ROLE_USER"));
			userRepository.save(user);
			
			return "redirect:/securedDocs";
		}
		else
		{
			
			model.addAttribute("user",myUser);
	
			model.addAttribute("paramUerror","Sorry Username Already exists !");
			return "register";
		}
		
		
	}
	
	@GetMapping("/viewComments/{id}")
	public String viewComments(Model model,@PathVariable Long id)
	{
		Threadiscussion thd=threadRepository.findById(id).get();
		model.addAttribute("threadId",thd.getId());
		model.addAttribute("commentList",thd.getComments());
		return "securedDocs/viewComment";
	}
	
	@PostMapping("/addComments/{id}")
	public String addComments(Model model,@RequestParam String replyContent,@PathVariable Long id,Authentication authentication)
	{
		Threadiscussion thd=threadRepository.findById(id).get();
		LocalDate dt=LocalDate.now();
		
		Comment comment=new Comment();
	     comment.setPostComment(replyContent);
		comment.setCreateDate(dt);
		comment.setUsername(authentication.getName());
		
		thd.getComments().add(comment);
		threadRepository.save(thd);
		Threadiscussion thrd=threadRepository.findById(id).get();
		model.addAttribute("threadId",thrd.getId());
		model.addAttribute("commentList",thrd.getComments());
		
		
		
		
		return "securedDocs/viewComment";
	}
	
	@GetMapping("/latDiscussion")
	public String latDiscussion(Authentication authentication,Model model)
	{
          String username = authentication.getName();
		
		User user=userRepository.findByUsername(username);
		List<Threadiscussion> thd=threadRepository.findAllByOrderByCreateDateDesc();
		
		
		
		model.addAttribute("threadlist",thd);
		
	
		
	
		model.addAttribute("firstname", user.getFirstname());
		model.addAttribute("empId",user.getEmpId());
		return "securedDocs/index";
	}
	
	@GetMapping("/prevDiscussion")
	public String prevDiscussion(Authentication authentication,Model model)
	{
		   String username = authentication.getName();
			
			User user=userRepository.findByUsername(username);
			List<Threadiscussion> thd=threadRepository.findAllByOrderByCreateDate();
			
			
			
			model.addAttribute("threadlist",thd);
			
		
			
		
			model.addAttribute("firstname", user.getFirstname());
			model.addAttribute("empId",user.getEmpId());
		
		return "securedDocs/index";
	}
}
