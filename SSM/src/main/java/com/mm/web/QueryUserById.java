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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mm.entity.User;
import com.mm.service.UserService;
@Controller
public class QueryUserById {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/queryUserById",method=RequestMethod.POST)
	public void queryUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/x-json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String queryString = request.getParameter("message");
		User user = new User();
		user = userService.findUserById(queryString);
		String lis = JSON.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect);
		out.println(lis);
		out.flush();
		out.close();
		
	}

}
