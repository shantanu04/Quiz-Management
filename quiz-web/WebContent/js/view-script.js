// View All - Script

$(document).ready(function(){

		
		const viewAllUrl = 'http://localhost:8080/quiz-restful/rest/questions/findAll';	
		
		$.ajax({
			url: viewAllUrl,
			type: 'GET',
			success: function(result) {
				
				console.log(result);
				
//		        var tTag = "<table class='table table-bordered table-striped'><thead><tr><th>Id</th><th>Question Label</th></thead><tbody>";
//				for(var i in result) {
//					tTag += '<tr><td>'+result[i].id+'</td><td>'+result[i].questionLabel+'</td></tr>';
//				}
//				tTag += "</tbody></table>";
				
				var vTag="";
				for(var i in result) {
					var count = i;
					vTag +="<div class='question-box'>";
					vTag += "<div class='questionLabel'><span>"+ (++count)+") </span><span class='qtext'>"+result[i].questionLabel+"</span></div>";
					for(var cL in result[i].mcqChoices) {
						var countCaptionNum = cL;
						vTag += "<div class='choiceLabel'><span class='choice-caption'>Choice "+(++countCaptionNum)+":</span> "+result[i].mcqChoices[cL].label+"</div>"
					}
					vTag += "</div>";
				}
				
				
				console.log(vTag);

				$("#viewall-display").html(vTag);
				
			},
			error: function(error) {
				console.log(`Error: ${error}`)
			}
		});		
		
});