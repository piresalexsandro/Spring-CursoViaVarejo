package com.example.demo.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Item{
	
	private Integer codigoItem;
	private BigDecimal valorItem;
	private Integer qtdeItem;
	private String nomeItem;
	
	public Item() {
	}

	public Item(final Integer codigoItem, final BigDecimal valorItem, final Integer qtdeItem, String nomeItem) {
		super();
		this.codigoItem = codigoItem;
		this.valorItem = valorItem;
		this.qtdeItem = qtdeItem;
		this.nomeItem = nomeItem;
	}

	public Integer getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(final Integer codigoItem) {
		this.codigoItem = codigoItem;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(final BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public Integer getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(final Integer qtdeItem) {
		this.qtdeItem = qtdeItem;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(final String nomeItem) {
		this.nomeItem = nomeItem;
	}

    public BigDecimal getValorTotalItem() {
		BigDecimal resultado = valorItem.multiply(BigDecimal.valueOf(qtdeItem.longValue()));		
		return resultado;
	}
	
	@Override
	public String toString() {
		return codigoItem + ", " + valorItem + ", " + qtdeItem + ", " + nomeItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
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
		Item other = (Item) obj;
		if (codigoItem == null) {
			if (other.codigoItem != null)
				return false;
		} else if (!codigoItem.equals(other.codigoItem))
			return false;
		return true;
	}

}
