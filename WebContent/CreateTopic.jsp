<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Topic</title>
<style>
.abc{
width:100%
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

	<h3><c:out value="${forum_name}"></c:out>Create Topic</h3>

	<table class="table" border=1>
		
		<form action='CreateTopic' method='POST'>
	
			<tr>
				<th scope="col"><label for="yourname">Your Name</label></th>
				<td><input type='text' style='width:100%' name='topic_name' id='topic_name' required placeholder="Your Name"/></td>
			</tr>
			
			<tr>
				<th scope="col"><label for="subject">Subject</label></th>
				<td><input type='text' style='width:100%' name='topic_subject' id='topic_subject' required placeholder="Subject"/></td>
			</tr>
			
			<tr>
				<th scope="col"><label for="content">Content</label></th>
				<td><textarea class="abc" rows=5 required name='topic_content' id='topic_content' placeholder="Content"></textarea></td>
			</tr>
			
			<tr>
				<td colspan=2><input type='hidden' name='forum_id' id='forum_id' value='${forum_id}' /><button class="btn btn-secondary" type='submit'>Post</button></td>
			</tr>
		
		</form>
	
	</table>

</body>
</html>