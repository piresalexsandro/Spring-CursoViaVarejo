package com.example.servlets;

import java.io.IOException;
import java.math.BigDecimal;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro")
public class Cadastro extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		Integer idade = Integer.parseInt(req.getParameter("idade"));
		BigDecimal salario = new BigDecimal(req.getParameter("salario"));
		//Date data = new Date();
		
		Pedido pedido = new Pedido();
		pedido.setNome(nome);
		pedido.setEmail(email);
		pedido.setIdade(idade);
		pedido.setSalario(salario);
        
		IPedidoDAO dao = new PedidoDAOImp(null);
		dao.insert(pedido);
		
		resp.sendRedirect("index.html");
	}
	
}
