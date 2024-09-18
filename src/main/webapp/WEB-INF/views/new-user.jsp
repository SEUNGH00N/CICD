<!DOCTYPE html>
<html>
<head>
<title>New User</title>
</head>
<body>
	<h1>New User</h1>
	<form action="/user/save" method="post">
		<label for="name">Name:</label> <input type="text" id="name"
			name="name" required /> <br /> <label for="email">Email:</label> <input
			type="email" id="email" name="email" required /> <br /> <input
			type="submit" value="Save" />
	</form>
	<a href="/users">Back to User List</a>
</body>
</html>
