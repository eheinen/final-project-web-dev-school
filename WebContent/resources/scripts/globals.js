$('document').ready(function() {

	manageMenuShow();
	checkFailedMessage();

	$(document).ready(function() {
		$('table').DataTable({
			"lengthMenu" : [ [ 5, 10, 15, 20, -1 ], [ 5, 10, 15, 20, "All" ] ],
			"bLengthChange" : false
		});
	});



	var schoolMinAcerage = $("#form-school\\:schoolMinAverage").val();
	if(schoolMinAcerage == 0.0){
		$("#form-school\\:schoolMinAverage").val("");
	}

	var schoolMinAcerage = $("#form-teacher\\:salaryPerHour").val();
	if(schoolMinAcerage == 0.0){
		$("#form-teacher\\:salaryPerHour").val("");
	}

	var schoolMinAverage = $("#form-teacher\\:weeklyHour").val();
	if(schoolMinAverage == 0.0){
		$("#form-teacher\\:weeklyHour").val("");
	}

	var roomNumber = $("#form-classroom\\:roomNumber").val();
	if(roomNumber == 0){
		$("#form-classroom\\:roomNumber").val("");
	}

	var salaryPerHour = $("#form-employee\\:salaryPerHour").val();
	if(salaryPerHour == 0){
		$("#form-employee\\:salaryPerHour").val("");
	}

	var weeklyHour = $("#form-employee\\:weeklyHour").val();
	if(weeklyHour == 0){
		$("#form-employee\\:weeklyHour").val("");
	}
	
	var examPointsPercent = $("#form-exam\\:examPointsPercent").val();
	if(examPointsPercent == 0.0){
		$("#form-exam\\:examPointsPercent").val("");
	}
	
});

function checkFailedMessage() {
	if ($('#message').text().trim() == "") {
		//includeClassNotFilled(false, false);
		$('#message').hide();
		return false;
	} else {
		//includeClassNotFilled(true, true);
		$("#message").slideDown("slow", function() {
		}).delay(3000).slideUp("slow", function() {
			$('#message').text("")
			//includeClassNotFilled(false, false);
		});
		return false;
	}
}

function includeClassNotFilled(hasInclude, focusFirst){
	var requiredFields = $('.required');
	if(hasInclude){
		for(var i=0; i<requiredFields.length; i++){
			$(requiredFields[i]).addClass("not-filled");
		}
	} else {
		for(var i=0; i<requiredFields.length; i++){
			$(requiredFields[i]).removeClass("not-filled");
		}
	}
	if(focusFirst){
		$('.not-filled').first().focus();
	}
}

function confirmDelete(){
	if (!confirm('Are you sure?')) return false;
}

function manageMenuShow(){
	var username = $('#usernameSession').val();
	var profile = $('#profileSession').val();

	var mn_home = $("#form-nav\\:mn-home");
	var mn_administrative = $("#mn-administrative");
	var mn_school = $("#mn-school");
	var mn_teacher_area = $("#mn-teacher-area");
	var mn_student_area = $("#mn-student-area");
	var mn_logout = $("#form-nav\\:mn-logout");

	mn_home.hide();
	mn_logout.hide();
	mn_administrative.hide();
	mn_school.hide();
	mn_teacher_area.hide();
	mn_student_area.hide();

	if(username != ""){
		mn_home.show();
		mn_logout.show();

		if(profile == "ADMIN"){
			mn_administrative.show();
		}
		else if(profile == "SCHOOL"){
			mn_school.show();
		}
		else if(profile == "TEACHER"){
			mn_teacher_area.show();
		}
		else if(profile == "STUDENT"){
			mn_student_area.show();
		}
		else if(profile == "MASTER"){
			mn_administrative.show();
			mn_school.show();
			mn_teacher_area.show();
			mn_student_area.show();
		}
	}
}
