<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topic</title>
<style>
.button {
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

<h3><c:out value="${forum_name}"></c:out></h3>
<table class="table" border='1'>
	<tr>
		<th>Topic</th>
		<th>Author</th>
		<th>Replies</th>
		<th>Last Post</th>
	</tr>
	
	<c:forEach items = "${topics}" var = "currentForumTopic">
         <tr>
			<td>
			
				<form action='DisplayPosts' method='POST'>
				
					<input type='hidden' name='topic_id' id='topic_id' value='${currentForumTopic.getTopicid()}' />
					
					<input type='submit' class="button" value="${currentForumTopic.getTopicname()}"></input>
				
				</form>
				
			</td>
			<td><c:out value="${currentForumTopic.getAuthor()}"></c:out></td>
			<td><c:out value="${currentForumTopic.getReplies()}"></c:out></td>
			<td><c:out value="${currentForumTopic.getLatepost()}"></c:out></td>
		</tr>
    </c:forEach>
	
</table>

<br />

	<a href='CreateTopic.jsp'>Create Topic</a>

	<%-- <form action='CreateTopic' method='POST'>
				
		<input type='hidden' name='forum_id' id='forum_id' value='${forum_id}' />
				
		<button type='submit'>Create Topic</button>
				
	</form> --%>

</body>
</html>