/**
 * 
 */
var urlprefix ="http://localhost:8080/clicservices/rest/brandadmin/";

$( document ).ready(function() {
    console.log( "document loaded" );
    $("#login").click(function(){

		var url = urlprefix + "login"
		var username = $("#name").val();
		var password = $("#password").val();
		$.ajax({
	        	 type: "POST",
	        	 url: url,	        	
	       		 data: JSON.stringify({"username" : ""+username+"","password" : ""+password+""}),
	        	 dataType: "JSON",	        	 
	        	 contentType: "application/json",	        
	        	 success: function(data)
	        	 {	    	
		        	if((data.responseCode)==200)
		        	{ 	
				
						 window.location.href ='tables.html';
		        	}
		        	else
		        	{
		        			alert(data.errorMessage);
							return;
		        	}	        	 
			     }	         
	        });
		});
    $("#insertrequestupdate").click(function(){
    	var url = urlprefix + "updateRequest";
    	var servicerequestId =$("#servicerequestId").val();
		var status = $("#status").val();
		var comment = $("#comment").val();
		
		console.log('id :'+servicerequestId);
		$.ajax({
       	 type: "POST",
       	 url: url,	        	
      		 data: JSON.stringify({"servicerequestId" : ""+servicerequestId+"","status" : ""+status+"","comment":""+comment+""}),
       	 dataType: "JSON",	        	 
       	 contentType: "application/json",	        
       	 success: function(data)
       	 {	    	
	        	if((data.responseCode)==200)
	        	{ 	
			
	        		location.reload();
	        	}
	        	else
	        	{
	        			alert(data.errorMessage);
						return;
	        	}	        	 
		     }	         
       });
	});
});
function editrequest(id)
{
	var modal = document.getElementById('myModal');
	modal.style.display = "block";
	$("#servicerequestId").val(id);
}

$("#close").click(function()
{
	document.getElementById('myModal').style.display = "none";
});



