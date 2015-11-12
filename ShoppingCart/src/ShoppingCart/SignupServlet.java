package ShoppingCart;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Lineitem;
import models.Shoppinguser;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDB db = new ProductDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response){
		String username = (String) request.getParameter("username");
		String pwd = (String)request.getParameter("password");
		String email = (String)request.getParameter("email");
		HttpSession session = request.getSession();
		request.removeAttribute("useralreadyexist");
		session.removeAttribute("username");
		session.removeAttribute("userid");
		session.removeAttribute("isitemincart");
		session.removeAttribute("items");
		session.removeAttribute("subtotal");
		session.removeAttribute("totalcost");
		
		if(db.checkUserByName(username)){
			//user already exists
			request.setAttribute("useralreadyexist", "User Already Exists!");
			try {
				getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			//
			Shoppinguser user = new Shoppinguser();
			user.setEmail(email);
			user.setUsername(username);
			user.setUserpwd(pwd);
			if(db.addNewUser(user)){
				session.setAttribute("username", username);
				int userid = db.checkUser(username, pwd);
				session.setAttribute("userid", userid);
				
				//direct to home page
				try {
					response.sendRedirect("http://localhost:8080/ShoppingCart/HomeServlet");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				request.setAttribute("useralreadyexist", "Add New User Failed");
				try {
					getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

}
