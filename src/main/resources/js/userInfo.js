// localStorage.clear();

function UserInfo(storage){

	function localStorageExist(){
		return !(typeof(localStorage) === "undefined");
	}

	this.saveLevel = function(inputLevel){
		this.save('sudokuLevel', inputLevel)
	}

	this.getLevel = function(){
		var level = this.get('sudokuLevel')
		if(level != undefined) return level
		return 'normal';
	}

	this.getBestTimeInLevel = function(level){
		var bestTime = this.get('bestTime')
		if(bestTime == undefined) return new Duration(0)
		return new Duration(JSON.parse(bestTime)[level])
	}

	this.setBestTimeInLevel = function(level, duration){
		var bestTimeInString = this.get('bestTime')

		var bestTime = {'easy':0, 'normal':0, 'hard':0, 'evil':0}
		if(bestTimeInString != undefined) bestTime = JSON.parse(localStorage.bestTime)

		bestTime[level] = duration.getDurationInMs()
		bestTimeInString = JSON.stringify(bestTime)

		this.save('bestTime', bestTimeInString)
	}

	this.save = function(parameter, valueInString){
		if(storage == undefined) return
		storage[parameter] = valueInString
	}

	this.get = function(parameter){
		if(storage == undefined) return undefined
		return storage[parameter]
	}
}