$(function()
{
    $("#productform").validate(
      {
        rules: 
        {
          prodid: 
          {
            required: true,
            maxlength: 10
          },
          email: 
          {
            required: true,
            email: true,
            minlength:10
          },
          address:
          {
            required: true,
            rangelength:[10,250]
          },
          message: 
          {
            rangelength:[50,1050]
          }
        }
      });	
});