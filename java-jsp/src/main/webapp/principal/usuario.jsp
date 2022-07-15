<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt_br">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	
	<jsp:include page="theme-loader.jsp"></jsp:include>
	
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<!-- Navbar include -->
			
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<!-- Sidebar include -->
					
					<jsp:include page="sidebar.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->
						
						<jsp:include page="page-header.jsp"></jsp:include>
						
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-md-6">
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de usuário</h5>
													</div>
													<div class="card-block">
														<form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group row form-static-label">
																<label class="col-sm-2 col-form-label">ID</label>
																<div class="col-sm-10">
																	<input type="text" class="form-control" name="id" id="id" readonly="readonly" value="${modelusuario.id}">
																</div>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome" class="form-control" value="${modelusuario.nome}">
																<span class="form-bar"></span> <label class="float-label">Insira seu nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email" class="form-control" required="required"
																			autocomplete="off" value="${modelusuario.email}">
																<span class="form-bar"></span> <label class="float-label">Email (exa@gmail.com)</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login" class="form-control" required="required"
																			autocomplete="off" value="${modelusuario.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Nome de usuário / login</label>
																<div class="col-form-label">${msgerro}</div>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha" class="form-control" required="required"
																			autocomplete="off" value="${modelusuario.senha}">
																<span class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
															
															<button type="button" id="novo" class="btn btn-primary" onclick="limparCampos();">Novo</button>
															<button class="btn btn-mat waves-effect waves-light btn-success">Salvar</button>
															<button type="button" class="btn btn-mat waves-effect waves-light btn-danger" onclick="criarDeleteAjax();">Excluir</button>
															<button type="button" class="btn btn-inverse" data-toggle="modal" data-target="#modalUsuario">Buscar</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>
									</div>
									<!-- Page-body end -->
									</div>
									<div id="styleSelector"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Required Jquery -->
		<jsp:include page="javascriptfile.jsp"></jsp:include>

		<!-- Modal -->
		<div class="modal fade" id="modalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuários</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">


					<div class="input-group mb-3">
						<input type="text" class="form-control" id="nomeBusca" placeholder="Nome" aria-label="nome" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>
					
					<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">id</th>
					      <th scope="col">Nome</th>
					      <th scope="col">Seleção</th>
					    </tr>
					  </thead>
					  <tbody>
					  	
					  </tbody>
					</table>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
		
			function buscarUsuario(){
				var nomeBusca = document.getElementById('nomeBusca').value;
				
				if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim != '') { // validando campo nulo
					var urlAction = document.getElementById('formUser').action;
					
						$.ajax({
						
						method: "get",
						url: urlAction, 
						data: "nomeBusca =" + nomeBusca + '&acao=buscarUser',
						succes: function(response){
							alert(response);
						}
						
					}).fail(xhr, status, errorThrown){
						alert('Erro ao buscar usuário: ' + xhr.responseText);
					});
				}
			}
			function criarDeleteAjax(){
				if (confirm('Tem certeza que quer excluir?')){
					var urlAction = document.getElementById('formUser').action;
					var idUser = document.getElementById('id').value;
					
					$.ajax({
						
						method: "get",
						url: urlAction, 
						data: "id=" + idUser + '&acao=deletarAjax',
						succes: function(response){
							limparCampos();
							document.getElementById('msg').textContent = response;
						}
						
					}).fail(xhr, status, errorThrown){
						alert('Erro ao deletar usuário por id: ' + xhr.responseText);
					});
					
				}
			}
			function criarDelete(){
				if (confirm('Tem certeza que quer excluir?')){
					document.getElementById("formUser").method = 'get';
					document.getElementById("acao").value = 'deletar';
					document.getElementById("formUser").submit();
				}
			}
			
			function limparCampos(){
				var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/
			    
			    for (p = 0; p < elementos.length; p++){
				    elementos[p].value = '';
			    }
			}
			
		</script>
</body>

</html>
