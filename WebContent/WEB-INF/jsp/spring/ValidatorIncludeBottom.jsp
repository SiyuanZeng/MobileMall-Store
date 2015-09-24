
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
var reviewDialog = {
	modal: true,
	autoOpen: false,
	width: 1600,
	height: 900,
	title: "Enter your information",
	buttons: {}
};

//Show the modal form when clicked.
$('#reviewJQButton').click(function() {
	$("#reviewModal").dialog(reviewDialog).dialog('open');
});


//Test button
$('#test').click(function() {
	alert("test hibernate annotation");
	 $.ajax({
	        type: "POST",
	        async: false,
	        url: 'http://localhost:8810/MobileMall-Store/shop/validateAndAddReview.do',
	        data: $("#item").serialize(),
	        success: function (data) {
				$('#review-table').html(data);
	        }
	    });
});

//Test button
$("form#hReview").submit(function(e) {
	e.preventDefault();
	 $.ajax({
	        type: "POST",
	        async: false,
	        url: 'http://localhost:8810/MobileMall-Store/shop/validateAndAddReview.do',
	        data: $("form#hReview").serialize(),
	        success: function (data) {
				$('#main').html(data);
	        	$("#reviewModal").dialog('close');
	        	showReviewsInPages();
	        }
	    });
});

</script>

</html>