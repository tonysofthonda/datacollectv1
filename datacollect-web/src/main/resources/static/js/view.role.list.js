(function(){
	$(function () {	
	    $('#roles-table').DataTable({
	        'paging': true,
	        'lengthChange': true,
	        'searching': true,
	        'ordering': true,
	        'info': true,
	        'autoWidth': true
	    });
	});
	
	  $('#modal-delete').on('show.bs.modal', function (event) {
	      var button = $(event.relatedTarget); // Button that triggered the modal
	      let recordId = button.data('record-id'); // Extract info from data-* attributes
	      let roleName = button.data('record-name')
	      var modal = $(this);
	      modal.find('#data-record-name').text(roleName);
	      modal.find('#action-delete').attr('href','role/delete/'+recordId);
	  });
})();