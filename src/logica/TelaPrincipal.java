package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;

public class TelaPrincipal implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String cargo = ((Usuario) pedido.getSession().getAttribute("usuario")).getCargo();
		
//		Por causa do nome da servlet de tela principal do gestor geral
		if (!cargo.equals("Gestor geral"))
			return "sistema?logica=TelaPrincipal" + cargo;
		else
			return "sistema?logica=TelaPrincipalGestorGeral";
	}

}