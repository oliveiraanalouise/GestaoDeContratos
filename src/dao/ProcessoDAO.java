package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Processo;

public class ProcessoDAO extends DAO{
	private final String colunaNotaFiscal = getNomeTabela() + ".notaFiscal",
						 colunaAditivo = getNomeTabela() + ".aditivo",
						 colunaValor = getNomeTabela() + ".valor",
						 colunaTipoAditivo = getNomeTabela() + ".tipoAditivo",
						 colunaDataPagamento = getNomeTabela() + ".dataPagamento",
						 colunaDataProcesso = getNomeTabela() + ".dataProcesso", 
						 colunaContrato = getNomeTabela() + ".contrato_id",
						 colunaSei = getNomeTabela() + ".numeroSei",
						 colunaAno = getNomeTabela() + ".ano",
						 colunaMes = getNomeTabela() + ".mes",
						 colunaUsuario = getNomeTabela() + ".usuario_id",
						 ordernarPorData = " order by " + colunaDataProcesso;

	public ProcessoDAO() {
		super("processo");
	}
	
	public ArrayList<Processo> getByContrato(int id){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaContrato + " = ?" + ordernarPorData
		);
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1,
				id
			);
			
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e){
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
				
		try {
			while(getResultado().next()){
				p = new Processo(
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getString(colunaAno),
					getResultado().getString(colunaMes),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					id,
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))
				);
				
				lista.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public void inserir(Processo processo){
		iniciaConexaoComBanco();
		
		setSqlQuery("insert into " + getNomeTabela() + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try{
			int posicao = 0;
			
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setString(
				++posicao, 
				processo.getNotaFiscal()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				processo.getAditivo()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				processo.getValor()
			);
			
			getStatement().setString(
				++posicao, 
				processo.getTipoAditivo()
			);
			
			getStatement().setDate(
				++posicao, 
				null //data de pagamento no momento � nula pois o processo est� sendo cadastrado agora
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(processo.getDataProcesso().getTime())
			);
			
			getStatement().setString(
				++posicao, 
				processo.getNumeroSei()
			);
			
			getStatement().setInt(
				++posicao, 
				processo.getIdContrato()
			);
			
			getStatement().setString(
				++posicao, 
				processo.getAno()
			);
			
			getStatement().setString(
				++posicao, 
				processo.getMes()
			);
			
			getStatement().executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		}
		encerraConexaocomBanco();
	}

	public ArrayList<Processo> getAllSemPagamento() {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null" + ordernarPorData + " desc"
		);
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e){
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
				
		try {
			while(getResultado().next()){
				p = new Processo(
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getString(colunaAno),
					getResultado().getString(colunaMes),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))					
				);
				
				lista.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}

	public void atualizarPagamento(String numeroSei) {
		iniciaConexaoComBanco();
		
		setSqlQuery("update "+getNomeTabela()+" set "+colunaDataPagamento+" = NOW() where "+colunaSei+" = ?");
		
		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			getStatement().setString(1, numeroSei);
			
			getStatement().executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		encerraConexaocomBanco();
	}

	public ArrayList<Processo> getByContratoSemPagamento(int idContrato) {
		iniciaConexaoComBanco();

		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null and " + 
			colunaContrato + " = ?"
		);

		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1, 
				idContrato
			);
			
			setResultado(
				getStatement().executeQuery()
			);
			
			while(getResultado().next()){
				p = new Processo(
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getString(colunaAno),
					getResultado().getString(colunaMes),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))
				);
				
				lista.add(p);
			}
		} catch (SQLException e){
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
}
