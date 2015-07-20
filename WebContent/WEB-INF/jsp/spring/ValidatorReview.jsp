<!-- *********************** Review Form ********************** -->
<div id="reviewModal">
	<form:form commandName="hReview" method="POST">
		<table id="review" border="1" style="width: 100%;">
			<tr>
				<td>
					<label for='title'>Review Title:</label><br>
				</td>
				<td>
					<form:input type="text" name="title" id="title" path="title"/>
					<br />(Maximum	20 words)
				</td>
			</tr>
			<tr>
				<td><label for='description'>Your Review:</label><br></td>
				<td>Please do not include: HTML reference to other
					retailers, pricing, personal information, any profane,
					inflammatory or copyrighted comments, or any copied content.<br />
					<form:textarea id="description" path="description" name="description" cols="60" rows="4"/>
				</td>
			</tr>
			<tr>
				<td><label for='name'>Name:</label><br></td>
				<td>
					<form:input type="text" name="name" id="name" path="name"/>
					<form:input type="text" name="itemId" id="itemId" path="itemId" value="${item.itemId}"/>
					<br/>(Maximum 20 words)
				</td>
			</tr>
		</table>
		<input type="submit" name='submit' value="Post it !" />
	</form:form>
</div>
