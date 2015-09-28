/*
    $('form').serializeObject()       get form data as an object
    $('form').formJSON(callback)      send form as ajax

    example:
    $('form[data-json]').formJSON(function(res) {
        $('h3.message').text(res.message);
    });
 */
 
(function($) {
    $.fn.serializeObject = function () {
        var arr = this.serializeArray(),
            o = {};
        $.each(arr, function () {
            var value = this.value != null ? this.value : '';
 
            if (o[this.name] != null) {
                if (!o[this.name].push)
                    o[this.name] = [o[this.name]];
                o[this.name].push(value);
            } else {
                o[this.name] = value;
            }
        });
        return o;
    };
 
    $.fn.formJSON = function(success, error) {
        this.on('submit', function(e) {
            e.preventDefault();
            var t = $(this);
 
            $.ajax({
    	        type: "POST",
    	        async: false,
    	        url: 'http://localhost:8810/MobileMall-Store/shop/viewItem.do',
                data: JSON.stringify(t.serializeObject()),
                dataType: 'json',
                processData: false,
                contentType: 'application/json',
                success: success,
                error: error,
                context: t
            });
        })
    };
})(jQuery);