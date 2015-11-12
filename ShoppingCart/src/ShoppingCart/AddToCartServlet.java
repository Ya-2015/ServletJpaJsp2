package ShoppingCart;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Product;
import models.Productreview;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDB db = new ProductDB();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
		response.setContentType("text/html");
		String productId = request.getParameter("ProductId");
		
		int proid = Integer.parseInt(productId);
		Product p = db.getProductById(proid);
		
		//add post
		if(request.getParameter("post") != null){
			String post = (String)request.getParameter("post");
			int rating = Integer.parseInt(request.getParameter("rating"));
			
			Productreview review = new Productreview();
			review.setPost(post);
			review.setPostdate(new Date());
			review.setPoststar(rating);
			review.setProductid(proid);
			review.setUsername((String)request.getSession(false).getAttribute("username"));
			
			db.addPost(review);
		}
		
		if(p != null){
			HttpSession session = request.getSession(true);
			session.setAttribute("product", p);
			
			//get product review
			ArrayList<Productreview> reviews = db.getReviewById(proid);
			session.setAttribute("reviews", reviews);
			
			try {
				getServletContext().getRequestDispatcher("/AddProduct.jsp").forward(request, response);
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
