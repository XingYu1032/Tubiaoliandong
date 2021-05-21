package com.Java.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Java.Bean.info;
import com.Java.Dao.dao;
import com.google.gson.Gson;

/**
 * Servlet implementation class getinfo
 */
@WebServlet("/getinfo")
public class getinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String tblx=request.getParameter("tblx");
		String type=request.getParameter("type");
		String num = request.getParameter("num");
		//System.out.println(num);
		List<info>  list= dao.getinfo(tblx,type,num);
		
		Gson gson= new Gson();
		String json=gson.toJson(list);
		System.out.println(json);

		request.setAttribute("json", json);
		request.setAttribute("info", list);
		request.setAttribute("type", tblx);
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}

}
