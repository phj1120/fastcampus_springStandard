package com.fastcampus.ch2;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
	
	@RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		int number1 = (int)(Math.random()*6) +1;
		int number2 = (int)(Math.random()*6) +1;
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src = 'resources/img/dice"+number1+".jpg'>");
		out.println("<img src = 'resources/img/dice"+number2+".jpg'>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
