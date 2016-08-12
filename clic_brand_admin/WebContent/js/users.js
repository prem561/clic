/**
 * 
 */
var urlprefix ="http://localhost:8080/clicservices/rest/brandadmin/";

$( document ).ready(function() 
{
//	$("#retailer-group").hide();
	$.get( urlprefix+"usertypeslist/1", function( data ) {
			$.each( data, function( key, value ) {
			$("#usertypeid").append("<option value="+value.typeId+">"+value.userType+"</option>");
			});
		
	});
	$.get( urlprefix+"retailerstoreslist/1", function( data ) {
		$.each( data, function( key, value ) {
			console.log(JSON.stringify(value));
		$("#retailer_list").append("<option value="+value.id+">"+value.storeName+"</option>");
		});
	
	});
	$("#createuser").click(function()
	{
		var usertype=$("#usertypeid").val();
		var retailerstore=$("#retailer_list").val();
		var username = $("#name").val();
		var password = $("#pswd").val();
		var brandId='1';
		var url =  urlprefix +'addBrandUser';
//		"+retailerstore+"
		$.ajax({
       	 type: "POST",
       	 url: url,	        	
      		 data: JSON.stringify({"username" : ""+username+"","password" : ""+password+"","usertype" : "1","retailerstore" : "1","brandId" : ""+brandId+""}),
       	 dataType: "JSON",	        	 
       	 contentType: "application/json",	        
       	 success: function(data)
       	 {	
       		 	alert(JSON.stringify(data));
	        	if(data.responseCode=="200")
	        	{ 	
					alert(data.status);
					return;
	        	}
	        	else
	        	{
	        			alert(data.errorMessage);
						return;
	        	}	        	 
		   },
		   error:function(err)
		   {
			   alert(JSON.stringify(err));
		   }
       });
	});
});


