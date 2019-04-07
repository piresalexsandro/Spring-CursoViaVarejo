package com.example.demo.repository;

import java.io.IOException;
import java.util.List;

import com.example.demo.model.Pedido;

public interface InterfacePedidoDataSource {

	public List<Pedido> lerArquivo();
	//public void gravarArquivo(Pedido pedido);
	public void gravarMemoria(Pedido pedido);
	void gravarArquivo(List<Pedido> listPedido) throws IOException;
	public String incluir(Pedido pedido);
	public Pedido consultar(int cdPedidoConsultar);
	public void alterar(int cdPedidoAlterar);
	public void excluir(int cdPedidoExcluir);
	public void sair();
	public void listarTodos();
	
}
