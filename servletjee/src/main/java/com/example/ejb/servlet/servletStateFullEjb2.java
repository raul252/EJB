package com.example.ejb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.ejb.business.CounterClick;
import com.example.ejb.business.Greeting;

/**
 * Servlet implementation class servletStateLessEjb
 */
@WebServlet("/servletStateFullEjb2")
public class servletStateFullEjb2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATEFUL_CLICK_BEAN_KEY = "click_bean"; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletStateFullEjb2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String message = greeting.getGreeting();
		 // Garantiza la exclusividad de mi bean @Stateful
		  CounterClick clicks = getStatefulCounterClicks(request); 
		  clicks.doClick();
		  int counter = clicks.getClicks(); 
		  
		  
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();  

		 out.println("<html>"); 
		 out.println("<head>");
		 out.println("<title> Ejemplo EJB @Stateful</title>");
		 out.println("</head>");

		 out.println("<body>");
		     out.println("<h1> Ejemplo EJB @Statefuls </h1>");
		 
		     out.println("<h3> ha realizado "+ counter +" clicks </h3>");
		 	
		     out.println("<form action='/servletjee/servlet/click'>");
		     out.println("<input type='submit' value='Haga un click'>");
			 out.println("</form>");
		 	
		 out.println("</body>");

		 out.println("</html>"); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private CounterClick getStatefulCounterClicks(HttpServletRequest request) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		CounterClick statefulTestBean = 
				(CounterClick) httpSession.getAttribute(STATEFUL_CLICK_BEAN_KEY);


		if (statefulTestBean == null) {
			try {

				InitialContext ic = new InitialContext();
				statefulTestBean =   (CounterClick) ic.lookup("java:module/CounterClick");
				httpSession.setAttribute(STATEFUL_CLICK_BEAN_KEY, statefulTestBean);	          	       
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
		return statefulTestBean;
	}


}
