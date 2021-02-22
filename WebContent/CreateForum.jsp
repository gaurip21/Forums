<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Forum</title>
<style>
.abc{
width:100%
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

	<h3><c:out value="${forum_name}"></c:out>Create Forum</h3>

	<table class="table" border=1>
		
		<form action='CreateForums' method='POST'>
	
			<tr>
				<th scope="col"><label for="forumname">Forum Name</label></th>
				<td><input type='text' style='width:100%' name='forumname' id='forumname' required placeholder="Forum Name"/></td>
			</tr>
			
			<tr>
				<td colspan=2><button class="btn btn-secondary" type='submit'>Post</button></td>
			</tr>
			
		</form>
	
	</table>

</body>
</html>