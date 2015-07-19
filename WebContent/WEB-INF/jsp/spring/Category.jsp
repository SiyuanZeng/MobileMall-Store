<%@ include file="IncludeTop.jsp" %>

<table align="left" border="0" cellspacing="2" cellpadding="2">
<tr><td>
<a href="<c:url value="/shop/index.do"/>"><b><font color="BLACK" size="2"/>&lt;&lt; Main Menu</font></b></a>
</td></tr>
</table>

<p>
<center>
  <h2><c:out value="${category.name}"/></h2>
  </center>
<table align="center" class="data" border="0" cellspacing="2" cellpadding="3">
  <tr>  <th><b>Product ID</b></th>  <th><b>Name</b></th>  </tr>
<c:forEach var="product" items="${productList.pageList}" varStatus="theCount">
  <tr <c:if test="${theCount.count % 2 eq 0 }">class="odd-row"</c:if>>
  <td><b><a href="<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>">
	  <font color="BLACK"><c:out value="${product.productId}"/></font>
	</a></b></td>
  <td><c:out value="${product.name}"/></td>
  </tr>
</c:forEach>
  <tr><td>
  <c:if test="${!productList.firstPage}">
    <a href="?page=previous"><font color="white"><B>&lt;&lt; Prev</B></font></a>
  </c:if>
  <c:if test="${!productList.lastPage}">
    <a href="?page=next"><font color="white"><B>Next &gt;&gt;</B></font></a>
  </c:if>
  </td></tr>
</table>

<%@ include file="IncludeBottom.jsp" %></p></p>
