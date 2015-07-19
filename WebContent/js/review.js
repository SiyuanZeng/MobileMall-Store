function addReview() {
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


