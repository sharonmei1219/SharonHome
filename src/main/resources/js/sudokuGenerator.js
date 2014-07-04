function updateProgress(){
	$.ajax({
		type : "GET",
		url : "generationProgress",
		async: true,
		success : function(response){
			if(response.inProgress == false) {
				clearInterval(progressUpdatePeriod);
			}

				renderProgressBar('#total-progress', response.percentageTotal, response.total);
				renderProgressBar('#easy-progress', response.percentageEasy, response.easy);
				renderProgressBar('#normal-progress', response.percentageNormal, response.normal);
				renderProgressBar('#hard-progress', response.percentageHard, response.hard);
				renderProgressBar('#evil-progress', response.percentageEvil, response.evil);
				renderPuzzleNumber('#info-overall', response.storedAll);
				renderPuzzleNumber('#info-easy', response.storedEasy);
				renderPuzzleNumber('#info-normal', response.storedNormal);
				renderPuzzleNumber('#info-hard', response.storedHard);
				renderPuzzleNumber('#info-evil', response.storedEvil);
				renderWarning(response.warning);
		}
	});
}

function renderPuzzleNumber(category, number){
	$(category).text(number)
}

function renderWarning(warning){
	$('#info-warnings').text(warning);
}

function renderProgressBar(category, percentage, number){
	$(category).css('width', percentage+'%');
	$(category).html(number);
}



function startGeneration(){
	$.ajax({
		type : "POST",
		url : "startGeneration",
		data : JSON.stringify({numberOfPuzzleToGenerate:$('#input-numberOfPuzzles').val(), numberOfHolesInPuzzle:$('#input-numberOfHoles').val()}),
		contentType: 'application/json',
		async: true,
		success : function(response){
			// alert('done');
		}
	});
	progressUpdatePeriod = setInterval(updateProgress, 50);
}
$(function(){
	$('#start-button').click(startGeneration);
})