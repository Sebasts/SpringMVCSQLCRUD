package io.hellsing.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.hellsing.data.PersistenceDAO;
import io.hellsing.data.User;
import io.hellsing.data.ValidateDAO;
import io.hellsing.funtionality.Emailer;

@Controller
@SessionAttributes("user")
public class MemoController {
	
	
	@ModelAttribute
	public User initUser(){
		return new User();
	}
//	@ModelAttribute("")
	
	@Autowired
	ValidateDAO vdao;
	
	@Autowired
	PersistenceDAO pdao;
	
	@RequestMapping("start.do")
	public ModelAndView landingPage(@ModelAttribute("user") User user){
		ModelAndView mv = new ModelAndView();
		
		user = new User();
		mv.addObject("user", user);
		mv.setViewName("landing.jsp");
		return mv;
	}
	@RequestMapping("redirect.do")
	public ModelAndView redirectPostsHere(@ModelAttribute("user") User user){
		ModelAndView mv = new ModelAndView();
		if(user == null){
			mv.addObject("user", user);
			mv.setViewName("login.jsp");
		}
		mv.addObject("user", user);
		mv.addObject("memo", user.getMemos());
		mv.setViewName("accountMemos.jsp");
		return mv;
	}
	@RequestMapping("login.do")
	public ModelAndView loginPage(@ModelAttribute("user") User user){
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("login.jsp");
		Emailer em = new Emailer();
		em.test();
		return mv;
	}
	@RequestMapping("logout.do")
	public ModelAndView logout(@ModelAttribute("user") User user){
		ModelAndView mv = new ModelAndView();
		user = new User();
		mv.addObject("user", user);
		mv.setViewName("login.jsp");
		return mv;
	}
	@RequestMapping("updateMemo.do")
	public String updateMemo(String content, String names, Integer memId, @ModelAttribute("user") User user){
//		System.out.println(text);
		System.out.println(memId);
		System.out.println(content);
		System.out.println(names);
		System.out.println(user);
		pdao.updateMemo(memId, names, content, user);

		return "redirect:redirect.do";
	}
	
	@RequestMapping("newMemo.do")
	public String loginPage(@ModelAttribute("user") User user, String name, String content){
		System.out.println(user);
		pdao.writeMemoToDb(user, name, content);
		return "redirect:redirect.do";
	}
	
	@RequestMapping(path="deleteMemo.do")
	public String deleteMemo(@ModelAttribute("user") User user, Integer memId, Integer index){
		System.out.println(memId);
		
		user.deleteMemo(index);
		pdao.deleteMemo(memId, user);
		//pdao.writeMemoToDb(user, );
//		mv.addObject("user", user);
//		mv.addObject("memo", user.getMemos());
//		mv.setViewName("accountMemos.jsp");
		return "redirect:redirect.do";
	}
	
	@RequestMapping("register.do")
	public ModelAndView accountRegistration(@Valid @ModelAttribute("user") User user, Errors errors){
		ModelAndView mv = new ModelAndView();

		mv.addObject("memo", user.getMemos());
		mv.addObject("user", user);
		mv.setViewName("accountCreation.jsp");
		return mv;
	}
	
	
	@RequestMapping("validate.do")
	public ModelAndView newAccountPage(@Valid @ModelAttribute("user") User user, Errors errors, String firstName
										,String lastName, String email, String password){
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		if(vdao.accountExists(user)){
			System.out.println("It worked");
			System.out.println("This user already exists");
			mv.setViewName("accountCreation.jsp");
			return mv;
		}
		if(errors.getErrorCount() > 0){
			mv.addObject("user", user);
			mv.setViewName("accountCreation.jsp");
			return mv;
		}
		System.out.println("validate.do");
		pdao.writeNewUserToDb(user);
		mv.addObject("user", user);
		mv.setViewName("login.jsp");
		
		return mv;
	}
	@RequestMapping(path="loginAttempt.do", method=RequestMethod.POST)
	public String loginPage(@Valid @ModelAttribute("user") User user, Errors errors, String email, String password){
		ModelAndView mv = new ModelAndView();
		System.out.println(user + " " + email + " " + password);
		user.setEmail(email);
		user.setPassword(password);
		if(vdao.accountExists(user)){
			System.out.println("It worked 1");
			if(vdao.passwordMatches(user)){
				System.out.println("It worked 2");
				user.setMemos(pdao.loadFromDb(user.getEmail()));
//				mv.addObject("memo", user.getMemos());
//				mv.addObject("user", user);
//				System.out.println(user);
//				mv.setViewName("accountMemos.jsp");
				return "redirect:redirect.do";
			}
		}
		return "redirect:login.do";
	}
	
	
}
