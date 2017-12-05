package com.mm.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mm.service.UserService;

@Controller
public class DeleteUser {
	@Autowired
	UserService userController;
	@RequestMapping(value="deleteUser",method=RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String info = "";
		String id = request.getParameter("message");
		Boolean b = userController.deleteUser(id);
		response.setContentType("application/x-json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(b) {
			info = "删除成功！";
		} else {
			info = "删除失败！";
		}
		String lis = JSON.toJSONString(info, SerializerFeature.DisableCircularReferenceDetect);
		out.println(lis);
		out.flush();
		out.close();
	}

}
