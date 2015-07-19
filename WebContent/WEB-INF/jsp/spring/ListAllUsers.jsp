<%@ include file="IncludeTop.jsp" %>

<center>
  <font size="4"><b>Registered Users</b></font>
</center>
<div style="margin-left:40%">${message }</div>
<c:if test="${not empty accountList}">
<table class="data" align="center" cellspacing="2" cellpadding="3">
  <tr>  <th><b>Username</b></th>  <th><b>Full Name</b></th>  <th><b>Address</b></th>  <th><b>Email</b></th>  <th><b>Contact Number</b></th>  </tr>
<c:forEach var="account" items="${accountList}" varStatus="theCount">
  <tr <c:if test="${theCount.count % 2 eq 0 }">class="odd-row"</c:if>>
  <td><b>
	  <font color="BLACK"><c:out value="${account.username}"/></font>
  </b></td>
  <td>${account.firstName } ${account.lastName }</td>
  <td>${order.address1 }, ${order.address2 }, ${order.city }, ${order.state }, ${order.country }</td>
  <td>${account.email }</td>
  <td>${account.phone }</td>
  </tr>
</c:forEach>
</table>
</c:if>
<%@ include file="IncludeBottom.jsp" %>
