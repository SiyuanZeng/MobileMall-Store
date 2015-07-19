 <%@ include file="IncludeTop.jsp" %>

<c:if test="${!empty message}">
  <b><font color="RED"><c:url value="${message}"/></font></b>
</c:if>



<c:if test="${!empty signonForwardAction}">
<input type="hidden" name="forwardAction" value="<c:url value="${signonForwardAction}"/>"/>
</c:if>

<div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>

		<form action="<c:url value="/shop/signon.do"/>" method="POST">
			<c:if test="${!empty signonForwardAction}">
				<input type="hidden" name="forwardAction" value="<c:url value="${signonForwardAction}"/>"/>
			</c:if>
			<fieldset>

				<p><label for="username">Username</label></p>
				<p><input type="text" id="username" name="username" value="j2ee"  /></p> <!-- JS because of IE support; better: placeholder="Username" -->

				<p><label for="password">Password</label></p>
				<p><input type="password" id="password" name="password" value="j2ee"  /></p> <!-- JS because of IE support; better: placeholder="password" -->

				<p><input type="submit" value="Sign In"></p>

			</fieldset>

		</form>

	</div> <!-- end login -->

<center>
<a href="<c:url value="/shop/newAccount.do"/>">
<img border="0" src="../images/button_register_now.gif" />
</a>
</center>

<%@ include file="IncludeBottom.jsp" %>

