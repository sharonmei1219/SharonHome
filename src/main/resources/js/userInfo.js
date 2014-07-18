// localStorage.clear();

function UserInfo(){

	function localStorageExist(){
		return !(typeof(localStorage) === "undefined");
	}

	this.getBestTime = function(){
		if(!localStorageExist()) return {easy:0, normal:0, hard:0, evil:0};
		if(typeof(localStorage.bestTime) !== 'undefined')
			return JSON.parse(localStorage.bestTime);
		return {easy:0, normal:0, hard:0, evil:0};
	}

	this.setBestTime = function(bestTime){
		if(!localStorageExist()) return;
		localStorage.bestTime = JSON.stringify(bestTime);
	}

	this.saveLevel = function(inputLevel){
		if(!localStorageExist()) return;
   		localStorage.sudokuLevel = inputLevel;
	}

	this.getLevel = function(){
		if(!localStorageExist()) return undefined;
		if(typeof(localStorage.sudokuLevel) !== 'undefined')
			return localStorage.sudokuLevel;
		return undefined;
	}
}

var userInfo = new UserInfo();