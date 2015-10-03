$(function()
{
   $.validator.addMethod("regex", function(value, element, regexpr) {          
	     return regexpr.test(value);
	   }, "Please enter a valid name.");  
	   
    $("form#hReview").validate(
      {
        rules: 
        {
          title: 
          {
            required: true,
            maxlength: 10
          },
          description: 
          {
	          required: true,
	          rangelength:[50,500]
          },
          name: 
          {
	          required: true,
	          maxlength: 50,
	          regex: /^[a-zA-Z ]*$/
          }
        }, 
        submitHandler: function(form) {
        	$("form#hReview").submit();
          }
      });	
});