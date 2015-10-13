
</div>
</div>

</div>
<div id="footer">
	<div class="copyright">© Mobile Mall All Rights reserved.</div>
</div>
<!--[if lt IE 7.]>
<script type="text/javascript">
supersleight.init();
</script>
<![endif]-->



</body>

<script type="text/javascript">


$("button, input:submit, input:button, a#jql, input:radio").button();

//Hook up form validation
//$('#reviewPost2').validate();

//Make the form a modal dialog and create a button
$('#reviewModal').dialog({
	modal: true,
	autoOpen: false,
	width: 800,
	height: 450,
	title: "Enter your information",
	buttons: {}
});

//Show the modal form when clicked.
$('#reviewJQButton').click(function() {
	$("#reviewModal").dialog('open');
});


</script>

</html>