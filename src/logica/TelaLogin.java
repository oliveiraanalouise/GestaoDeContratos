/*
 * Limpa todos os dados da sess�o e redireciona para tela de login 
 */

package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TelaLogin implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().invalidate();
		return "/login.jsp";
	}

}
