<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0 auto;
	max-width: 800px;
	padding: 0 20px;
}

.container {
	border: 2px solid #dedede;
	background-color: #f1f1f1;
	border-radius: 5px;
	padding: 10px;
	margin: 10px 0;
}

.darker {
	border-color: #ccc;
	background-color: #ddd;
}

.container::after {
	content: "";
	clear: both;
	display: table;
}

.container img {
	float: left;
	max-width: 60px;
	width: 100%;
	margin-right: 20px;
	border-radius: 50%;
}

.container img.right {
	float: right;
	margin-left: 20px;
	margin-right: 0;
}

.time-right {
	float: right;
	color: #aaa;
}

.time-left {
	float: left;
	color: #999;
}
</style>
</head>
<body>
	<div id="login">
		<h1>
			<strong>Group Chart...</strong>.
		</h1>
		<form>
			<table>
				<c:forEach var="msg" items="${messages}">
					<tr>
						<td><div class="container">
								<h3>${msg.name}:</h3>
								<p>${msg.message}</p>
								<span class="time-right">${msg.sendDate}</span>
							</div></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<h6>${statusMessage}</h6>
		<form action="/message/send" method="post" autocomplete="off">
			<p>
				<input type="text" name="message" autofocus>
			</p>
			<input type="hidden" name="userName" value="${userName}">
			<p>
				<input type="submit" value="SEND">
			</p>
			<p>
				<input type="button" onClick="history.go(0)" VALUE="Refresh">
			</p>
		</form>
	</div>
</body>
</html>