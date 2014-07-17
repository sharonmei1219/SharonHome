localStorage.clear();

function UserInfo(){
	if(typeof(localStorage) === "undefined")
		var localStorage = {};

	this.getBestTime = function(){
		if(typeof(localStorage.bestTime) !== 'undefined')
			return JSON.parse(localStorage.bestTime);
		return {easy:0, normal:0, hard:0, evil:0};
	}

	this.setBestTime = function(bestTime){
		localStorage.bestTime = JSON.stringify(bestTime);
	}

	this.saveLevel = function(inputLevel){
   		localStorage.sudokuLevel = inputLevel;
	}

	this.getLevel = function(){
		if(typeof(localStorage.sudokuLevel) !== 'undefined')
			return localStorage.sudokuLevel;
		return undefined;
	}
}

var userInfo = new UserInfo();