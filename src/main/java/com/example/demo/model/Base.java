package com.example.demo.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Base {
	private Date dataHoraInclusao;
	private Date dataHoraAlteracao;
	private Integer codigoPedido;
	
	public Base() {
	}

	public Base(Date dataHoraInclusao, Date dataHoraAlteracao, Integer codigoPedido) {
		this.dataHoraInclusao = dataHoraInclusao;
		this.dataHoraAlteracao = dataHoraAlteracao;
		this.codigoPedido = codigoPedido;
	}

	public Date getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(Date dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public Date getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	public void setDataHoraAlteracao(Date dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	public Integer getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPedido == null) ? 0 : codigoPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		if (codigoPedido == null) {
			if (other.codigoPedido != null)
				return false;
		} else if (!codigoPedido.equals(other.codigoPedido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Base " + dataHoraInclusao + ", " + dataHoraAlteracao + ", " + codigoPedido;
	}
	
	
}
