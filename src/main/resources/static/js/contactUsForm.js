
$(document).ready(function () 
		{

	
	//$("#contactUsForm").submit(function () {
	$("#submitButtonDiv").click(function () {
	
		//var represent = $('#represent').val();  
		var email = $('#email').val();  
		var fullName = $("#fullName").val();  
		var telephone = $('#telephone').val();  
		var company = $('#company').val();  
		var comments = $('#comments').val();
		var dataString =  JSON.stringify({  "email" : email,"fullName": fullName, 
			"telephone" : telephone, "company" : company,"comments" : comments })	

			$.ajax({
				type: 'POST',
				url: '/rest/candidate/create',
				contentType:"application/json; charset=utf-8",
				dataType: 'json',
				data: dataString,  
				success: function (data) 
				{
					alert("Thank you for contacting us. Our rep will contact you in 72 hours ");
					
				},
				error : function(jqXHR, setting, errorThrown){

//					alert('Error 2' + data);
					alert('Error in: ' + settings.url + ' - Error: ' +
							errorThrown + " - Response: " + jqXHR.responseText); 

				}

			});
	});

$("#findall").click(function () {
	
	//var represent = $('#represent').val();  
		

		$.ajax({
			type: 'GET',
			url: '/rest/candidates',
			contentType:"application/json; charset=utf-8",
			dataType: 'json',
			success: function (data) 
			{
				alert("List " + data.id + "  " + data.fullname);
				
			},
			error : function(jqXHR, setting, errorThrown){

//				alert('Error 2' + data);
				alert('Error in: ' + settings.url + ' - Error: ' +
						errorThrown + " - Response: " + jqXHR.responseText); 

			}

		});
});
	});



$('#ajaxError').ajaxError(function(e, xhr, settings, exception) {
	$(this).text('Error in: ' + settings.url + ' - Error: ' +
			exception + " - Response: " + xhr.responseText);
});





