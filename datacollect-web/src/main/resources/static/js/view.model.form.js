(function() {
	
	var $modelForm;
	
	$(init());
	
	function init(){
		$modelForm = $('#modelForm');        
		initFormValidation();
    }
	
	function initFormValidation(){
		$modelForm.validate({
            rules: {
            	code: {
                    required: true,
                    alphanumeric: true
                },
                year: {
                    required: true,
                    min: 1,
                    max: 9999
                }
            },
            messages: {
            	code: {
                    required: 'Code is required',
                    alphanumeric: 'Only alphanumeric characters are allowed'
                },
                year: {
                    required: 'Year is required',
                    min: 'Year must be a number grater than 0',
                    max: 'Year must be a number lower than 9999'
                },
                
            }, 
            errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            }
        });    
    }
    
    function resetForm(){
    	$modelForm[0].reset();
    }
})();