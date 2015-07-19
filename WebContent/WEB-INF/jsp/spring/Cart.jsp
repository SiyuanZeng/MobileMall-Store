<%@ include file="IncludeTop.jsp" %>
<script type="text/javascript">
	var numberItem =  <c:out value="${available}"/>;
	function checkItems(){
		var q = $("#quantity").val();
		if(/^\d+$/.test(q) && numberItem >= parseInt(q)){
			return true;
		} else {
			$("#message").html("Quantity exceeds stock");
			return false;
		}
	}
</script>
<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/shop/index.do"/>"><b><font color="BLACK" size="2">&lt;&lt; Main Menu</font></b></a>
</td></tr>
</table>
<table border="0" width="90%" cellspacing="0" cellpadding="0">
<tr><td valign="top" align="center">
<h2 align="center">Shopping Cart</h2>
<form action="<c:url value="/shop/updateCartQuantities.do"/>" method="post" onsubmit="return checkItems()">
<div id="message" style="text-align: center;color: #FF0000 !important;margin 0px auto;">${message }</div>
<table align="center" class="data" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <th><b>Item ID</b></th>  <th><b>Product ID</b></th>  <th><b>Description</b></th> <th><b>In Stock?</b></th> <th><b>Quantity</b></th>  <th><b>List Price</b></th> <th><b>Total Cost</b></th>  <th>&nbsp;</th>
  </tr>

<c:if test="${cart.numberOfItems == 0}">
<tr class="odd-row"><td colspan="8"><b>Your cart is empty.</b></td></tr>
</c:if>

<c:forEach var="cartItem" items="${cart.cartItemList.pageList}" varStatus="theCount">
  <tr <c:if test="${theCount.count % 2 eq 0 }">class="odd-row"</c:if>>
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
  <input id="quantity" type="text" size="3" name="<c:out value="${cartItem.item.itemId}"/>" value="<c:out value="${cartItem.quantity}"/>" />
  </td>
  <td align="right"><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
  <td align="right"><fmt:formatNumber value="${cartItem.totalPrice}" pattern="$#,##0.00" /></td>
  <td><a href="<c:url value="/shop/removeItemFromCart.do"><c:param name="workingItemId" value="${cartItem.item.itemId}"/></c:url>">
    <img border="0" src="../images/button_remove.gif" />
  </a></td>
  </tr>
</c:forEach>
<tr class="odd-row">
<td colspan="7" align="right">
<b>Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00" /></b><br/>
<c:if test="${cart.numberOfItems > 0}">
<input type="image" border="0" src="../images/button_update_cart.gif" name="update" />
</c:if>

</td>
<td>&nbsp;</td>
</tr>
</table>
<center>
  <c:if test="${!cart.cartItemList.firstPage}">
    <a href="<c:url value="viewCart.do?page=previousCart"/>"><font color="green"><B>&lt;&lt; Prev</B></font></a>
  </c:if>
  <c:if test="${!cart.cartItemList.lastPage}">
    <a href="<c:url value="viewCart.do?page=nextCart"/>"><font color="green"><B>Next &gt;&gt;</B></font></a>
  </c:if>
</center>
</form>

<c:if test="${cart.numberOfItems > 0}">
<br /><center><a href="<c:url value="/shop/checkout.do"/>" onclick="return checkItems()"><img border="0" src="../images/button_checkout.gif" /></a></center>
</c:if>

</td>

<tr><td colspan="8" valign="top" width="20%" align="right">
<c:if test="${!empty userSession.account.username}">
  <c:if test="${userSession.account.listOption}">
    <%@ include file="IncludeMyList.jsp" %>
  </c:if>
</c:if>
</td></tr>

</tr>
</table>

<%@ include file="IncludeBanner.jsp" %>

<%@ include file="IncludeBottom.jsp" %>
