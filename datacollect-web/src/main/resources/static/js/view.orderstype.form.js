(function() {
	
	var $ordersTypeForm;
	
	$(init());
	
	function init(){
		$ordersTypeForm = $('#ordersTypeForm');        
		initFormValidation();
    }
	
	function initFormValidation(){
		$ordersTypeForm.validate({
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
                    required: 'Order type is required'
                },
                serviceType: {
                    required: 'service type is required'
                }
            }, 
            errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            }
        });    
    }
    
    function resetForm(){
    	$ordersTypeForm[0].reset();
    }
})();