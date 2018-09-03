/*Redireciona para p�gina de erro "falta de acesso"*/
package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErroArquivoInexistente implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		return "/errosPag/arquivoNaoEncontrado.html";
	}

}
