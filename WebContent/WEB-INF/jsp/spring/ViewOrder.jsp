<%@ include file="IncludeTop.jsp" %>

<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/index.jsp"/>"><b><font color="BLACK" size="2">&lt;&lt; Main Menu</font></b></a>
</td></tr>
<tr><td>
<%--
<html:link paramId="orderId" paramName="order" paramProperty="orderId" page="/shop/viewOrder.do?webservice=true"><b><font color="BLACK" size="2">Use Web Service</font></b></c:url>
--%>
</td></tr>
</table>

<c:if test="${!empty message}">
  <center><b><c:out value="${message}"/></b></center>
</c:if>

<p>

<table width="60%" align="center" border="0" cellpadding="3" cellspacing="1" class="data">
<tr><td align="center" colspan="2">
  <font size="4"><b>Order #<c:out value="${order.orderId}"/></b></font>
  <br /><font size="3"><b><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></b></font>
</td></tr>
<tr><th colspan="2">
<font color="GREEN" size="4"><b>Payment Details</b></font>
</th></tr>
<tr><td>
Card Type:</td><td>
<c:out value="${order.cardType}"/>
</td></tr>
<tr><td>
Card Number:</td><td><c:out value="${order.creditCard}"/> <font color="red" size="2">* Fake number!</font>
</td></tr>
<tr><td>
Expiry Date (MM/YYYY):</td><td><c:out value="${order.expiryDate}"/>
</td></tr>
<tr><th colspan="2">
<font color="GREEN" size="4"><b>Billing Address</b></font>
</th></tr>
<tr><td>
First name:</td><td><c:out value="${order.billToFirstName}"/>
</td></tr>
<tr><td>
Last name:</td><td><c:out value="${order.billToLastName}"/>
</td></tr>
<tr><td>
Address 1:</td><td><c:out value="${order.billAddress1}"/>
</td></tr>
<tr><td>
Address 2:</td><td><c:out value="${order.billAddress2}"/>
</td></tr>
<tr><td>
City: </td><td><c:out value="${order.billCity}"/>
</td></tr>
<tr><td>
State:</td><td><c:out value="${order.billState}"/>
</td></tr>
<tr><td>
Zip:</td><td><c:out value="${order.billZip}"/>
</td></tr>
<tr><td>
Country: </td><td><c:out value="${order.billCountry}"/>
</td></tr>
<tr><th colspan="2">
<font color="GREEN" size="4"><b>Shipping Address</b></font>
</th></tr><tr><td>
First name:</td><td><c:out value="${order.shipToFirstName}"/>
</td></tr>
<tr><td>
Last name:</td><td><c:out value="${order.shipToLastName}"/>
</td></tr>
<tr><td>
Address 1:</td><td><c:out value="${order.shipAddress1}"/>
</td></tr>
<tr><td>
Address 2:</td><td><c:out value="${order.shipAddress2}"/>
</td></tr>
<tr><td>
City: </td><td><c:out value="${order.shipCity}"/>
</td></tr>
<tr><td>
State:</td><td><c:out value="${order.shipState}"/>
</td></tr>
<tr><td>
Zip:</td><td><c:out value="${order.shipZip}"/>
</td></tr>
<tr><td>
Country: </td><td><c:out value="${order.shipCountry}"/>
</td></tr>
<tr><td>
Courier: </td><td><c:out value="${order.courier}"/>
</td></tr>
<tr><td colspan="2">
  <b><font color="GREEN" size="4">Status:</font> <c:out value="${order.status}"/></b>
</td></tr>
<tr><td colspan="2">
<table width="100%" align="center" class="data" border="0" cellspacing="2" cellpadding="3">
  <tr>
  <th><b>Item ID</b></th>
  <th><b>Description</b></th>
  <th><b>Quantity</b></th>
  <th><b>Price</b></th>
  <th><b>Total Cost</b></th>
  </tr>
<c:forEach var="lineItem" items="${order.lineItems}">
  <tr class="odd-row">
  <td><b><a href="<c:url value="/shop/viewItem.do"><c:param name="itemId" value="${lineItem.itemId}"/></c:url>">
    <font color="BLACK"><c:out value="${lineItem.itemId}"/></font>
  </a></b></td>
  <td>
    <c:out value="${lineItem.item.attribute1}"/>
    <c:out value="${lineItem.item.attribute2}"/>
    <c:out value="${lineItem.item.attribute3}"/>
    <c:out value="${lineItem.item.attribute4}"/>
    <c:out value="${lineItem.item.attribute5}"/>
    <c:out value="${lineItem.item.product.name}"/>
  </td>
  <td><c:out value="${lineItem.quantity}"/></td>
  <td align="right"><fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00"/></td>
  <td align="right"><fmt:formatNumber value="${lineItem.totalPrice}" pattern="$#,##0.00"/></td>
  </tr>
</c:forEach>
  <tr class="odd-row">
  <td colspan="5" align="right"><b>Total: <fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00"/></b></td>
  </tr>
</table>
</td></tr>

</table>

<%@ include file="IncludeBottom.jsp" %>
