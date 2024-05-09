<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Iniciar Sesión</title>
</head>
<body>
<% if (request.getParameter("error") != null) { %>
<p>Error al iniciar sesión. Verifica tus credenciales.</p>
<% } %>
<form action="login" method="post">
  Usuario: <input type="text" name="username"><br>
  Contraseña: <input type="password" name="password"><br>
  <input type="submit" value="Iniciar Sesión">
</form>
</body>
</html>
