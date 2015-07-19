
<c:if test="${!empty userSession.myList}">
<p>&nbsp;</p>
<table align="right" class="data" border="0" cellspacing="2" cellpadding="3" class="width:90%;">
  <tr><th>
<font size="4"><b>My Favorites</b></font>
<font size="2"><i><br />Shop for more of your <br />favorite brand here.</i></font>
  </th></tr>
  <tr>
  <td>
  <c:forEach var="product" items="${userSession.myList.pageList}" >
    <a href="<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>">
      <c:out value="${product.name}"/>
    </a>
    <br/>
    <font size="2">(<c:out value="${product.productId}"/>)</font>
    <br/>
  </c:forEach>
  </td>
  </tr>
  <tr>
  <td>
  <c:if test="${!userSession.myList.firstPage}">
    <a href="viewCart.do?page=previous"><font color="white"><B>&lt;&lt; Prev</B></font></a>
  </c:if>
  <c:if test="${!userSession.myList.lastPage}">
    <a href="viewCart.do?page=next"><font color="white"><B>Next &gt;&gt;</B></font></a>
  </c:if>
  </td>
  </tr>

</table>
</c:if>
