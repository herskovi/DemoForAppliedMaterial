
$(document).ready(function () 
		{

	
	//$("#contaNNctUsForm").submit(function () {

	


			$.ajax({
				type: 'GET',
				url: '/rest/rollback',
				contentType:"application/json; charset=utf-8",
				dataType: 'json',
				success: function (data) 
				{
					alert("Hi, Your new version of Geomtery Server sucks. Please rollback your application for the previous version. Next time it will happened, you will be layed off. This message will destroy itself in 5 seconds ");
					
				},
				error : function(jqXHR, setting, errorThrown){

//					alert('Error 2' + data);
					alert('Error in: ' + settings.url + ' - Error: ' +
							errorThrown + " - Response: " + jqXHR.responseText); 

				}

			});
	});




