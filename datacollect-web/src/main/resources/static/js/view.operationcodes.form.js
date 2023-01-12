(function() {
	
	var $operationCodeForm;
	
	$(init());
	
	function init(){
		$operationCodeForm = $('#operationCodeForm');
		initFormValidation();
    }
	
	function initFormValidation(){
		$operationCodeForm.validate({
            rules: {
            	code: {
                    required: true
                },
                serviceType: {
                	required: true
                }
            },
            messages: {
            	code: {
                    required: 'Code is required',
                    alphanumeric: 'Only alphanumeric characters are allowed'
                },
                serviceType: {
                    required: 'service type is required'
                },
            },
            errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            }
        });    
    }
    
    function resetForm(){
    	$operationCodeForm[0].reset();
    }
})();