<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post</title>
<style>
.abc{
width:100%;
}
.forumname {
  background: none!important;
  border: none;
  padding: 0!important;
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<h3>
	<form action='DisplayTopics' method='POST'>
		<input type='hidden' name='forum_id' id='forum_id' value='${forum_id}' />
		<button class="forumname" type='submit'>${forum_name}</button> > <c:out value="${topic_name}"></c:out>
	</form> 
</h3>
<br />
<table class="table" border='1'>
	<tr>
		<th>Author</th>
		<th>Content</th>
		<th>Posted On</th>
	</tr>
	
	<c:forEach items = "${posts}" var = "currentTopicPost">
         <tr>
			<td><c:out value="${currentTopicPost.getAuthorname()}"></c:out></td>
			<td><c:out value="${currentTopicPost.getContent()}"></c:out></td>
			<td><c:out value="${currentTopicPost.getPostedon()}"></c:out></td>
		</tr>
    </c:forEach>
	
</table>

<br />

	<table class="table" border=1>
		
		<form action='CreatePost' method='POST'>
	
			<tr>
				<th><label for="yourname">Your Name</label></th>
				<td><input class="abc" type='text' name='post_name' id='post_name' required placeholder="Your Name"/></td>
			</tr>
			
			<tr>
				<th><label for="reply">Reply</label></th>
				<td><input class="abc" type='text' name='post_reply' id='post_name' required placeholder="Reply"/></td>
			</tr>
			
			<tr>
				<td colspan=2><input type='hidden' name='topic_id' id='topic_id' value='${topic_id}' /><button class="btn btn-secondary" type='submit'>Post</button></td>
			</tr>
		
		</form>
	
	</table>

</body>
</html>