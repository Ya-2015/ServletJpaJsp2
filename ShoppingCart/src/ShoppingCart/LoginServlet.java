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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDB db = new ProductDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		HttpSession session = request.getSession();
		request.removeAttribute("loggedinfailuire");
		session.removeAttribute("username");
		session.removeAttribute("userid");
		session.removeAttribute("isitemincart");
		session.removeAttribute("items");
		session.removeAttribute("subtotal");
		session.removeAttribute("totalcost");
		
		int userid = db.checkUser(username, pwd);
		if (userid != -1){
			
			try {
				session.setAttribute("username", username);
				session.setAttribute("userid", userid);
				//save data to database from shopping cart by unlogged in user
				ArrayList<Lineitem> itemlist = (ArrayList<Lineitem>) request.getSession(false).getAttribute("items");
				if(itemlist != null){
					for (Lineitem it : itemlist){
						it.setUserid(userid);
						db.addNewLineitem(it);
				}
				}
				//direct to home page
				response.sendRedirect("http://localhost:8080/ShoppingCart/HomeServlet");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			request.setAttribute("loggedinfailuire", true);
			try {
				getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
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
