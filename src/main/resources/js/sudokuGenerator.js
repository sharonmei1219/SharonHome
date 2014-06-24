function updateProgress(){
	$.ajax({
		type : "GET",
		url : "generationProgress",
		async: true,
		success : function(response){
			if(response.inProgress == false) {
				clearInterval(progressUpdatePeriod);
			}else{
				renderProgressBar('#total-progress', response.percentageTotal, response.total);
				renderProgressBar('#easy-progress', response.percentageEasy, response.easy);
				renderProgressBar('#normal-progress', response.percentageNormal, response.normal);
				renderProgressBar('#hard-progress', response.percentageHard, response.hard);
				renderProgressBar('#evil-progress', response.percentageEvil, response.evil);
			}
		}
	});
}

function renderProgressBar(category, percentage, number){
	$(category).css('width', percentage+'%');
	$(category).html(number);
}

var progressUpdatePeriod = setInterval(updateProgress, 50);

$(updateProgress);