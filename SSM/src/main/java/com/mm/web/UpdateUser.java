package com.mm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mm.entity.User;
import com.mm.service.UserService;

@Controller
public class UpdateUser {
	@Autowired
	UserService userController;
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String state = "";
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setAddress(request.getParameter("address"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		user.setGender(request.getParameter("gender"));
		user.setId(request.getParameter("id"));
		boolean b = userController.updateUser(user);
		if(b) {
			state =  "OK";
		}
		out.println(state);
		out.flush();
		out.close();
	}
}
