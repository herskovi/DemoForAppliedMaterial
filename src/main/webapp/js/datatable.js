
$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/rest/candidates",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
			      { "mData": "fullName" },
				  { "mData": "email" },
				  { "mData": "telephone" },
				  { "mData": "comments" }
			]
	 })
});


