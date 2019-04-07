package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PedidoService;


@RestController
public class HelloController {

	@Autowired
	private PedidoService service;
	
	@GetMapping(value="entrar")
	public String entrar() {
		return "Voce entrou";
	}
		
	@GetMapping(value="sair")
	public String sair() {
		return "Voce saiu";
	}
}
