<%@ include file="IncludeTop.jsp" %>

<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/shop/viewCategory.do"><c:param name="categoryId" value="${product.categoryId}"/></c:url>">
  <b><font style="color:#FFFFFF" size="2">&lt;&lt; <c:out value="${product.name}"/></font></b>
</a>
</td></tr>
</table>

<p>

<center>
  <b><font size="4"><c:out value="${product.name}"/></font></b>
</center>

<c:forEach var="item" items="${itemList.pageList}">
 <div class="product"
	title="${product.name}"
	onclick="location.href='<c:url value="/shop/viewItem.do"><c:param name="itemId" value="${item.itemId}"/></c:url>'">
	<div class="foto">
		<div class="center">
			<img
				src="../images/iPhone4_1.jpg" height="160" width="155">
		</div>
	</div>
	<div class="titel" style="height:auto; margin:5px 0 5px 20px;" title="Item Id"><c:out value="${item.itemId}"/></div>
	<div class="titel" style="height:auto; margin:5px 0 5px 20px;" title="Product Id"><c:out value="${item.product.productId}"/></div>
	<div class="titel" style="height:auto; margin:5px 0 5px 20px;" title="Description">
		<c:out value="${item.attribute1}"/>
	    <c:out value="${item.attribute2}"/>
	    <c:out value="${item.attribute3}"/>
	    <c:out value="${item.attribute4}"/>
	    <c:out value="${item.attribute5}"/>
	    <c:out value="${product.name}"/>
	</div>
	<div class="merk"><fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00"/></div>
	<div class="titel" style="height:auto; margin:5px 0 5px 20px;"><a href="<c:url value="/shop/addItemToCart.do"><c:param name="workingItemId" value="${item.itemId}"/></c:url>">
    <img border="0" src="../images/button_add_to_cart.gif"/>2
  </a></div>
	<div class="gaverder"></div>
	</div>
  
</c:forEach>
<div>
  <c:if test="${!itemList.firstPage}">
    <a href="?page=previous"><font color="white"><B>&lt;&lt; Prev</B></font></a>
  </c:if>
  <c:if test="${!itemList.lastPage}">
    <a href="?page=next"><font color="white"><B>Next &gt;&gt;</B></font></a>
  </c:if>
</div>
<%@ include file="IncludeBottom.jsp" %></p></p>
