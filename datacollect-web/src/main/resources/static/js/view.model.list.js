(function () {
	
	var $modalEnable = $('#modal-enable');
	var $modalDisable = $('#modal-disable');
	var lastSelectedRecordId;
	
	$(init());
	
	function init(){      
		initDataTable();
		initEvents();
    }
	
	function initDataTable(){
		$('#modelTable').DataTable({
	        'paging': true,
	        'lengthChange': true,
	        'searching': true,
	        'ordering': true,
	        'info': true,
	        'autoWidth': true,
	        'columnDefs': [
	        	{ "width": "20%" },
	        	{ "width": "10%" },
	        	{ "width": "20%" },
	        	{ "width": "25%" },
	            { "width": "15%" },
	        	{ "width": "10%" }
            ],
	        'rowCallback': function( row, data ) {
	        	$('.input-switch').bootstrapToggle();
	        	
	        	$('.input-switch-wrapper').click(function(event){
	    			event.stopPropagation();
	    		    var $checkbox = $(this).find('input[type=checkbox].input-switch');
	    		    lastSelectedRecordId = $checkbox.data('record-id');
	    		    if($checkbox.is(':disabled')){
	    		    	return;
	    		    }
	    		    if ($checkbox.is(':checked')){
	    		    	showDisableModal();  
	    		    }else{
	    		    	showEnableModal();
	    		    }
	    		});
	        },
	        'drawCallback': function(){
	        	$('#modelTable').removeClass('invisible');
	        }
	    });
	}
	
	function initEvents(){		
		$('#modal-disable-btn-ok').click(function(event){			
			$.post('model/disable/' + lastSelectedRecordId, function(){
				$('input[type=checkbox].input-switch[data-record-id="' + lastSelectedRecordId + '"').prop('checked', false).change();
			});
			$modalDisable.modal('hide');
		});
		$('#modal-enable-btn-ok').click(function(){
			$.post('model/enable/' + lastSelectedRecordId, function(){
				$('input[type=checkbox].input-switch[data-record-id="' + lastSelectedRecordId + '"').prop('checked', true).change();
			});
			$modalEnable.modal('hide');
		});
	}
	
	function showDisableModal(){
		$modalDisable.modal('show');       
		$modalDisable.find('.modal-body').find('h4').text(lastSelectedRecordId);
	}
	
	function showEnableModal(){
		$modalEnable.modal('show');       
		$modalEnable.find('.modal-body').find('h4').text(lastSelectedRecordId);
	}
})();