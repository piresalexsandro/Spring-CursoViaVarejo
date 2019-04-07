package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.FileRepository;

@Service
public class PedidoService {
	
	
	@Autowired
	private FileRepository repository;
	
	public void criarPedido(){
		//repository.salvarNaBase();
	}
	

}
