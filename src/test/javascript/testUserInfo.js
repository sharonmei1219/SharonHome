describe('localStorage', function(){
	it('gets correct parameter if it is exist in localStorage', function(){
		var userInfo = new UserInfo(localStorage)
		userInfo.save('name', 'sharon')
		expect(userInfo.get('name')).toBe('sharon')
	})

	it('gets undefined if the parameter does not exist in localStorage', function(){
		var userInfo = new UserInfo(localStorage)
		expect(userInfo.get('neverExistParameter')).toBe(undefined)
	})

	it('gets undefined if localStorage does not exit', function(){
		var userInfo = new UserInfo(undefined)
		userInfo.save('name', 'sharon')
		expect(userInfo.get('name')).toBe(undefined)
	})
})

describe('UserInfo', function(){
	it('get Duration NullObject if bestTime is not saved', function(){
		var userInfo = new UserInfo(localStorage)
		localStorage.clear()
		expect(userInfo.getBestTimeInLevel('easy').toString()).toEqual('--:--:--')
	})

	it('saves easy level bestTime when whole bestTime field does not exist', function(){
		var userInfo = new UserInfo(localStorage)
		localStorage.clear()
		userInfo.setBestTimeInLevel('easy', new Duration(1000))
		expect(userInfo.getBestTimeInLevel('easy').toString()).toBe('00:00:01')
	})

	it('saves easy level bestTime without impact to other level bestTime', function(){
		var userInfo = new UserInfo(localStorage)
		localStorage.clear()
		userInfo.setBestTimeInLevel('hard', new Duration(61000))
		userInfo.setBestTimeInLevel('easy', new Duration(1000))
		expect(userInfo.getBestTimeInLevel('easy').toString()).toBe('00:00:01')
		expect(userInfo.getBestTimeInLevel('hard').toString()).toBe('00:01:01')
	})

	it('get default level which is normal when there is no level stored', function(){
		var userInfo = new UserInfo(localStorage)
		localStorage.clear()
		expect(userInfo.getLevel()).toBe('normal')
	})
})