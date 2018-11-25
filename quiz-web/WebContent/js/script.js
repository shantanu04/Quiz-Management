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
	
	//	Insertion of Data to the REST - Section - Start
	
	const createUrl = 'http://localhost:8080/quiz-restful/rest/questions';
	
	$("#questError, #choiceError1, #choiceError2, #choiceError3, #choiceError4, #choiceValidError1, #choiceValidError2, #choiceValidError3, #choiceValidError4").hide();
	
	$("#btnSave").click(function(){
		
		
		var txtQuestionLabel = $("#question-label").val();
		var txtChoiceLabel1 = $("#choice1").val();
		var txtChoiceLabel2 = $("#choice2").val();
		var txtChoiceLabel3 = $("#choice3").val();
		var txtChoiceLabel4 = $("#choice4").val();
		
		var choiceStatus1 = '';
		var choiceStatus2 = '';
		var choiceStatus3 = '';
		var choiceStatus4 = '';		
		var testJSON = '';
		
		console.log(txtQuestionLabel);
		
		if (txtQuestionLabel == '') {
			$("#questError").slideDown(2000, function() {
				$("#questError").fadeOut(2000);
			})
			return false;
		} 
	
		if (txtChoiceLabel1 == '') {
			$("#choiceError1").slideDown(2000, function() {
				$("#choiceError1").fadeOut(2000);
			})
			return false;
		} 
		
		if (txtChoiceLabel2 == '') {
			$("#choiceError2").slideDown(2000, function() {
				$("#choiceError2").fadeOut(2000);
			})
			return false;
		}
		
		if (txtChoiceLabel3 == '') {
			$("#choiceError3").slideDown(2000, function() {
				$("#choiceError3").fadeOut(2000);
			})
			return false;
		} 
		
		if (txtChoiceLabel4 == '') {
			$("#choiceError4").slideDown(2000, function() {
				$("#choiceError4").fadeOut(2000);
			})
			return false;
		}		
		

		choiceStatus1 = $("#choice1-opt option:selected").val();

		choiceStatus2 = $("#choice2-opt option:selected").val();
		
		choiceStatus3 = $("#choice3-opt option:selected").val();
		
		choiceStatus4 = $("#choice4-opt option:selected").val();

		
		
		var testJSON = '{"questionLabel" : "'+txtQuestionLabel+'",'+
		'"mcqChoices" : ['+
			'{"label" : "'+txtChoiceLabel1+'", "valid" : '+choiceStatus1+' },'+
			'{"label" : "'+txtChoiceLabel2+'", "valid" : '+choiceStatus2+' },'+
			'{"label" : "'+txtChoiceLabel3+'", "valid" : '+choiceStatus3+' },'+
			'{"label" : "'+txtChoiceLabel4+'", "valid" : '+choiceStatus4+' }'+
		']}';
		
		console.log(testJSON);
		
		$.ajax({
			url: createUrl,
			type: "POST",
			data: testJSON,
			dataType: "text",
			contentType: 'application/json',
	        success: function(result) {
	        	alert("Data Inserted Successfully");
	        	location.reload();
	        },	        
	        error: function(result) {
	        	console.log(testJSON);
	        	alert("Failed to Insert");
	        }			
		})	
		
	});
	
	//	Insertion of Data to the REST - Section - END

	
	

});