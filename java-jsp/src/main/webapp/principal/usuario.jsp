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
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post">
															<div class="form-group row">
																<label class="col-sm-2 col-form-label">ID</label>
																<div class="col-sm-10">
																	<input type="text" class="form-control" name="id"
																		id="id" readonly="readonly" value="${modelusuario.id}">
																</div>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" value="${modelusuario.nome}">
																<span class="form-bar"></span> <label
																	class="float-label">Insira seu nome</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelusuario.email}">
																<span class="form-bar"></span> <label
																	class="float-label">Email (exa@gmail.com)</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="cidade" id="cidade"
																	class="form-control" required="required"
																	value="${modelusuario.cidade}"> <span
																	class="form-bar"></span> <label class="float-label">Cidade</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="telefone" id="telefone"
																	class="form-control" required="required"
																	value="${modelusuario.telefone}"> <span
																	class="form-bar"></span> <label class="float-label">Telefone</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	autocomplete="off"
																	"
                                                                	value="${modelusuario.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Nome de usuário / login</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelusuario.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Senha</label>
															</div>
															<button
																class="btn btn-mat waves-effect waves-light btn-primary ">Novo</button>
															<button
																class="btn btn-mat waves-effect waves-light btn-success">Salvar</button>
															<button
																class="btn btn-mat waves-effect waves-light btn-danger ">Excluir</button>
														</form>
													</div>
												</div>
											</div>
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
</body>

</html>
