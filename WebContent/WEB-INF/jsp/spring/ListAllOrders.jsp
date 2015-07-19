<%@ include file="IncludeTop.jsp" %>

<center>
  <font size="4"><b>All Orders</b></font>
</center>
<div style="margin-left:40%"></div>
<c:if test="${not empty orderList}">
<table class="data" align="center" border="0" cellspacing="2" cellpadding="3">
  <tr>  <th><b>Order ID</b></th>  <th><b>Date</b></th></tr>
<c:forEach var="order" items="${orderList}" varStatus="theCount">
  <tr <c:if test="${theCount.count % 2 eq 0 }">class="odd-row"</c:if>>>
  <td><b><a href="<c:url value="/admin/viewOrder.do"><c:param name="orderId" value="${order.orderId}"/></c:url>">
	  <font color="BLACK"><c:out value="${order.orderId}"/></font>
  </a></b></td>
  <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss"/></td>
  </tr>
</c:forEach>
</table>
</c:if>
<%@ include file="IncludeBottom.jsp" %>
