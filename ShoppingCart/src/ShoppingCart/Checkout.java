package ShoppingCart;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Lineitem;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final boolean Lineitem = false;
	private ProductDB db = new ProductDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
		
		String purchaseNo = (String) request.getParameter("PurchaseId");
		
		if ((request.getSession(false).getAttribute("username") != null)){//process database
			
			//remove item
			if (purchaseNo != null){
				Lineitem it = db.getLineitemById(Integer.parseInt(purchaseNo));
				db.removeLineitem(it);
			}
			
			//update qty
			Object chg = request.getParameter("chgid2");
			if(chg != null){
				int chgid = Integer.parseInt(request.getParameter("chgid2"));
				int qty = Integer.parseInt(request.getParameter("chgquantity"));
				Lineitem it = db.getLineitemById(chgid);
				
				it.setQuantity(new BigDecimal(qty));
				it.setProductcost(qty * it.getPrice());
				
				db.updateLineitem(it);
			}
			
			HttpSession session = request.getSession(true);
			int userid = (int) request.getSession(false).getAttribute("userid");
			ArrayList<Lineitem> items = db.getLineitemsByUser(userid);
			
			//get total
			Object b = db.getTotalCostByUser(userid);
			if(b != null){
				session.setAttribute("totalcost", (double)b);
			}
			
			session.setAttribute("items", items);
			try {
				getServletContext().getRequestDispatcher("/Checkout.jsp").forward(request, response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
