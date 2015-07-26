<!-- *********************** Review Form ********************** -->
<div id="reviewModal">
	<form:form commandName="hReview" method="POST">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table id="review" border="1" style="width: 100%;">
			<tr>
				<td>
					<label for='title'>Review Title:</label><br>
				</td>
				<td>
					<div><form:input type="text" name="title" id="title" path="title"/></div>
					<div><form:errors path="title" cssClass="error" /></div>
					<div><br />(Maximum	20 words)</div>
				</td>
			</tr>
			<tr>
				<td><label for='description'>Your Review:</label><br></td>
				<td><div>Please do not include: HTML reference to other
					retailers, pricing, personal information, any profane,
					inflammatory or copyrighted comments, or any copied content.<br /></div>
					<div><form:textarea id="description" path="description" name="description" cols="60" rows="4"/></div>
					<div><form:errors path="description" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td><label for='name'>Name:</label><br></td>
				<td>
					<div><form:input type="text" name="name" id="name" path="name"/></div>
					<div><form:errors path="name" cssClass="error" /></div>
					<div><form:input type="hidden" name="itemId" id="itemId" path="itemId" value="${item.itemId}"/></div>
					<div><form:errors path="itemId" cssClass="error" /></div>
					<div><br/>(Maximum 20 words)</div>
				</td>
			</tr>
		</table>
		<input type="submit" name='submit' value="Post it !" />
	</form:form>
</div>
