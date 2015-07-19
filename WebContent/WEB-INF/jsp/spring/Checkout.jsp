<%@ include file="IncludeTop.jsp" %>


<table border="0" width="100%" cellspacing="0" cellpadding="0">
<tr><td valign="top" width="20%" align="left">
<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/shop/viewCart.do"/>"><b><font color="BLACK" size="2">&lt;&lt; Shopping Cart</font></b></a>
</td></tr>
</table>
</td>

<td valign="top" align="center">
<h2 align="center">Checkout Summary</h2>

<table align="center" class="data" border="0" cellspacing="2" cellpadding="5">

  <tr>
  <th><b>Item ID</b></th>  <th><b>Product ID</b></th>  <th><b>Description</b></th> <th><b>In Stock?</b></th> <th><b>Quantity</b></th>  <th><b>List Price</b></th> <th><b>Total Cost</b></th>
  </tr>

<c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
  <tr>
  <td><b>
  <a href="<c:url value="/shop/viewItem.do"><c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>">
    <c:out value="${cartItem.item.itemId}"/>
	</a></b></td>
  <td><c:out value="${cartItem.item.product.productId}"/></td>
  <td>
    <c:out value="${cartItem.item.attribute1}"/>
    <c:out value="${cartItem.item.attribute2}"/>
    <c:out value="${cartItem.item.attribute3}"/>
    <c:out value="${cartItem.item.attribute4}"/>
    <c:out value="${cartItem.item.attribute5}"/>
    <c:out value="${cartItem.item.product.name}"/>
   </td>
  <td align="center"><c:out value="${cartItem.inStock}"/></td>
  <td align="center">
  <c:out value="${cartItem.quantity}"/>
  </td>
  <td align="right"><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
  <td align="right"><fmt:formatNumber value="${cartItem.totalPrice}" pattern="$#,##0.00" /></td>
  </tr>
</c:forEach>
<tr class="odd-row">
<td colspan="7" align="right">
<b>Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00" /></b><br />

</td>
</tr>
</table>
<center>
  <c:if test="${!cart.cartItemList.firstPage}">
    <a href="checkout.do?page=previousCart"><font color="green"><B>&lt;&lt; Prev</B></font></a>
  </c:if>
  <c:if test="${!cart.cartItemList.lastPage}">
    <a href="checkout.do?page=nextCart"><font color="green"><B>Next &gt;&gt;</B></font></a>
  </c:if>
</center>
<br>
<center><a href="<c:url value="/shop/newOrder.do"/>"><img border="0" src="../images/button_continue.gif" /></a></center>
</td>

<td valign="top" width="20%" align="right">&nbsp;</td>
</tr>
</table>

<%@ include file="IncludeBottom.jsp" %>
