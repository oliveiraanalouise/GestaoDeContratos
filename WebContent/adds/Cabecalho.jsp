<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="ISO-8859-1" />
<title>SGC</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
<link rel="shortcut icon" type="image/png" href="/gestaodecontratos/layout/images/logo pequeno.png" />
</head>

<nav class="navbar  navbar-fixed-top  navbar-default  js-sticky-reference" id="main-navbar">
<%@ page import="entity.Usuario,
				 entity.Cargo"
%>
<% Usuario u = (Usuario) session.getAttribute("usuario");
   Cargo c = (Cargo) session.getAttribute("cargoParaLogin");
%>
	<div class="navbar-header">
		<a class="navbar-brand" href="/gestaodecontratos/sistema?logica=TelaPrincipal">
			<img align="left" height="20" src="/gestaodecontratos/layout/images/logo pequeno.png" />
			Sistema de Gest�o de Contratos
		</a>
	</div>

	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=1"><%=u.getCargo().get(1).getNome() %></a></li>
			<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=0"><%=u.getCargo().get(0).getNome() %></a></li>
			<li><a href="adds/ajuda.jsp">Ajuda</a></li>
			<li><a href="/gestaodecontratos/sistema?logica=Logout">Sair</a></li>
		</ul>
	</div>
</nav>
	<div class="page-header">
		<div class="container-fluid">
			<table>
				<tr>
					<th class="col-md-1" style="color: blue"><h3><i>Matr�cula: <%= u.getMatricula()%> </i></h3></th>
					<th class="text-center col-md-1" style="color: blue"><h3><i>Nome: <%= u.getNome()%> </i></h3></th>
					<th class="text-center col-md-1" style="color: blue"><h3><i><%= c.getNome()%> </i></h3></th>
					<th class="text-right col-md-1" style="color: blue"><h3><i>Setor: <%= u.getSetor().getSigla()%> </i></h3></th>
				</tr>
			</table>
		</div>
	</div>
</html>
