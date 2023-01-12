(function(){
	
	var $modalEnable = $('#modal-enable');
	var $modalDisable = $('#modal-disable');
	var lastSelectedRecordId;
		
	$(init());
	
	function init(){      
		initDataTable();
		initEvents();
    }
	
	function initEvents(){		
		$('#modal-disable-btn-ok').click(function(event){			
			$.post('dealer/disable/' + lastSelectedRecordId, function(){
				$('input[type=checkbox].input-switch[data-record-id="' + lastSelectedRecordId + '"').prop('checked', false).change();
			});
			$modalDisable.modal('hide');
		});
		$('#modal-enable-btn-ok').click(function(){
			$.post('dealer/enable/' + lastSelectedRecordId, function(){
				$('input[type=checkbox].input-switch[data-record-id="' + lastSelectedRecordId + '"').prop('checked', true).change();
			});
			$modalEnable.modal('hide');
		});
	}
	
	function initDataTable(){
	    $('#delaers-table').DataTable({
	        'paging': true,
	        'lengthChange': true,
	        'searching': true,
	        'ordering': true,
	        'info': true,
	        'autoWidth': true,
	        'rowCallback': function( row, data ) {
	        	$('.input-switch').bootstrapToggle();
	        	
	        	$('.input-switch-wrapper').click(function(event){
	    			event.stopPropagation();
	    		    var $checkbox = $(this).find('input[type=checkbox].input-switch');
	    		    lastSelectedRecordId = $checkbox.data('record-id');
	    		    
	    		    var lastSelectedDealerNumber = $checkbox.data('dealer-number');
	    		    if($checkbox.is(':disabled')){
	    		    	return;
	    		    }
	    		    if ($checkbox.is(':checked')){
	    		    	showDisableModal(lastSelectedDealerNumber);  
	    		    }else{
	    		    	showEnableModal(lastSelectedDealerNumber);
	    		    }
	    		});
	        },
	        'drawCallback': function(){
	        	$('#delaers-table').removeClass('invisible');
	        }
	    });
	}
	
	function showDisableModal(dealerNumber){
		$modalDisable.modal('show');       
		$modalDisable.find('.modal-body').find('h4').text(dealerNumber);
	}
	
	function showEnableModal(dealerNumber){
		$modalEnable.modal('show');       
		$modalEnable.find('.modal-body').find('h4').text(dealerNumber);
	}

})();