/*
 * Super classe com dados e métodos usuados por todos os DAO.
 * Todo DAO deve ser uma extensão dessa classe. 
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBConnection;
import lombok.Getter;
import lombok.Setter;

public class DAO {
	@Getter
	private Connection dbConnection = null;
	@Getter @Setter
	private String sqlQuery = null; 
	@Getter
	private String nomeTabela = null; 
	private String nomeBanco = "gestaodecontratos";
	private String usuarioBanco = "gestaodecontratos"; 
	private String senhaBanco = "suporte2017";
	private String ip = "localhost";
	@Getter @Setter
	private ResultSet resultado = null;
	@Getter @Setter
	private PreparedStatement statement = null;
	private boolean fecharConexao = true;
			
	protected DAO (String tabelaDB, Connection conexao){
//		recebe a conexao de outra classe. É usada quando um DAO chama outro para evitar abertura de novas conexões
		this.nomeTabela = tabelaDB;
		this.dbConnection = conexao;
		this.fecharConexao = false;
	}
	
	protected DAO (String tabelaDB){
		this.nomeTabela = tabelaDB;
	}

	protected void iniciaConexaoComBanco() {
		// inicia a conexão com o banco de dados
		if (dbConnection == null) {
//			s� inicia uma nova conexão caso ela ainda não exista
			this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();
		}
	}

	protected void encerraConexaocomBanco() {
		// fecha a conexão com o banco
		try {
			if(this.statement != null)
				this.statement.close();
			
			if (fecharConexao){
				/*
				 * s� fecha a conexão caso o DAO não seja dependente de outros
				 * exemplo: UsuarioDAO chama SetorDAO. SetorDAO não fecha a conexao, então essa variável de controle
				 * fica como falsa pois UsuarioDAO talvez ainda precise fazer novos acessos ao banco
				*/
				try {
					this.dbConnection.close();
					this.dbConnection = null;
				} catch (NullPointerException e) {}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected int getResultSize() {
//		retorna a quantidade de itens no resultSet após uma consulta ao banco
		int item = 0;
		
		try {
			this.resultado.last();
			item = this.resultado.getRow();
			this.resultado.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return item;
	}
}
