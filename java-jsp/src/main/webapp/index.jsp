<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt_br">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./css/style-login.css" />
    <title>Java Jsp</title>
  </head>
  <body>
    <div class="container">
      <h1>login</h1>

      <form class="login" action="<%= request.getContextPath() %>/ServletLogin" method="post">
      	<input type="hidden" value="<%= request.getParameter("url") %>" name="url" />
        <input
          type="text"
          name="login"
          placeholder="Informe o login"
          required="required"
        />
        <input
          type="password"
          name="senha"
          placeholder="Informe a senha"
          required="required"
        />
        <button type="submit">Login</button>
        <h3 class="error">${msg}</h3>
      </form>
    </div>
  </body>
</html>
