// Delete - Script

$(document).ready(function(){
	
	// Delete the Question ID - Start
	
	$("#deleteError").hide();
	
	$("#btnDelete").click(function(){
		
		var txtID = $("#qid-label").val();
		
		console.log(txtID);
		
		if (txtID == '') {
			$("#deleteError").slideDown(2000, function() {
				$("#deleteError").fadeOut(2000);
			})
			return false;
		}
		
		const deleteUrl = 'http://localhost:8080/quiz-restful/rest/questions/deleteMcq';
			
		var deleteData = '{"id":'+txtID+'}';
		
		$.ajax({
			url: deleteUrl,
			type: "DELETE",
			data: deleteData,
			dataType: "text",
			contentType: 'application/json',
	        success: function(result) {
	        	alert("Question Deleted Successfully");
	        	$("#qid-label").val("");
	        	location.reload();
	        },	        
	        error: function(result) {
	        	alert("Unable to delete the Question");
	        	$("#qid-label").val("");
	        }			
		});
		
		// Delete the Question ID - End
		
	});
	
	// Display the list of Questions - Section - Start
	
	const Url = 'http://localhost:8080/quiz-restful/rest/questions/findAll';
	
	var tTag='';
	$.ajax({
		url: Url,
		type: 'GET',
		success: function(result) {
			
			//console.log(result);
			
	        var tTag = "<table class='table table-bordered table-striped'><thead><tr><th>Id</th><th>Question Label</th></thead><tbody>";
			for(var i in result) {
				tTag += '<tr><td>'+result[i].id+'</td><td>'+result[i].questionLabel+'</td></tr>';
			}
			tTag += "</tbody></table>";
			
			//console.log(tTag);
			
			$("#questions-display").html(tTag);
			
		},
		error: function(error) {
			console.log(`Error: ${error}`)
		}
	});
	
	// Display the list of Questions - Section - End		
	
});