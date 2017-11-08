
function requestUnauthorized(xhr) {
	window.location.href = xhr.getResponseHeader("Location");
}
 
!function($) {
    $.ajaxSetup({
        statusCode: {
            401: requestUnauthorized,
            403: requestUnauthorized
        }
    });
}(window.jQuery);


function busy() {
	jQuery("body").addClass("busy");
}

function idle() {
	jQuery("body").removeClass("busy");
}

