function appendErrorMsgBasedOnElementName(elementName, $errorElement){
	$('.has-error[for="' + elementName + '"').find('span.error.help-block').remove();
	$errorElement.addClass('help-block');
    $('.has-error[error-for="' + elementName + '"').append($errorElement);  
}

jQuery.validator.addMethod("alphanumeric", function(value, element) {
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}); 