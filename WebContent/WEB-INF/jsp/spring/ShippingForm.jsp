<%@ include file="IncludeTop.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
function validate(){
	cleanErrors();
	var mikExp = /^[a-zA-Z\s]+$/;
	 var noError = true;
	 var firstName = $("#firstName").val();
	 var lastName = $("#lastName").val();
	 var city = $("#city").val();
	 var country = $("#country").val();
	 var state = $("#state").val();
	 var zip = $("#zip").val();
	 if(!mikExp.test(firstName)){
	        showError("Special Characters and numbers not allowed in first name");
	        noError = false;
	 }
	 if(!mikExp.test(lastName)){
        showError("Special Characters and numbers not allowed in last name");
        noError = false;
 	 }
	 if(!mikExp.test(city)){
        showError("Special Characters and numbers not allowed in city name");
        noError = false;
 	 }
	 if(!mikExp.test(country)){
        showError("Special Characters and numbers not allowed in country name");
        noError = false;
 	 }
	 if(!mikExp.test(state)){
        showError("Special Characters and numbers not allowed in state name");
        noError = false;
 	 }
	 if(!/^\d+$/.test(zip)){
	        showError("Zip code should be a number");
        noError = false;
	 }
	 return noError;
}

function cleanErrors(){
	 $("#errorContainer").html("");
}

function showError(error){
    $("#errorContainer").append("<li>"+error+"</li>");
}
</script>
<!-- Support for Spring errors holder -->
<spring:bind path="orderForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <B><FONT color=RED>
      <BR><c:out value="${error}"/>
    </FONT></B>
  </c:forEach>
</spring:bind>
<div id="flt_err_contiainer" style="margin: 0px auto; text-align: center;color: #FF0000">
                            <div id="flt_err" class="error">
                                <ol id="errorContainer" style="color: red;"></ol>
                            </div>
                        </div>
<form action="<c:url value="/shop/newOrder.do"/>" method="post" onsubmit="return validate()">

<TABLE bgcolor="#008800" border=0 cellpadding=3 cellspacing=1 bgcolor="#FFFF88">
<TR bgcolor="#FFFF88"><TD colspan=2>
<FONT color=GREEN size=4><B>Shipping Address</B></FONT>
</TD></TR>

<TR bgcolor="#FFFF88"><TD>
First name:</TD><TD>
  <spring:bind path="orderForm.order.shipToFirstName">
	  <input id="firstName" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
Last name:</TD><TD>
  <spring:bind path="orderForm.order.shipToLastName">
	  <input id="lastName" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
Address 1:</TD><TD>
  <spring:bind path="orderForm.order.shipAddress1">
	  <input type="text" size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
Address 2:</TD><TD>
  <spring:bind path="orderForm.order.shipAddress2">
	  <input type="text" size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
City: </TD><TD>
  <spring:bind path="orderForm.order.shipCity">
	  <input id="city" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
State:</TD><TD>
  <spring:bind path="orderForm.order.shipState">
	  <input id="state" type="text" size="4" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
Zip:</TD><TD>
  <spring:bind path="orderForm.order.shipZip">
	  <input id="zip" type="text" size="10" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="20"/>
  </spring:bind>
</TD></TR>
<TR bgcolor="#FFFF88"><TD>
Country: </TD><TD>
  <spring:bind path="orderForm.order.shipCountry">
	  <input id="country" type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="20"/>
  </spring:bind>
</TD></TR>

</TABLE>
<P>
<input type="image" src="../images/button_submit.gif">

</form>

<%@ include file="IncludeBottom.jsp" %>
