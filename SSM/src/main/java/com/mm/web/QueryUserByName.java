package com.mm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mm.entity.User;
import com.mm.service.UserService;

@Controller
public class QueryUserByName  {
	@Autowired
	UserService userController;
	
	@RequestMapping(value="/queryUserByName",method=RequestMethod.POST)
	public void queryUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="message") String queryString) throws IOException {
//		String queryString = request.getParameter("message");
		List<User> list = new ArrayList<User>();
		if(null==queryString || "".equals(queryString)) {
			list = userController.findAllUsers();
		} else {
			list = userController.findUsersByName(queryString);
		}
		response.setContentType("application/x-json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String lis = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		out.println(lis);
		out.flush();
		out.close();
		
	}
}
