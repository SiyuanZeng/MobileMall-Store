﻿function addReview() {
    $.ajax({
        type: "POST",
        async: false,
        url: 'http://localhost:8810/MobileMall-Store/shop/addReview.do',
        data: {
            title : $('#title').val(),
            name : $('#name').val(),
            description: $('#description').val(),
            itemId: $("#itemId").val()
        },
        success: function (data) {
			$('#review-table').html(data);
        }
    });
}

//How to assort all the jQuery examples
//Attach this new method to jQuery
$(function() {
	$("button, input:submit, input:button, a#jql, input:radio").button();

	//Hook up form validation
	//$('#reviewPost2').validate();

	//Make the form a modal dialog and create a button
	var reviewDialog = {
		modal : true,
		autoOpen : false,
		width : 1600,
		height : 900,
		title : "Enter your information",
		buttons : {}
	};

	//Show the modal form when clicked.
	$('#reviewJQButton').click(function() {
		$("#reviewModal").dialog(reviewDialog).dialog('open');
	});

	//Test button
	$('#test').click(function() {
		alert("test hibernate annotation");
		$.ajax({
			type : "POST",
			async : false,
			url : 'http://localhost:8810/MobileMall-Store/shop/viewItem.do',
			data : $("#item").serialize(),
			success : function(data) {
				$('#review-table').html(data);
			}
		});
	});

	$("form#hReview").submit(function(e) {
		e.preventDefault();
		$.ajax({
			type : "POST",
			async : false,
			url : 'http://localhost:8810/MobileMall-Store/shop/validateAndAddReview.do',
			// How do I use jQuery's form.serialize but exclude empty fields, without it, it will passing comma to the controller and cause the validator to fail the empty value test. 
			data : $(this).find(":input").filter(function () { return $.trim(this.value).length > 0 }).serialize(),
			success : function(data) {
				var result = $.trim(data);
				if (data.indexOf("DOCTYPE") < 0){
					$('#main').html(data);
					$("#reviewModal").dialog('destroy');
					showReviewsInPages();
				} else {
					var elements = $(data);
					var found = $('#reviewModal', elements);
					$("#reviewModal").dialog('destroy');
					$('#reviewModal').replaceWith(found);
					$("#reviewModal").dialog(reviewDialog).dialog('open');
				}
			}
		});
	});
});