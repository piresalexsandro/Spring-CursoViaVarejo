package br.com.pedido.dao;

import java.util.List;

import br.com.pedido.entity.Funcionario;


/**
 * Interface do Usuario.
 * @author Alexsandro Pires.
 */
public interface IFuncionarioDAO {

	/**
	 * Insere um registro na tabela de Funcionario
	 * @param Objeto Funcionario .
	 * @return nao ha retorno a ser tratado
     * @throws Exception 
	 */	
	void insert(Funcionario obj);
	
	/**
	 * Atualiza dados na tabela de Funcionarios
	 * @param Objeto Funcionario .
	 * @return nao ha retorno a ser tratado
     * @throws Exception 
	 */	
	void update(Funcionario obj);

	/**
	 * Exclui um registro na tabela de Funcionario
	 * @param Id do Funcionario.
	 * @return nao ha retorno a ser tratado
     * @throws Exception 
	 */	
	void deleteById(Long id);
	
	/**
	 * Obtem um registro na tabela de Funcionario
	 * @param Id do Funcionario .
	 * @return O objeto Funcionario 
     * @throws Exception 
	 */	
	Funcionario findById(Long id);
	
	/**
	 * Obtem todos os objetos gravados no arquivo em disco. 
	 * @param null.
	 * @return a lista de Funcionarios gravada no aquivo fisico.
     * @throws Exception 
	 */	
	List<Funcionario> findAll();
	
}
