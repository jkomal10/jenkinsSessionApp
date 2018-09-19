package com.session.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
@SessionAttributes("sessionusername")
@Controller
public class LoginController {
	
	String sessionusername;
	 @RequestMapping(value = "/index")
	  public void showLogin(@RequestParam("username")String name, @RequestParam("password")String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		 model.addAttribute("username",name);
		 model.addAttribute("password",password);
		 
		 if(name.equals("user")&&password.equals("pass"))
		 {
			 HttpSession session=request.getSession();
			 session.setAttribute("sessionusername", "sessionuser");
			 System.out.println(session.getAttribute("sessionusername"));
			 session.setMaxInactiveInterval(30*60);
			 response.sendRedirect("success.jsp");
		 }
		 else
		 {
			 System.out.println("Wrong password");
			 response.sendRedirect("index.jsp");
		 }
	    }
	 
	 @RequestMapping(value = "/login")
	  public void login(@RequestParam("username")String name, @RequestParam("password")String password,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		 String user = request.getParameter("username");
			String pwd = request.getParameter("password");
			
			if("user".equals(user) && "pass".equals(pwd)){
				HttpSession session = request.getSession();
				session.setAttribute("user", "user");
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user", "cookieuser");
				System.out.println(userName+"user");
				System.out.println(session.getAttribute("user"));
				response.addCookie(userName);
				//Get the encoded URL string
				String encodedURL = response.encodeRedirectURL("LoginSuccess.jsp");
				response.sendRedirect(encodedURL);
			}
			else
			{
				String encodedURL = response.encodeRedirectURL("login.jsp");
				response.sendRedirect(encodedURL);
			}
		}
	 
	 @RequestMapping(value = "/logout")
	  public String showLogout(HttpServletRequest request,HttpServletResponse response) {
		 	HttpSession session = request.getSession(false);
	    	System.out.println("User="+session.getAttribute("sessionusername"));
	    	if(session != null){
	    		session.invalidate();
	    	}
	    return "index";
	    }
}
