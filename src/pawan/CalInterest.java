package pawan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalInterest
 */
@WebServlet("/CalInterest")
public class CalInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalInterest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
    	rd.forward(req, resp);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		double pri_amt,ttl_int,net_val;
		float per_int,yrs;
		Boolean err_cnt=false;
		String pri_amt_txt,per_int_txt,yrs_txt;
		//ArrayList to save errors
		ArrayList<String> err_list=new ArrayList<String>();
		//Getting Values
		pri_amt_txt=request.getParameter("principle").trim();	
		per_int_txt=request.getParameter("per").trim();
		yrs_txt=request.getParameter("years").trim();
		//Validating Inputs
		//for Principal amount
		if(pri_amt_txt == "")	{
			err_list.add("Invalid ,Missing Pirincipal amount.");
		} else if(!pri_amt_txt.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			err_list.add("Invalid ,Principal must be Integer or Floating point value.");
		} else {
			err_list.add("Valid.");
		}
		//for Interest Percent
		if(per_int_txt == "") {
			err_list.add("Invalid , missing Interest Percent.");
		} else if(!per_int_txt.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			err_list.add("Invalid ,Interest Percent must be Integer or Floating point value.");
		} else {
			err_list.add("Valid.");
		}
		//for Years
		if(yrs_txt == "")	{
			err_list.add("Invalid , missing Years.");
		} else if(!yrs_txt.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			err_list.add("Invalid ,Years must be Integer or Floating point value.");
		} 
		else{
			err_list.add("Valid.");
		}
		//Setting the err_cnt counter
		for (int i = 0 ; i < err_list.size() ; i++) {
			if(!err_list.get(i).equals("Valid.")) {
				err_cnt=true;
				break;
			}
		}
		//Checking err_cnt 
		if(err_cnt==true) {
			request.setAttribute("result", "error");
			request.setAttribute("err_list", err_list);
		}else {
			pri_amt=Double.parseDouble(pri_amt_txt);	
			per_int=Float.parseFloat(per_int_txt);
			yrs=Float.parseFloat(yrs_txt);
			//Calculation logic
			ttl_int=pri_amt*per_int*yrs;
			net_val=pri_amt+ttl_int;
			//Adding values to request 
			request.setAttribute("result", "ok");
			request.setAttribute("total_interest", ttl_int);
			request.setAttribute("net_value", net_val);
			request.setAttribute("principle", pri_amt);
			request.setAttribute("per_int", per_int);
			request.setAttribute("yrs", yrs);
		}
		//Forwarding request
		rd.forward(request, response);

	}
}
