<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");
%>
<body>
	<%-- <%=request.getAttribute("result") %> --%>
	${requestScope.result}입니다.<br>
	
	${names[0]}<br>
	
	<%-- <%= ((Map)request.getAttribute("notice")).get("title") %> --%>
	${notice.title}<br>
	
	${result}<br>
	
	${empty param.n?"파라미터가 없습니다.":param.n}<br>
	${param.n/2}<br>
	${header.host}<br>
	${header.accept}
	
</body>
</html>