$(document).ready(function(){
	
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
	
	//	Updation of Data to the REST - Section - Start
	
	const updateUrl = 'http://localhost:8080/quiz-restful/rest/questions/updateMcq';
	
	$("#uiderror, #uquestError, #uchoiceError1, #uchoiceError2, #uchoiceError3, #uchoiceError4, #uchoiceValidError1, #uchoiceValidError2, #uchoiceValidError3, #uchoiceValidError4").hide();
	
	$("#btnUpdate").click(function(){
		
		var uqidLabel = $("#uqid").val();
		var utxtQuestionLabel = $("#uquestion-label").val();
		var utxtChoiceLabel1 = $("#uchoice1").val();
		var utxtChoiceLabel2 = $("#uchoice2").val();
		var utxtChoiceLabel3 = $("#uchoice3").val();
		var utxtChoiceLabel4 = $("#uchoice4").val();
		
		var ucid1 = $("#cid1").val();
		var ucid2 = $("#cid2").val();
		var ucid3 = $("#cid3").val();
		var ucid4 = $("#cid4").val();
		
		var uchoiceStatus1 = '';
		var uchoiceStatus2 = '';
		var uchoiceStatus3 = '';
		var uchoiceStatus4 = '';
		
		var testJSON = '';
		
		if (uqidLabel == '') {
			$("#uiderror").slideDown(2000, function() {
				$("#uiderror").fadeOut(2000);
			})
			return false;
		}		
		
		if (utxtQuestionLabel == '') {
			$("#uquestError").slideDown(2000, function() {
				$("#uquestError").fadeOut(2000);
			})
			return false;
		} 
	
		if (utxtChoiceLabel1 == '') {
			$("#uchoiceError1").slideDown(2000, function() {
				$("#uchoiceError1").fadeOut(2000);
			})
			return false;
		} 
		
		if (utxtChoiceLabel2 == '') {
			$("#uchoiceError2").slideDown(2000, function() {
				$("#uchoiceError2").fadeOut(2000);
			})
			return false;
		}
		
		if (utxtChoiceLabel3 == '') {
			$("#uchoiceError3").slideDown(2000, function() {
				$("#uchoiceError3").fadeOut(2000);
			})
			return false;
		}		

		if (utxtChoiceLabel4 == '') {
			$("#uchoiceError4").slideDown(2000, function() {
				$("#uchoiceError4").fadeOut(2000);
			})
			return false;
		}

		uchoiceStatus1 = $("#uchoice1-opt option:selected").val();

		uchoiceStatus2 = $("#uchoice2-opt option:selected").val();
		
		uchoiceStatus3 = $("#uchoice3-opt option:selected").val();

		uchoiceStatus4 = $("#uchoice4-opt option:selected").val();		

		
		
		var testJSON = '{'+
		'"id" : "'+uqidLabel+'",'+
		'"questionLabel" : "'+utxtQuestionLabel+'",'+
		'"mcqChoices" : ['+
			'{"id":"'+ucid1+'", "label" : "'+utxtChoiceLabel1+'", "valid" : '+uchoiceStatus1+' },'+
			'{"id":"'+ucid2+'", "label" : "'+utxtChoiceLabel2+'", "valid" : '+uchoiceStatus2+' },'+
			'{"id":"'+ucid3+'", "label" : "'+utxtChoiceLabel3+'", "valid" : '+uchoiceStatus3+' },'+
			'{"id":"'+ucid4+'", "label" : "'+utxtChoiceLabel4+'", "valid" : '+uchoiceStatus4+' }'+
		']}';
		
		console.log(testJSON);

		
		$.ajax({
			url: updateUrl,
			type: "PUT",
			data: testJSON,
			dataType: "text",
			contentType: 'application/json',
	        success: function(result) {
	        	alert("Question and Choices - Updated Successfully");
	        	location.reload();
	        },	        
	        error: function(result) {
	        	alert("Update Failed");
	        }			
		});
		
	});
	
	//	Updation of Data to the REST - Section - END


});