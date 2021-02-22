<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forums</title>
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
<table class="table" border='1'>
	<tr>
		<th>Forum</th>
		<th>Topics</th>
	</tr>
	
	<c:forEach items = "${forums}" var = "forum">
         <tr>
			<td>
			
				<form action='DisplayTopics' method='POST'>
				
					<input type='hidden' name='forum_id' id='forum_id' value='${forum.getForumid()}' />
					
					<input type='submit' class="button" value="${forum.getForumname()}"></input>
				
				</form>
				
			</td>
			<td>
				<c:out value="${forum.getForumtopics()}"></c:out>
			</td>
		</tr>
    </c:forEach>
	
</table>

<br />

	<a href='CreateForum.jsp'>Create Forum</a>

</body>
</html>