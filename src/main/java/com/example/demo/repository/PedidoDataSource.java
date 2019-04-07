package com.example.demo.repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.example.demo.controller.Console;
import com.example.demo.model.Pedido;

public class PedidoDataSource implements InterfacePedidoDataSource {

	Scanner sc = new Scanner(System.in);
	
	@Autowired
	Pedido p, 
	       pedidoExcluir, 
	       retornoConsulta, 
	       pedidoFromJson,
           retornoPedidoAlterar; 

	@Autowired
	Console console;
	
	List<Pedido> listPedido = new ArrayList<Pedido>();
	Map<Integer, Object> mapPedidos = new HashMap<>();
	
	String desejaSair;
	int cdPedido;
	
	public boolean acao(final String opcao) throws IOException {
		switch (opcao.toUpperCase()) {
		case "L":
			lerArquivo();
			break;
		case "X":
			lerArquivo();
			listarTodos();
			break;
		case "C":
			System.out.print("Entre com o codigo do pedido a ser consultado: ");
			cdPedido = sc.nextInt();
			consultar(cdPedido);
			break;
		case "I":
			String retorno = incluir(p = console.preencherDados());
			gravarArquivo(p);
			System.out.println(retorno);
			break;
		case "E":
			System.out.print("Entre com o codigo do pedido a ser excluido: ");
			cdPedido = sc.nextInt();
			excluir(cdPedido);
			break;
		case "A":
			System.out.print("Entre com o codigo do pedido a ser alterado: ");
			cdPedido = sc.nextInt();
			alterar(cdPedido);
			break;
		case "S":
			sair();
			if (desejaSair.toUpperCase().equals("S")) {
				if (listPedido != null) {
					//System.out.println(listPedido);
					//gravarArquivo(listPedido);
				}
				return true;
			}
			break;
		default:
			System.out.println("Opcao invalida");
			break;
		}

		return false;
	}

	@Override
	public void listarTodos() {
		mapPedidos.forEach((k,v)->System.out.println(mapPedidos));
     }		
		

	@Override
	public String incluir(Pedido pedido) {
		mapPedidos.put(pedido.getCodigoPedido(), pedido);
		gravarListaPedido(pedido);
		return "Pedido incluido com sucesso";
	}

	@Override
	public Pedido consultar(final int cdPedidoConsultar) {
		System.out.println();

		//@Autowired
		//Pedido retornoConsulta; //= new Pedido();
		retornoConsulta.setCodigoPedido(cdPedidoConsultar); 
		
		if (mapPedidos.containsKey(cdPedidoConsultar)) {
			retornoConsulta = (Pedido) mapPedidos.getOrDefault(retornoConsulta.getCodigoPedido(), null);
			retornoConsulta.setDataHoraAlteracao(null);
			console.retornarPedido(retornoConsulta);
		} else {
			System.out.println("Pedido nao encontrado!");
		}
		return retornoConsulta; 
	}
	
	@Override
	public void excluir(int cdPedidoExcluir) {
		
		//@Autowired
		//private Pedido pedidoExcluir; // = new Pedido();
		pedidoExcluir.setCodigoPedido(cdPedidoExcluir); 
		
		if (mapPedidos.containsKey(cdPedidoExcluir)) {
			mapPedidos.remove(pedidoExcluir.getCodigoPedido());
			System.out.println("Pedido: "+ cdPedido + ", excluido com sucesso");
		}else {
			System.out.println("Pedido n�o encontrado!");
		}
	}

	@Override
	public void alterar(int cdPedidoAlterar) {

		//@Autowired
		//private Pedido retornoPedidoAlterar = new Pedido();

		if (mapPedidos.containsKey(cdPedidoAlterar)) {
			retornoPedidoAlterar = consultar(cdPedidoAlterar);
			console.retornarPedido(retornoPedidoAlterar);
			mapPedidos.put(retornoPedidoAlterar.getCodigoPedido(), new Object());
		} else {
			System.out.println("Pedido n�o encontrado! Deseja incluir?");
			this.incluir(retornoPedidoAlterar);
		}
	}

	@Override
	public void sair() {
		System.out.println();
		System.out.print("Realmente deseja sair da aplicao? ");
		String x = sc.next();
		desejaSair = x;
	}
	
	@Override
	public List<Pedido> lerArquivo() {
		
		//@Autowired 
		List<Pedido> listPedidoFromJson = new ArrayList<>();
		//@Autowired 
		//Pedido pedidoFromJson; //= new Pedido();
		
        try {
    	    List<String> lines = Files.readAllLines(Paths.get("C:\\eclipse-workspace\\temp\\filePedido.json"));
            for(String line : lines) {
            	
                //Converte String JSON para objeto Java
            	listPedidoFromJson.add(new Gson().fromJson(line, Pedido.class));
            	pedidoFromJson = new Gson().fromJson(line, Pedido.class);
            	incluir(pedidoFromJson);
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPedidoFromJson;
    }

	
	public void gravarArquivoLista(List<Pedido> listPedido) throws IOException {
	    Gson gson = new Gson();
		  
	    for(Pedido pedido : listPedido) {
	    	String json = gson.toJson(pedido);
		    try {
		        final Charset name = StandardCharsets.UTF_8;
		        final Path path = Paths.get("C:\\eclipse-workspace\\temp\\filePedido.json");
		        if (!path.toFile().exists()) {
		            Files.createFile(path);
		        }
		        final BufferedWriter newBufferedWriter = Files.newBufferedWriter(path, name, StandardOpenOption.APPEND);
		        newBufferedWriter.append(json + "\r\n");
		        newBufferedWriter.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }			  
	    }
	}

	public void gravarArquivo(Pedido pedido) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(pedido);
		try {
	        final Charset name = StandardCharsets.UTF_8;
	        final Path path = Paths.get("C:\\eclipse-workspace\\temp\\filePedido.json");
	        if (!path.toFile().exists()) {
	            Files.createFile(path);
	        }
	        final BufferedWriter newBufferedWriter = Files.newBufferedWriter(path, name, StandardOpenOption.APPEND);
	        newBufferedWriter.append(json + "\r\n");
	        newBufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void gravarArquivo(Pedido pedido) throws IOException {
//		Gson gson = new Gson();
//		String json = gson.toJson(pedido);
//		StringBuilder jsonSB = new StringBuilder(json);
//		jsonSB.append("\n");
//		json = jsonSB.toString();
//		try {
//			Files.write(Paths.get("C:\\eclipse-workspace\\temp\\filePedido.json"),json.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void gravarListaPedido(Pedido pedido) {
		listPedido.add(p);
    }

	@Override
	public void gravarMemoria(Pedido pedido) {
	}

	@Override
	public void gravarArquivo(List<Pedido> listPedido) throws IOException {
		// TODO Auto-generated method stub
		
	}

}