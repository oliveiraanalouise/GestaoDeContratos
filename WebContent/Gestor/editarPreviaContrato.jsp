<!-- P�gina principal do Gestor Geral -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gest�o de Contratos da CTB</title>

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<%@page import=" entity.Processo,
					 entity.Contrato,
					 utilidades.FormatarCampo,
					 java.util.ArrayList"%>
	<%
		String id = (String) request.getParameter("id");
		@SuppressWarnings("unchecked")
		ArrayList<Processo> previaProcessos = (ArrayList<Processo>) request.getSession().getAttribute("previaProcessos");
		int i = Integer.parseInt(request.getParameter("i"));
	
	%>
	<form action="sistema?logica=EditarPrevia" method="post">
		<div class="aw-simple-panel__box">
			<div style="display: none">
				<input type="text" class="form-control input-lg" name="idContrato"
					value="${param.id}" >
			</div>

			<div style="display: none">
				<input name="i"
					value="${param.i}" >
			</div>
			<div style="display: none">
				<input name="acao"
					value="editar" >
			</div>
			<div style="display: none">
				<input type="text" class="form-control input-lg" name="idContrato"
					value="editar" >
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					value="<%=previaProcessos.get(i).getNumeroSei() %>" placeholder="N�mero SEI" name="numero" > <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					value="<%=previaProcessos.get(i).getNotaFiscal() %>"placeholder="Nota fiscal" name="notaFiscal" > <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Valor"
					value="<%=previaProcessos.get(i).getValorAsString() %>"id="valor" name="valor" maxlength="14" onkeyup="MascaraMoeda(this)"
					 /> <span class="form-control-feedback" aria-hidden="true">
				</span>
			</div>

			<div class="form-group custom-select has-feedback">
				<select name="mes" id="mes">
					<option value="<%=previaProcessos.get(i).getMes() %>"style="display: none"><%=previaProcessos.get(i).getMes() %></option>
					<option value="Janeiro">Janeiro</option>
					<option value="Fevereiro">Fevereiro</option>
					<option value="Mar�o">Mar�o</option>
					<option value="Abril">Abril</option>
					<option value="Maio">Maio</option>
					<option value="Junho">Junho</option>
					<option value="Julho">Julho</option>
					<option value="Agosto">Agosto</option>
					<option value="Setembro">Setembro</option>
					<option value="Outubro">Outubro</option>
					<option value="Novembro">Novembro</option>
					<option value="Dezembro">Dezembro</option>
				</select>
				<!-- select m�s de refer�ncia -->
			</div>
			<!-- fim div select cargos -->

			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg" placeholder="Ano"
					value="<%=previaProcessos.get(i).getAno() %>"name="ano" maxlength="4"  /> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				Data do processo: <input type="date" class="form-control input-lg"
					value="<%=previaProcessos.get(i).getDataProcesso() %>"name="data" > <span class="form-control-feedback"
					aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback" align="center">
				<input type="checkbox" name="aditivoChkBox" id="aditivoChkBox"
					onClick="aditivo()"> Houve aditivo?<br>
			</div>
			<div id="infoAditivo" style="display: none">
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						value="<%=previaProcessos.get(i).getAditivoAsString() %>" placeholder="Valor do aditivo" name="valorAditivo" maxlength="14"
						onkeyup="MascaraMoeda(this)" /> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						value="<%=previaProcessos.get(i).getTipoAditivo() %>"placeholder="Tipo de aditivo" name="tipoAditivo"> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
				<div class="form-group  has-feedback">
					Nova data de vencimento: <input type="date"
						class="form-control input-lg" name="novaDataVencimento"> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="form-group">
				<button type="submit"
					class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
			</div>
		</div>
	</form>

	<jsp:include page="../adds/Rodape.jsp"></jsp:include>

	<script type="text/javascript" src="js/campoValor.js"></script>
	<script type="text/javascript" src="js/mostrarCampoAditivo.js"></script>
</body>
</html>