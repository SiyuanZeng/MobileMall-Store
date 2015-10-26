<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
	<META HTTP-EQUIV="Cache-Control" CONTENT="max-age=0">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<meta http-equiv="expires" content="0">
	<META HTTP-EQUIV="Expires" CONTENT="Tue, 01 Jan 1980 1:00:00 GMT">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<title>Mobile Mall</title>
	<link rel="stylesheet" href="<c:url value="/css/default.css"/>" />
	<link rel="stylesheet" href="<c:url value="/css/menu-2.css"/>" />
	<script type="text/javascript" src="<c:url value="/js/menu.js"/>"></script>
</head>
<body>

	<div id="container">
	<div id="site">
    	<div id="header">
    		<a href="<c:url value="/index.jsp"/>" title="homepage">
        	<div id="logo">
            	
            </div>
            </a>
            <div id="topnav">
            	<c:if test="${empty userSession.account}" > <a href="<c:url value="/shop/signonForm.do"/>" title="Sign in as user" class="inloggen">User Sign-In</a> | <a href="<c:url value="/admin/signon.do"/>" title="inloggen" class="inloggen">Admin Sign-In</a></c:if><c:if test="${!empty userSession.account}" ><a href="<c:url value="/shop/signoff.do"/>" title="inloggen" class="inloggen">Sign-Out</a></c:if>
            </div>
        </div>
        <div id="sse1">
		  <div id="sses1">
		    <ul>
		      <c:if test="${empty userSession.account}" >
		      	<li><a href="<c:url value="/shop/index.do"/>">Shop for mobiles</a></li>
		      	<li><a href="<c:url value="/shop/viewCart.do"/>">Shopping Cart</a></li>
		      </c:if>
		      
		      <c:if test="${!empty userSession.account}" >
				<c:if test="${ userSession.account.admin}" >
					 <li><a href="<c:url value="/admin/listAllOrders.do"/>">List Orders</a></li>
					 <li><a href="<c:url value="/admin/listAllUsers.do"/>">List Registered Users</a></li>
				</c:if>
			    <c:if test="${ !userSession.account.admin}" >
			    	<li><a href="<c:url value="/shop/index.do"/>">Shop for mobiles</a></li>
		      		<li><a href="<c:url value="/shop/viewCart.do"/>">Shopping Cart</a></li>
			    	<li><a href="<c:url value="/shop/editAccount.do"/>">Edit Account</a></li>
			    	<li><a href="<c:url value="/shop/listOrders.do"/>">View my orders</a></li>
			    </c:if>
			  </c:if>
		    </ul>
		    <c:if test="${empty userSession.account || !userSession.account.admin}">
		    <div class="search">
                <form method="post" action="<c:url value="/shop/searchProducts.do"/>">
					<input name="keyword" type="text" class="input" id="zoekwoord_boven" onclick="if(this.value == 'Type here...') { this.value = ''; }" onfocus="if(this.value == 'Type here...') { this.value = ''; }" onblur="if(this.value == '') { this.value = 'Type here...'; }" value="Type here...">
                	<input name="button" type="submit" class="searchbutton" value="" title="search">
				</form>
            </div>
            </c:if>
		  </div>
		</div>
		<div id="content">
			<a href='<c:if test="${empty userSession.account || !userSession.account.admin}"><c:url value="/shop/index.do"/></c:if>'><img src="<c:url value="/images/discount-banner.jpg"/>" width="940" /></a>
		</div>
    </div>
 	
    <s:form action="HelloWorld" >
        <s:textfield name="userName" label="User Name" />
        <s:submit />
    </s:form>   
    
    <div id="footer">
    	<div class="copyright">
    	© Mobile Mall All Rights reserved.
    </div>
    </div>
</div>
<!--[if lt IE 7.]>
<script type="text/javascript">
supersleight.init();
</script>
<![endif]-->


</body>
</html>