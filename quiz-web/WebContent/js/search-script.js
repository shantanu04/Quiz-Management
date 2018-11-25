// Search - Script

$(document).ready(function(){
	
	// Search URL
	
	$("#searchError").hide();
	
	$("#btnSearch").click(function(){
		
		var txtSearchQuestionLabel = $("#search-question-label").val();
		
		console.log(txtSearchQuestionLabel);
		
		if (txtSearchQuestionLabel == '') {
			$("#searchError").slideDown(2000, function() {
				$("#searchError").fadeOut(2000);
			})
			return false;
		}
		
		const searchUrl = 'http://localhost:8080/quiz-restful/rest/questions?s='+txtSearchQuestionLabel;
		
		var tTag='';
		$.ajax({
			url: searchUrl,
			type: 'GET',			
			success: function(result) {
				
				//console.log(result);
				tTag = "<div class='qdc-title'>Search Results</div>";
		        tTag += "<table class='table table-bordered table-striped'><thead><tr><th>Id</th><th>Question Label</th></thead><tbody>";
				for(var i in result) {
					tTag += '<tr><td>'+result[i].id+'</td><td>'+result[i].questionLabel+'</td></tr>';
				}
				tTag += "</tbody></table>";
				
				//console.log(tTag);
				
				$("#questions-display").html(tTag);
				
			},
			error: function(error) {
				alert("Search Not Found");
				console.log(`Error: ${error}`)
			}
		});		
		
	});
	
});