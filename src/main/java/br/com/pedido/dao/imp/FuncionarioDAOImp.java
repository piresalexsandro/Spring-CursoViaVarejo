package br.com.pedido.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pedido.connection.MySQLConnection;
import br.com.pedido.dao.IFuncionarioDAO;
import br.com.pedido.entity.Funcionario;

public class FuncionarioDAOImp extends MySQLConnection implements IFuncionarioDAO {

	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
	public FuncionarioDAOImp(Connection conn) {
		this.conn = conn;
	}
			
	@Override
	public void insert(Funcionario obj) {
		
		try {
			conn = getConnection();
			StringBuilder query = new StringBuilder();
			
    		query.append("INSERT INTO funcionario (CD_FUN, NM_FUN, EMAIL_FUN, IDADE, DT_ADMIS, DT_DEMIS, VL_SAL) "); 	
			query.append("VALUES (NULL,?,?,?,?,?,?)");
			
			ps = conn.prepareStatement(query.toString());
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEmail());
			ps.setInt(3, obj.getIdade());
			ps.setDate(4, obj.getDataAdmissao());
		    ps.setDate(5, obj.getDataDemissao());
			ps.setBigDecimal(6, obj.getSalario());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Funcionario obj) {
		
		try {
			conn = getConnection();
			StringBuilder query = new StringBuilder();
			query.append("UPDATE Funcionario SET NM_FUN = ? "); 
			query.append(",EMAIL_FUN = ? ");
			query.append(",IDADE	= ? " );
			query.append(",DT_ADMIS = ? ");
			query.append(",DT_DEMIS = ? ");
			query.append(",VL_SAL = ? "); 
			//query.append(",DT_CRIACAO  = ? ");
			query.append(",WHERE CD_FUN = ? ");
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEmail());
			ps.setInt(3, obj.getIdade());
			ps.setDate(4, obj.getDataAdmissao());
			ps.setDate(5, obj.getDataDemissao());
			ps.setBigDecimal(6, obj.getSalario());
		//  ps.setDate(7, obj.getData());
			ps.executeUpdate();
			
		} catch (SQLException exc) {
			throw new RuntimeException(exc);
		} finally {
			closeConnection(rs,ps,conn);
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			conn = getConnection();
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM Funcionario WHERE CD_FUN = ?");
			
			ps.setLong(1, id);
			ps.executeQuery();
			
		}catch (SQLException exc) {
			throw new RuntimeException(exc);
		} finally {
			closeConnection(rs,ps,conn);
		}
	}

	@Override
	public Funcionario findById(Long id) {
		
		try {
			conn = getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT FUNCIONARIO.* FROM FUNCIONARIO "); 
			query.append("WHERE CD_FUN = ? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			//next pq a posição 0 e nula entao pegarmos a proximas
			if (rs.next()) {
				Funcionario resultado = new Funcionario();
				resultado.setId(rs.getLong("CD_FUN"));
				resultado.setNome(rs.getString("NM_FUN"));
				resultado.setEmail(rs.getString("EMAIL_FUN"));
				resultado.setIdade(rs.getInt("IDADE"));
				resultado.setDataAdmissao(rs.getDate("DT_ADMIS"));
				resultado.setDataAdmissao(rs.getDate("DT_DEMIS"));
				resultado.setSalario(rs.getBigDecimal("VL_SAL"));
				return resultado;
			}
			return null;
		}catch (SQLException exc) {
			throw new RuntimeException(exc);
		} finally {
			closeConnection(rs,ps,conn);
		}
	}

	@Override
	public List<Funcionario> findAll() {
		try {
			conn = getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT FUNCIONARIO.* FROM FUNCIONARIO "); 
			rs = ps.executeQuery();
			
			//next pq a posição 0 e nula entao pegarmos a proximas
			while (rs.next()) {
				List<Funcionario> listaFunc = new ArrayList<>();
				Funcionario resultado = new Funcionario();
				resultado.setId(rs.getLong("CD_FUN"));
				resultado.setNome(rs.getString("NM_FUN"));
				resultado.setEmail(rs.getString("EMAIL_FUN"));
				resultado.setIdade(rs.getInt("IDADE"));
				resultado.setDataAdmissao(rs.getDate("DT_ADMIS"));
				resultado.setDataAdmissao(rs.getDate("DT_DEMIS"));
				resultado.setSalario(rs.getBigDecimal("VL_SAL"));
				listaFunc.add(resultado);
				return listaFunc;
			}
			return null;
		}catch (SQLException exc) {
			throw new RuntimeException(exc);
		}finally {
			closeConnection(rs,ps,conn);
		}
	}

}
