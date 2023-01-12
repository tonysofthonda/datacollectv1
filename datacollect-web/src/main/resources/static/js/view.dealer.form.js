(function($, dealerId){
    var $cityInput = $("#dcCityId");
    var $stateInput = $("#stateId");
    var $facilityId = $("#facilityId");
    var $facilityQuantity = $("#quantity");
    var $facilityMessageContainer = $("#facilities-message-container");
    var $dealerFacilitiesTable;
    var lastSelectedFacilityId;
    
    $(init());
    
    function init(){
        initEvents();
        initCityValues();
        loadCities($stateInput.val(), $("#cityId").val());
        initFacilitiesForm();
    }

    function initEvents(){
        $('.select2').select2();//Initialize Select2 Elements 

        $('#stateId').on('change',function(){//input on form
            initCityValues();
            loadCities($stateInput.val());
        });  

        $.ajax({
            url: 'dealer/getDealerFacilities/' + dealerId,
            type:'GET',
            dataType: 'json',
            success: function(data){
               $dealerFacilitiesTable = $('#dealer-facilities-table').DataTable({
                    'paging': true,
                    'searching': true,
                    'ordering': true,
                    'info': true,
                    'autoWidth': true,
                    'data': data,
                    'columns': [
                        { data: 'facilityConcept', title:'Concept' },
                        { data: 'quantity', title:'Quantity' },
                        { title:'Actions' },
                    ],
                    'columnDefs':[
                    {
                        "targets": [ 2 ],
                        "render": function(data, type, row, meta){
                            var $rowContent = $('<div>')
                                .append(      
                                    $('<button>', {
                                        'type': 'button', 
                                        'class': 'btn btn-xs btn-primary col-xs-3 col-xs-offset-2 btn-edit-dealer-facility',
                                        'text': 'edit',
                                        'data-facility-id': row.facilityId,
                                        'data-concept': row.facilityConcept,
                                        'data-quantity': row.quantity,
                                    })
                                )
                                .append(
                                    $('<button>', {
                                        'type': 'button', 
                                        'data-toggle': 'modal', 
                                        'data-target': '#modal-delete-facility',
                                        'class': 'btn btn-xs btn-warning col-xs-3 col-xs-offset-1 btn-delete-dealer-facility',
                                        'text': 'delete',
                                        'data-facility-id': row.facilityId
                                    })

                                );  
                            return $rowContent.html();
                        } 
                    }]
                });
               $('#dealer-facilities-table').css('width', '100%');//fix width 0 on table, due bootstrap bug (see https://www.datatables.net/examples/api/tabs_and_scrolling.html)
            },error: function(){
                showErrorFacilityMsg('An error ocurred trying to get dealer facilities');
            }
        });
        
        $('#dealer-facilities-table').on('click', '.btn-edit-dealer-facility', function(){
            editFacilityAction($(this));
        });
        
        $('#dealer-facilities-table').on('click', '.btn-delete-dealer-facility', function(){
            lastSelectedFacilityId = $(this).attr('data-facility-id');
        });
        
        $("#btn-accept-delete-facility").click(function(e){
            deleteFacilityAction(); 
            return false;
        });

    }      

    function initCityValues(){
        $cityInput.empty();
        $cityInput.append(
            $('<option></option>').val("").html("Select an option")
        );
    }

    function loadCities(stateId, lastSelectedCityId){
        if(stateId === '-1'){
            return;
        }  
        $.ajax({
            type: "GET",
            url: "dealer/getCitiesByState/" + stateId, 
            contentType: 'application/json',
            success: function(cityList){
                $.each(cityList, function (index, city) {
                    $cityInput.append(
                        $('<option></option>').val(city.id).html(city.name)
                     );
                });          
                if(lastSelectedCityId != undefined && lastSelectedCityId != '-1' && lastSelectedCityId != ''){
                    $cityInput.children('option[value="' + lastSelectedCityId + '"]').prop("selected", true);
                }
            },error: function(){
                alert("An error ocurred trying to get city list, please try again");
            } 
        });
    }
    
    function initFacilitiesForm(){
        $('#dealer-facilities-form').validate({
            rules: {
                facilityId: {
                    required: true
                },
                quantity: {
                    required: true,
                    min: 1,
                    max: 999999999
                }
            },
            messages: {
                facilityId: {
                    required: 'Facility concept is required'
                },
                quantity: {
                    required: 'Quantity is required',
                    min: 'Quantity must be a number grater than 0',
                    max: 'Quantity must be a number lower than 1,000,000,000'
                }
            }, 
            errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            },
            submitHandler: function(form, event){
                event.preventDefault();
                $('#dealer-facilities-form').find('span.error.help-block').remove();
                var postData = {
                    facilityId: $facilityId.val(),
                    dealerId: dealerId,
                    quantity: $facilityQuantity.val()                
                };
                $.ajax({
                    url: 'dealer/saveFacility',
                    type:'POST',
                    data: postData,
                    success: function(data){
                        showSuccessFacilityMsg('Record saved successfully');
                        updateItemToFacilitiesTable(postData.facilityId, $facilityId.children("option:selected").text(), postData.quantity)
                        resetForm(); 
                    },error: function(){
                        showErrorFacilityMsg('An error ocurred trying to process data');
                    }
                });
            }
        });    
    }
    
    function resetForm(){
        $('#dealer-facilities-form')[0].reset();
    }

    function deleteFacilityAction(){
        $.ajax({
            url: 'dealer/deleteFacility',
            type: 'POST',
            data: {
                'facilityId': lastSelectedFacilityId,
                'dealerId': dealerId
            },
            success: function(data){
                showSuccessFacilityMsg('Record deleted successfully');
                var $row = $("#dealer-facilities-table").find('.btn-edit-dealer-facility[data-facility-id="' + lastSelectedFacilityId + '"]').parents('tr');
                $dealerFacilitiesTable.row($row).remove().draw();
                resetForm();
                $('#modal-delete-facility').modal('hide');
            },error: function(){
                showErrorFacilityMsg('An error ocurred trying to process data');
            }
        });
    }

    function editFacilityAction($btn){
        $facilityId.find('option[value="' + $btn.attr('data-facility-id') + '"]').prop('selected', true);
        $facilityQuantity.val($btn.attr('data-quantity'));        
    }
    
    function updateItemToFacilitiesTable(facilityId, facilityConcept, facilityQuantity){
        var $existingBtn = $("#dealer-facilities-table").find('.btn-edit-dealer-facility[data-facility-id="' + facilityId + '"]');
        var rowData = {
            'facilityConcept': facilityConcept,
            'quantity': facilityQuantity,
            'facilityId': facilityId,
            'dealerId': dealerId
        };

        if($existingBtn.length > 0 ){
            var $row = $existingBtn.parents('tr');
            $dealerFacilitiesTable.row($row).data(rowData).draw();
        }else{
            $dealerFacilitiesTable.row.add(rowData).draw();
        }
    }
    
    function showSuccessFacilityMsg(message){
        showFacilityMessage(message, 'success');
    }
    
    function showErrorFacilityMsg(message){
        showFacilityMessage(message, 'error');
    }
    
    function showWarnFacilityMsg(message){
        showFacilityMessage(message, 'warning');
    }
    
    function showFacilityMessage(message, messageType){
        $facilityMessageContainer.html('');
        $facilityMessageContainer.append(
            $('<div>', {
                'class': 'alert alert-' + messageType + ' alert-dismissible'
                }
            )
            .text(message)
            .append(
                $('<button>',{'type':'button', 'class': 'close'})
                    .attr('data-dismiss', 'alert')
                    .attr('aria-label', 'Close')
                    .append(
                        $('<span>').attr('aria-hidden', 'true').html('&times;')
                    )
            )
        );
    }
    
})(jQuery, onLoadDealerId);