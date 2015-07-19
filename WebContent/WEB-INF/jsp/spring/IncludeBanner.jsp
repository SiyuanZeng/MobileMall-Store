<br>
<c:if test="${!empty userSession.account}">
	<table align="center" background="../images/bkg-topbar.gif" cellpadding="5" width="100%">
	<tr><td>
	<center>
			<c:out value="${userSession.account.username}" escapeXml="false"/>
			&nbsp;
	</center>
	</td></tr>
	</table>
</c:if>
