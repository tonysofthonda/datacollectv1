(function($) {
	let $ldapGroupsTable; 
	let $ldapId;
	let $ldapDescription;
	let $ldapGroupForm;
	let $roleName;
	let $roleDescription;
	let idRow;
	let $treeview;	
	
	$(init());

	function init() {		
		$ldapGroupForm = $('#ldap-groups-form');
		$roleName = $('#name');
		$roleDescription = $('#description');
		$ldapId = $('#ldapId');
		$ldapDescription = $('#ldapDescription');
		$treeview = $('#role-permissions-treeview');
		
		loadViewList();
		
		initEvents();
		initDataTable();
		initGroupsForm();
		initRoleForm();			
	}
	
	function loadViewList(){		
		let viewList;
		let allowedViews;
		
		let allowedViewsCall = {};
		if(isEdit){
			allowedViewsCall = $.get('view/getViewsAllowedByRole/' + roleId, function(response){
				allowedViews = response;
			});
		}		
		
		$.when(
			$.get('view/getAll', function(response){
				viewList = response;
			}),
			allowedViewsCall				
		).then(function(){
			let treeviewData = buildPermissionsTreeview(viewList, allowedViews);
			initTreeview(treeviewData);
		}).fail(function(response) {
		    alert('An error ocurred trying to get view list');
		});
	}
	
	function buildPermissionsTreeview(viewList, allowedViews){
		return viewList.map(function(view){					
			let matchFound = false;
			let found;
			
			if(allowedViews != undefined && allowedViews instanceof Array){	
				foundView = allowedViews.filter(function(item){
					return item.id === view.id;
				});					
				matchFound = foundView instanceof Array && foundView.length > 0;
			}
			
			return { 
				text: 	view.friendlyName,
				state: {
					checked: matchFound,
					expanded: matchFound
				},
				nodes: 	view.actions.map(function(viewAction){	
					let node = {
						id:  viewAction.id,
						text: viewAction.friendlyName
					};
					if(matchFound){
						let isAllowed = foundView.filter(function(allowedView){ 
								return allowedView.actions.filter(function(allowedViewAction){
											return viewAction.id === allowedViewAction.id;
										}).length > 0;
						}).length > 0;
						node.state = {
							checked: isAllowed
						};					
					}
					return node;
				})
			};				
		});
	}
	
	function initTreeview(treeviewData){
		$treeview.treeview({
			data: treeviewData,
			showCheckbox: true,
			highlightSelected: false,
			levels: 0,	/* Prevent parents to be expanded */
			onNodeChecked:  function(event, data) {
				if(data.nodes !== undefined){
					//parent node
					data.nodes.forEach(function(node) {
						$treeview.treeview('checkNode', [ node.nodeId, { silent: true } ]);
					});
					$treeview.treeview('expandNode', [ data.nodeId, { silent: true } ]);
				}else{
					//child node
					var parentNode = $treeview.treeview('getParent', data.nodeId);
					$treeview.treeview('checkNode', [ parentNode, { silent: true } ]);
					parentNode.nodes.forEach(function(node) {
						if(node.text != null && node.text.toLowerCase() === 'view'){
							$treeview.treeview('checkNode', [ node.nodeId, { silent: true } ]);
						}					
					});
				}
			},
			onNodeUnchecked: function(event, data) {
				if(data.nodes !== undefined){
					//parent node
					data.nodes.forEach(function(node) {
						$treeview.treeview('uncheckNode', [ node.nodeId, { silent: true } ]);
					});
				}else{
					//child node
					if(data.text.toLowerCase() === 'view'){
						//when uncheck view, all childs and parent are unchecked
						$treeview.treeview('uncheckNode', [ data.parentId ]);
						return;
					}
					var parentNode = $treeview.treeview('getParent', data.nodeId);
					var hasChildSelected = false;
					parentNode.nodes.forEach(function(node) {
						if(node.state.checked){
							hasChildSelected = true;
						}
					});
					if(!hasChildSelected){
						$treeview.treeview('uncheckNode', [ parentNode.nodeId, { silent: true } ]);
					}
				}
			}
		});
	}
	
	function initEvents(){
		$('#ldap-groups-table tbody').on('click', '.btn-edit-ldap-group', function(data){
			var $btn = $(this);
			$ldapId.val($btn.attr('data-id'));
			$ldapDescription.val($btn.attr('data-description'));
			idRow= $btn.attr('data-row-id');
	    });
	        
		 $('#ldap-groups-table tbody').on('click', '.btn-delete-ldap-group', function(){
			var d = $ldapGroupsTable.row(this).data();
			$ldapGroupsTable
		    .row( d )
		    .remove()
		    .draw();
        });		
		
		$('#btn-save-ldap-group').click(function() {
			$('#ldap-groups-form').submit();
		});
		
		$('#btn-save-role').click(function() {
			$('#role-form').submit();			
		});
	}
	
	function initDataTable(){
		//TODO: refactor map table rows as object instead of array
		$ldapGroupsTable = $('#ldap-groups-table').DataTable({
			'paging' : true,
			'lengthChange' : true,
			'searching' : true,
			'ordering' : true,
			'info' : true,
			'autoWidth' : true,
			'columnDefs':[
                 {
                     "targets": [2],
                     "render": function(data, type, row, meta){
                         var $rowContent = $('<div>')
                             .append(      
                                 $('<button>', {
                                     'type': 'button', 
                                     'class': 'btn btn-xs btn-primary col-xs-3 col-xs-offset-2 btn-edit-ldap-group',
                                     'text': 'edit',
                                     'data-row-id': meta.row,
                                     'data-id': row[0],
                                     'data-description' : row[1]
                                 })
                             ).append(
                                 $('<button>', {
                                     'type': 'button', 
                                     'class': 'btn btn-xs btn-warning col-xs-3 col-xs-offset-1 btn-delete-ldap-group',
                                     'text': 'delete '
                                 })
                             );  
                         return $rowContent.html();
                     } 
                 }]
		});
	}

	function initGroupsForm() {
		$('#ldap-groups-form').validate({
			rules : {
				ldapId : {
					required : true
				},
				ldapDescription : {
					required : true
				}
			},
			errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            },
			messages : {
				ldapId : {
					required : 'LDAP Id is required'
				},
				ldapDescription : {
					required : 'LDAP Description is required'
				}
			},
			submitHandler: function(form, event){
				event.preventDefault();
				if(idRow !== undefined){
					updateLdapGroupsTable();
				}else{
					createLdapGroupsTable();
				}
				resetForm();
			}
		});
	}
	
	function initRoleForm(){
		$('#role-form').validate({
			rules : {
				name : {
					required : true
				},
				description : {
					required : true
				}
			},
			errorElement: "span",
            errorPlacement: function(error, element) {
            	appendErrorMsgBasedOnElementName($(element).attr('name'), $(error));
            },
			messages : {
				name : {
					required : 'Role Name is required'
				},
				description : {
					required : 'Role Description is required'
				}
			},
			submitHandler: function(form, event){
				event.preventDefault();
				saveRole();
			}
		});
	}

	function updateLdapGroupsTable() {
		let rowData = [ $ldapId.val(), $ldapDescription.val(), null ];
		let $row = $('#ldap-groups-table').find(".btn-edit-ldap-group[data-row-id='" + idRow + "']").parents('tr');
		$ldapGroupsTable.row($row).data(rowData).draw();
		idRow = undefined;
	}
	
	function createLdapGroupsTable() {
		let rowData = [ $ldapId.val(), $ldapDescription.val(), null ];
		$ldapGroupsTable.row.add(rowData).draw();
	}
	
    function saveRole(){    	
    	let roleData = {
			name: $roleName.val(),
			description: $roleDescription.val(),
			ldapGroupList: getGroupList(),
			permissionList: getPermissionList()
    	};    	
    	let $id = $('#id');
    	if($id !== undefined){
    		roleData.id = $id.val()
    	}
    	
    	$.ajax({
	         url: 'role/add',
	         type: 'POST',
	         contentType: "application/json",
	         data: JSON.stringify(roleData),
	         success: function(data){
	             window.location.href = "role/saved";
	         },error: function(response){
	        	 if(response.responseJSON instanceof Array){
	        		 response.responseJSON.map(function(item){
		        		 showError(item);
		        	 }); 
	        	 }else{
	        		 showError('An unknown exception ocurred while trying to execute the operation');
	        	 }
	        	         	
	         }
        });
    }
    
    function getGroupList(){
    	let jsonObj = [];
    	$ldapGroupsTable.data().each(function ( value, index ) {
    		jsonObj[index] = {
    			 ldapId: value[0],
				 name: value[1]
	    	 };
        });
    	return jsonObj;
    }
    
    function getPermissionList(){
    	return $treeview.treeview('getChecked').filter(function(node){
    		//filter parents
    		return node.nodes == null;
    	}).map(function(node){
    		return {
    			id: node.id
    		}
    	});
    }
    
    function resetForm(){
    	$ldapGroupForm[0].reset();
    }
    
    function showError(errorMsg){    	 
    	 $('#messages').append(
			 $('<div/>', {
	    		 'class': 'alert alert-error alert-dismissible'
	    	}).append(
				 $('<button/>',{
					 'type': 'button', 
					 'class': 'close',
					 'data-dismiss': 'alert',
					 'aria-hidden': 'true'
				 }).text('x')
			).append(
				$('<h4/>').append($('<i/>', { 'class': 'icon fa fa-warning'})).append("Error")
			).append(errorMsg)
    	 );
    }
	
})(jQuery);