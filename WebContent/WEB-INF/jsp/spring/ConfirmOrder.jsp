<%@ include file="IncludeTop.jsp" %>

<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/shop/index.do"/>"><b><font color="BLACK" size="2">&lt;&lt; Main Menu</font></b></a>
</td></tr>
</table>

<p>
<center>
<b>Please confirm the information below and then press continue...</b>
</center>
<p>
<table width="60%" align="center" border="0" cellpadding="3" cellspacing="1" class="data">
<tr><th align="center" colspan="2">
  <font size="4"><b>Order</b></font>
  <br /><font size="3"><b><fmt:formatDate value="${orderForm.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></b></font>
</th></tr>

<tr><td colspan="2">
<font color="GREEN" size="4"><b>Billing Address</b></font>
</td></tr>
<tr><td>
First name:</td><td><c:out value="${orderForm.order.billToFirstName}"/>
</td></tr>
<tr><td>
Last name:</td><td><c:out value="${orderForm.order.billToLastName}"/>
</td></tr>
<tr><td>
Address 1:</td><td><c:out value="${orderForm.order.billAddress1}"/>
</td></tr>
<tr><td>
Address 2:</td><td><c:out value="${orderForm.order.billAddress2}"/>
</td></tr>
<tr><td>
City: </td><td><c:out value="${orderForm.order.billCity}"/>
</td></tr>
<tr><td>
State:</td><td><c:out value="${orderForm.order.billState}"/>
</td></tr>
<tr><td>
Zip:</td><td><c:out value="${orderForm.order.billZip}"/>
</td></tr>
<tr><td>
Country: </td><td><c:out value="${orderForm.order.billCountry}"/>
</td></tr>
<tr><td colspan="2">
<font color="GREEN" size="4"><b>Shipping Address</b></font>
</td></tr><tr><td>
First name:</td><td><c:out value="${orderForm.order.shipToFirstName}"/>
</td></tr>
<tr><td>
Last name:</td><td><c:out value="${orderForm.order.shipToLastName}"/>
</td></tr>
<tr><td>
Address 1:</td><td><c:out value="${orderForm.order.shipAddress1}"/>
</td></tr>
<tr><td>
Address 2:</td><td><c:out value="${orderForm.order.shipAddress2}"/>
</td></tr>
<tr><td>
City: </td><td><c:out value="${orderForm.order.shipCity}"/>
</td></tr>
<tr><td>
State:</td><td><c:out value="${orderForm.order.shipState}"/>
</td></tr>
<tr><td>
Zip:</td><td><c:out value="${orderForm.order.shipZip}"/>
</td></tr>
<tr><td>
Country: </td><td><c:out value="${orderForm.order.shipCountry}"/>
</td></tr>

</table>
<p>
<center><a href="<c:url value="/shop/newOrder.do?_finish=true"/>"><img border="0" src="../images/button_continue.gif" /></a></center>

<%@ include file="IncludeBottom.jsp" %>
