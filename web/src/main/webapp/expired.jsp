<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Calculadora</title>
</head>
<body>
	<br />
	<br />
	<br />
	<br />
	<div align="center">
		<img src="../../img/error.gif" style="margin: -11px 0px 2px -11px; float: center; width : 31px; height : 30px;"/>
	</div>
	<div align="center">
		<h2>ERROR</h2>
		<p style="color: red;">
			Descripción del error:
			<%=request.getAttribute("errorMsg")%></p>

		<form method="post" action="doLogOut">
			<div class="actionButtons">
				<button type="submit" class="btn-link">LogOut</button>
			</div>
		</form>
	</div>
</body>
</html>

