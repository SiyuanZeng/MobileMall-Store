<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <%@ include file="IncludeTop.jsp" %>

<c:if test="${!empty message}">
  <b><font color="RED"><c:url value="${message}"/></font></b>
</c:if>

<div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>

		<form:form commandName="loginform" method="POST">
			<c:if test="${!empty signonForwardAction}">
				<input type="hidden" name="forwardAction" value="<c:url value="${signonForwardAction}"/>"/>
			</c:if>
			<fieldset>

			<p><label for="username">Username</label></p>
			<p><form:input type="text" id="username" path="username" value="administrator"  /></p> <!-- JS because of IE support; better: placeholder="Username" -->

			<p><label for="password">Password</label></p>
			<p><form:input type="password" id="password" path="password" value="password"  /></p> <!-- JS because of IE support; better: placeholder="password" -->

			<p><input type="submit" value="Sign In"></p>

			</fieldset>

		</form:form>

	</div> <!-- end login -->

<%@ include file="IncludeBottom.jsp" %>

