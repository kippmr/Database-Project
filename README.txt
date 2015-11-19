TODO: (line# is where it is approx located in UserInterface.java)
	UserInterface.java
		line19: initialize User object then assigning it to value later
				this should work?

		line192:checkSNo(String) return boolean, given sNo
				check all serial numbers to see if the given one exists return true if so

		line235:p9() just copy p8()

		line260:p11() for the bonus

		line279:getReadables() change to make an ArrayList of Items

		line307:getAudioProducts() change to make an ArrayList of Items


	User.java
		line76: checkUser(String) returns boolean, given currentUser 
				check if the given username exists in Users.txt return true is so

		line79: getUser(String) returns User object, given currentUser 
				make an object for the given username, returns the object created

		line102:addUser(String) returns boolean, given currentUser 
				append the given username to Users.txt (do not not check if username already exists), return true if done successfully


	ShoppingCart.java
		line167:getContent(String) returns String, given currentUser 
				returns the Cart_USERNAME.txt contents, don't split commas

		line201:checkQuantity(int, String, String) returns boolean, given quantity, sNo, currentUser
				check if there is enough quantity in the given sNo
				append item to Cart_USERNAME.txt and return true if so
				might not be able to append the item here


Matt:
	- working in kipp branch
	
Sean:
	- working in sean branch
	
Jack:
	- working in dev branch, then branching off to his own