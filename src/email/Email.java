package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	private SimpleEmail email;
	private String  assunto = null,
			mensagem = null,
			smtp = "smtp.office365.com",
			emailFrom = "contrato.ctb@ctb.ba.gov.br",
			emailTo = null,
			senha = "-aA#EY^u", 
			nome = "Gest�o de Contratos";
	private int smtpPorta = 587;

	public Email(){}
	
	public void enviarCodigo(String emailTo, int codigo){
		//envia a mensagem com o codigo para o email recebido no parametro
		this.assunto = "Recupera��o de senha Gest�o de Contratos - CTB";
		this.mensagem = "O token de recupera��o �: " + codigo + ".\nSe n�o solicitou, desconsidere essa mensagem.";
		this.emailTo = emailTo;
		this.enviar();
	}
	
	public void enviarConfirmacaoCadastro(String emailTo, String nome){
		this.assunto = "Confirma��o de cadastro";
		this.emailTo = emailTo;
		this.mensagem = nome + ", obrigado por se cadastrar no Sistema de Gest�o de Contratos " +
			"da CTB. Agora basta aguardar o administrador liberar o seu acesso " +
			"para come�ar a usar. Te enviaremos outro email quando seu acesso for aprovado.";
		this.enviar();
	}
	
	private void enviar(){
		email = new SimpleEmail();
		
		email.setHostName(smtp);
		email.setSmtpPort(smtpPorta);
		email.setAuthenticator(new DefaultAuthenticator(emailFrom, senha));
		email.setStartTLSEnabled(true);
		try {
		    email.setFrom(emailFrom, nome);
		    email.setSubject(assunto);
		    email.setDebug(true);
		    email.setMsg(mensagem);
		    email.addTo(emailTo);
		    email.send();
		} catch (EmailException e) {
		    e.printStackTrace();
		}
		/*
		
		try {
			//monta a mensagem
			mail.setFrom(emailFrom, nome);
			mail.setSubject(assunto);
			mail.setMsg(mensagem);
			mail.setSSLOnConnect(true);
			mail.setAuthentication(emailFrom, senha);
			mail.setHostName(smtp);
			mail.setSmtpPort(smtpPorta);
			mail.addTo(emailTo);
			//envia a mensagem
			mail.send();
		} catch (EmailException e) {}*/
	}
}