<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
