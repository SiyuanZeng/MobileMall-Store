<%@ include file="IncludeTop.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
function showError(error){
    $("#errorContainer").append("<li>"+error+"</li>");
}
function checkForm(){
    $("#errorContainer").html("");
    var noError = true;
    var mikExp = /^[a-zA-Z\s]+$/;
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var phoneExp = /^\+?[0-9]+[-. ]?[0-9]+[-. ]?[0-9]+$/;
    var username = $("#username").val();
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var address1 = $("#address1").val();
    var address2 = $("#address2").val();
    var city = $("#city").val();
    var country = $("#country").val();
    var zip = $("#zip").val();
    if(!mikExp.test(firstName)){
        showError("Special Characters and numbers not allowed in first name");
        noError = false;
    }
    if(!mikExp.test(lastName)){
        showError("Special Characters and numbers not allowed in last name");
        noError = false;
    }
    if(password == ""){
        showError("Password is mandatory");
        noError = false;
    }
    if(confirmPassword == "" || confirmPassword != password){
        showError("Confirm password mismatch");
        noError = false;
    }
    if(email == "" || !re.test(email)){
        showError("Invalid Emaid id");
        noError = false;
    }
    if(phone != "" && !phoneExp.test(phone)){
        showError("Invalid Phone number");
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
    if(!/^\d+$/.test(zip)){
	        showError("Zip code should be a number");
	        noError = false;
	 }
    return noError;
}
</script>
<!-- Support for Spring errors object -->
<spring:bind path="accountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <B><FONT color=RED>
      <BR><c:out value="${error}"/>
    </FONT></B>
  </c:forEach>
</spring:bind>
<div id="flt_err_contiainer" style="margin: 0px auto; text-align: center;">
                            <div id="flt_err" class="error">
                                <ol id="errorContainer"></ol>
                            </div>
                        </div>
<c:if test="${accountForm.newAccount}">
<form action="<c:url value="/shop/newAccount.do"/>" method="post" onsubmit="return checkForm()">
</c:if>
<c:if test="${!accountForm.newAccount}">
<form action="<c:url value="/shop/editAccount.do"/>" method="post" onsubmit="return checkForm()">
</c:if>

<table id="accountForm" cellpadding="10" cellspacing="0" align="center" border="1"><tr><td>

<table border="0" cellpadding="3" cellspacing="1" class="data">
<tr><th colspan="2">User Information</th></tr>
<tr><td>
User ID:</td><td>
<c:if test="${accountForm.newAccount}">
  <spring:bind path="accountForm.account.username">
	  <input id="username" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="25"/>
  </spring:bind>
</c:if>
<c:if test="${!accountForm.newAccount}">
  <c:out value="${accountForm.account.username}"/>
</c:if>
</td></tr><tr><td>
New password:</td><td>
  <spring:bind path="accountForm.account.password">
	  <input id="password" type="password" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="25"/>
  </spring:bind>
</td></tr><tr><td>
Repeat password:</td><td>
  <spring:bind path="accountForm.repeatedPassword">
	  <input id="confirmPassword" type="password" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="25"/>
  </spring:bind>
</td></tr>
</table>

<%@ include file="IncludeAccountFields.jsp" %>

</td></tr></table>

<br /><center>
<input border="0" type="image" src="../images/button_submit.gif" name="submit" value="Save Account Information" />
</center>

</form>

<%@ include file="IncludeBottom.jsp" %></p>
