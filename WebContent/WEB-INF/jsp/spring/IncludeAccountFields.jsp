

<TABLE border=0 cellpadding=3 cellspacing=1 class="data">
<tr><th colspan="2">Account Information</th></tr>
<TR><TD>
First name:</TD><TD>
  <spring:bind path="accountForm.account.firstName">
	  <input id="firstName" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Last name:</TD><TD>
  <spring:bind path="accountForm.account.lastName">
	  <input id="lastName" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Email:</TD><TD>
  <spring:bind path="accountForm.account.email">
	  <input id="email" type="text" size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Phone:</TD><TD>
  <spring:bind path="accountForm.account.phone">
	  <input id="phone" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="15"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Address 1:</TD><TD>
  <spring:bind path="accountForm.account.address1">
	  <input id="address1" type="text" size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Address 2:</TD><TD>
  <spring:bind path="accountForm.account.address2">
	  <input id="address2" type="text" size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="40"/>
  </spring:bind>
</TD></TR>
<TR><TD>
City: </TD><TD>
  <spring:bind path="accountForm.account.city">
	  <input id="city" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
State:</TD><TD>
  <spring:bind path="accountForm.account.state">
	  <input id="state" type="text" size="4" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="80"/>
  </spring:bind>
</TD></TR>
<TR><TD>
Zip:</TD><TD>
  <spring:bind path="accountForm.account.zip">
	  <input id="zip" type="text" size="10" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="20"/>
  </spring:bind>
</TD></TR>

<TR><TD>
Country: </TD><TD>
  <spring:bind path="accountForm.account.country">
	  <input id="country" type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" maxlength="20"/>
  </spring:bind>
</TD></TR>

</TABLE>

<TABLE class="data" border=0 cellpadding=3 cellspacing=1 >
<tr><th colspan="2">Profile Information</th></tr>
<TR><TD>
Language Preference:</TD><TD>
  <spring:bind path="accountForm.account.languagePreference">
    <select name="<c:out value="${status.expression}"/>">
      <c:forEach var="language" items="${languages}">
        <option <c:if test="${language == status.value}">selected</c:if> value="<c:out value="${language}"/>">
        <c:out value="${language}"/></option>
	    </c:forEach>
    </select>
  </spring:bind>
</TD></TR><TR><TD>
Favourite Category:</TD><TD>
  <spring:bind path="accountForm.account.favoriteCategoryId">
    <select name="<c:out value="${status.expression}"/>">
      <c:forEach var="category" items="${categories}">
        <option <c:if test="${category.categoryId == status.value}">selected</c:if> value="<c:out value="${category.categoryId}"/>">
        <c:out value="${category.name}"/></option>
	    </c:forEach>
    </select>
  </spring:bind>
</TD></TR><TR><TD colspan=2>
  <spring:bind path="accountForm.account.listOption">
    <input type="checkbox" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value}">checked</c:if>>
		Enable MyList
  </spring:bind>

</TD></TR><TR><TD colspan=2>
  <spring:bind path="accountForm.account.bannerOption">
    <input type="checkbox" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value}">checked</c:if>>
		Enable MyBanner
  </spring:bind>
</TD></TR>
</TABLE>
