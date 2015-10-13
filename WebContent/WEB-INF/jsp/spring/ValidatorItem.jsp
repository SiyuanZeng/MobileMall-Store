<%@ include file="IncludeTop.jsp"%>

<!-- product information -->
<table align="left" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td><a
			href="<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>">
				<b> <font color="BLACK" size="2">&lt;&lt; <c:out
							value="${product.name}" /></font>
			</b>
		</a></td>
	</tr>
</table>
<p>
<table align="center" class="data" cellspacing="2" cellpadding="3" border="0" width="60%">
	<tr>
		<td><c:out value="${product.description}" escapeXml="false" /></td>
	</tr>
	<tr>
		<td width="100%"><b><c:out value="${item.itemId}" /></b></td>
	</tr>

	<tr>
		<td><b> <font size="4"> <c:out
						value="${item.attribute1}" /> <c:out value="${item.attribute2}" />
					<c:out value="${item.attribute3}" /> <c:out
						value="${item.attribute4}" /> <c:out value="${item.attribute5}" />
					<c:out value="${product.name}" />
			</font>
		</b></td>
	</tr>

	<tr>
		<td><font size="3"><i><c:out value="${product.name}" /></i></font>
		</td>
	</tr>
	<tr>
		<td><fmt:formatNumber value="${item.listPrice}"
				pattern="$#,##0.00" /></td>
	</tr>

	<!-- Buttons to click -->
	<tr>
		<td><a
			href="<c:url value="/shop/addItemToCart.do"><c:param name="workingItemId" value="${item.itemId}"/></c:url>">
				<img border="0" src="../images/button_add_to_cart.gif" />
		</a> 
			<input type="button" value="Write Review" id="reviewJQButton" /> 
			<input id="itemId" value="${item.itemId}" type="hidden" /></td>
	</tr>
</table>

<!-- Show message -->
<div id="main">
	<ul id="holder" class="two-col-special review-table">
		<c:forEach items="${item.hReviews}" var="review">
		    <li>
				<div class="review-name"> <c:out value="${review.name}" /> </div>
				<div class="review-time"> <fmt:formatDate type="date" value="${review.timeStamp}" /> </div>
		    </li>
		    <li>
				<div class="review-title"> <c:out value="${review.title}" /> </div>
				<div class="review-description"> <c:out value="${review.description}" /> </div> <br />
				<div class="review-helpful">0 of 0 users found this review helpful</div>
		    </li>
		</c:forEach>
    </ul>
</div>



<%@ include file="ValidatorReview.jsp"%>
<%@ include file="ValidatorIncludeBottom.jsp"%>
