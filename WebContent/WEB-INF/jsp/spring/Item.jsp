<%@ include file="IncludeTop.jsp"%>

<table align="left" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td><a
			href="<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>">
				<b><font color="BLACK" size="2">&lt;&lt; <c:out value="${product.name}" /></font></b>
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
		<td>
			<b> 
				<font size="4"> 
					<c:out value="${item.attribute1}" /> 
					<c:out value="${item.attribute2}" />
					<c:out value="${item.attribute3}" /> 
					<c:out value="${item.attribute4}" /> 
					<c:out value="${item.attribute5}" />
					<c:out value="${product.name}" />
				</font>
			</b>
		</td>
	</tr>

	<tr>
		<td>
			<font size="3"><i><c:out value="${product.name}" /></i></font>
		</td>
	</tr>
	<tr>
		<td><fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00" /></td>
	</tr>

	<tr>
		<td>
			<a href="<c:url value="/shop/addItemToCart.do"><c:param name="workingItemId" value="${item.itemId}"/></c:url>">
				<img border="0" src="../images/button_add_to_cart.gif" />
			</a>
			<input type="button" value="Test Hibernate Annotation" id="test" />
			<input type="button" value="Write Review" id="reviewJQButton" />
			<input id="itemId" value="${item.itemId}" type="hidden"/>
		</td>
		
	</tr>
	<tr>
		<td>
			<div id="reviewModal">
				<form id="review-form" name="review-form" method="POST" >
					<table id="review" border="1" style="width: 100%;">
						<tr>
							<td>
								<label for='title'>Review Title:</label><br>
							</td>
							<td> 
								<input type="text" id="title" name="title" /><br />
								(Maximum 20 words)
							</td>
						</tr>
						<tr>
							<td>
								<label for='description'>Your Review:</label><br>
							</td>
							<td>Please do not include: HTML reference to other retailers, pricing, personal information, any profane, inflammatory or copyrighted comments, or any copied content.<br />
								<textarea id="description" name="description" cols="60" rows="4"></textarea>
								</br>
							</td>
						</tr>
						<tr>
							<td>
								<label for='name'>Name:</label><br>
							</td>
							<td><input type="text" id="name" name="name"/> <br />
							(Maximum 20 words)
							</td>
						</tr>
					</table>
					<input type="submit" name='submit' value="Post it !"/>
				</form>
				
			</div>
		</td>
	</tr>
</table>

<table id="review-table">
	<c:forEach items="${item.reviews}" var="review">
		<tr>
			<td>
				<div class="review-name"><c:out value="${review.name}" /></div>
				<div class="review-time"><fmt:formatDate type="date" value="${review.timeStamp}" /></div>
			</td>
			<td>
				<div class="review-title"><c:out value="${review.title}" /></div>
				<div class="review-description"><c:out value="${review.description}" /></div><br/>
				<div class="review-helpful">0 of 0 users found this review helpful</div>
			</td>
		</tr>
	</c:forEach>
</table>


<%@ include file="IncludeBottom.jsp"%>
