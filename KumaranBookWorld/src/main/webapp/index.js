


//getting all home containers
let HomeContainer = document.querySelector(".HomeContainer");
let ScheduleContainer = document.querySelector(".ScheduleContainer");
let AboutUsContainer = document.querySelector(".AboutUsContainer");
let SignUpContainer = document.querySelector(".SignUpContainer");
let LoginContainer = document.querySelector(".LoginContainer");
let AboutBooksContainer = document.querySelector("#AboutBooksContainer");
let allBooks = document.querySelector("#allBooks");
let MyProfileContainer = document.querySelector("#myProfileContainer");
let AdminContainer = document.querySelector("#adminContainer");
let showSearchedBooks = document.querySelector('#searchedBooks');

//click function for all elements in nav bar
showHome()
function showHome() {
	AboutBooksContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	LoginContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	HomeContainer.style.display = "block";

	let allBooks = document.getElementById("allBooks");
	allBooks.style.display = "grid";


}

function showScheduleHome() {
	HomeContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	LoginContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	ScheduleContainer.style.display = "block";

	fetchSchedule();
}

function showAboutUsHome() {
	HomeContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	LoginContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	AboutUsContainer.style.display = "block";

}

function showSignUp() {
	document.getElementById('nameSignUp').value = "";
	document.getElementById('email').value = "";
	document.getElementById('PasswordSignUp').value = "";
	document.getElementById('ConfirmPassword').value = "";



	HomeContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	LoginContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	SignUpContainer.style.display = "block";
	SignUpContainer.style.alignitems = "center"
	SignUpContainer.style.justifycontent = "center";


}

function showLoginHome() {
	var userName = document.getElementById("Username");
	userName.value = "";
	var password = document.getElementById("Password");
	password.value = "";
	HomeContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	LoginContainer.style.display = "block";
}


function showAboutBooksContainer() {

	HomeContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	LoginContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	AboutBooksContainer.style.display = "flex";
}



function showMyProfileContainer() {

	HomeContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	LoginContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	AdminContainer.style.display = "none";
	showSearchedBooks.style.display = "none";


	MyProfileContainer.style.display = "block";
}


function showAdminPage() {

	HomeContainer.style.display = "none";
	ScheduleContainer.style.display = "none";
	AboutUsContainer.style.display = "none";
	LoginContainer.style.display = "none";
	SignUpContainer.style.display = "none";
	AboutBooksContainer.style.display = "none";
	MyProfileContainer.style.display = "none";
	showSearchedBooks.style.display = "none";

	AdminContainer.style.display = "block";

}



// search function and disappear
let menuBar = document.querySelector("#menu");
let searchBar = document.querySelector("#searchBarAndx");

function showSearchBar() {
	menuBar.style.display = "none";
	searchBar.style.display = "block";
	searchBar.style.display = "flex";
	searchBar.style.alignitems = "center";
	searchBar.style.justifycontent = "center";
}

function removeSearchBar() {
	searchBar.style.display = "none";
	menuBar.style.display = "block";
}


fetchBooks()


var book = { "b": "About book" };

function fetchBooks() {

	var httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			var response = JSON.parse(httpRequestObject.responseText);
			for (var i = 0; i < response.length; i++) {
				var eachObject = response[i];
				var templateTag = document.getElementsByTagName("template")[1];
				var eachItemDiv = templateTag.content;
				var copiedDiv = eachItemDiv.cloneNode(true);
				copiedDiv.querySelector("img").src = eachObject.image;
				copiedDiv.querySelector(".card-title").innerText = eachObject.title;
				copiedDiv.querySelector(".card-text").innerText = eachObject.author;
				copiedDiv.querySelector(".btn-primary").setAttribute('onclick', "fetchAboutBook(" + i + ")");
				document.querySelector('#allBooks').append(copiedDiv);

			}

		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/FetchBooks");
	httpRequestObject.send();
}

function fetchAboutBook(i) {

	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			document.getElementById('AboutBooksContainer').innerHTML = "";
			var response = JSON.parse(httpRequestObject.responseText);
			var eachObject = response[i];
			var templateTag = document.getElementsByTagName("template")[0];
			var eachItemDiv = templateTag.content;
			var copiedDiv = eachItemDiv.cloneNode(true);
			copiedDiv.querySelector("img").src = eachObject.image;
			copiedDiv.querySelector(".titleBook").innerText = "Title: " + eachObject.title;
			copiedDiv.querySelector(".AuthorName").innerText = "Author: " + eachObject.author;
			copiedDiv.querySelector(".publisher").innerText = "Publisher: " + eachObject.publisher;
			copiedDiv.querySelector(".genre").innerText = "Genre: " + eachObject.genre;
			copiedDiv.querySelector(".noOfBooks").innerText = "No. of availbale books: " + eachObject.noOfBooks;
			copiedDiv.querySelector(".bookDescription").innerText = "Book Description: " + eachObject.bookDescription;



			// adding reviews 

			var reviewArray = eachObject.reviewArray;
			console.log(reviewArray);
			var reviewDiv = copiedDiv.getElementById("existingReview");
			for (var j = 0; j < reviewArray.length; j++) {
				var eachReview = reviewArray[j];
				var name = eachReview.name;
				var bookReview = eachReview.bookReview;

				var eachExistingReview = `<p id="userName">Name: ${name} </p> 
					       <p id="userReview"> ${bookReview}</p>
					       <br>
					       <br> `;
				reviewDiv.innerHTML += (eachExistingReview);
			}

			copiedDiv.querySelector("#bookReviewSubmit").setAttribute('onclick', "addBookReview(" + eachObject.bookId + "," + i + ")");
           	document.getElementById('AboutBooksContainer').append(copiedDiv);
			showAboutBooksContainer();

		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/FetchBooks");
	httpRequestObject.send();
}


/* ************************ fetch schedule ************************* */

function fetchSchedule() {
	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			document.getElementById('tableBody').innerHTML = "";
			let response = JSON.parse(httpRequestObject.responseText);

			for (var i = 0; i < response.length; i++) {
				var eachObject = response[i];
				var templateTag = document.getElementsByTagName("template")[2];
				var eachItemDiv = templateTag.content;
				var copiedDiv = eachItemDiv.cloneNode(true);

				copiedDiv.querySelector("#scheduleId").innerText = eachObject.scheduleId;
				copiedDiv.querySelector("#date").innerText = eachObject.date;
				copiedDiv.querySelector("#address").innerText = eachObject.address;
				copiedDiv.querySelector("#city").innerText = eachObject.city;
				copiedDiv.querySelector("#timings").innerText = eachObject.timings;
				copiedDiv.querySelector(".btn-danger").setAttribute('onclick', "registerUser(" + eachObject.scheduleId + ")");
				document.getElementById('tableBody').append(copiedDiv);
			}
		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/FetchSchedule");
	httpRequestObject.send();

}




function registerUser(scheduleId) {
	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = httpRequestObject.responseText;
			if (response == "notLoggedIn") {
				launch_toast("You should login to register", false);
				showLoginHome()
			}
			else if (response == "registrationLimitExceeded") {
				launch_toast("Registrations closed! You can register for some other event", false);
			}
			else if (response == "alreadyRegistered") {
				launch_toast("You have already registered", false);
			}
			else if (response == "true") {
				launch_toast("You have registered successfully", true);
			}
			else {
				launch_toast("Registration failed", false);
			}
		}

	};
	httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/Registration");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send("scheduleId=" + scheduleId);
}

function getSessionUser() {
	var promise = new Promise(function(resolve, reject) {
		let httpRequestObject = new XMLHttpRequest();
		httpRequestObject.onreadystatechange = function() {
			if (httpRequestObject.readyState == 4) {
				let response = httpRequestObject.responseText;
				resolve(response);
			}
		}

		httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/GetSession");
		httpRequestObject.send();

	});
	return promise;
}

function getNoOfRegistrations(scheduleId) {
	var promise = new Promise(function(resolve, reject) {
		let httpRequestObject = new XMLHttpRequest();
		httpRequestObject.onreadystatechange = function() {
			if (httpRequestObject.readyState == 4) {
				let response = httpRequestObject.responseText;
				resolve(response);
			}
		}

		httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/GetNoOfRegistrations");
		httpRequestObject.send("scheduleId=" + scheduleId);

	});
	return promise;
}

function checkIfAlreadyRegistered(scheduleId) {
	var promise = new Promise(function(resolve, reject) {
		let httpRequestObject = new XMLHttpRequest();
		httpRequestObject.onreadystatechange = function() {
			if (httpRequestObject.readyState == 4) {
				let response = httpRequestObject.responseText;
				resolve(response);
			}
		};

		httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/CheckIfAlreadyRegistered");
		httpRequestObject.send("scheduleId=" + scheduleId);

	});
	return promise;
}




/* ******************************* Login ************************************** */

// checking submit event 
//if event happens addEventlListner will send the event (e)
// as a parameter

const form = document.querySelector('#form');
const userName = document.querySelector('#Username');
const password = document.querySelector('#Password');

form.addEventListener("submit", (e) => {
	//preventDefault() prevents the submission of the form if there is any errors 
	//we cannot handle this if it is a button click   

	if (!validateInputs()) {
		e.preventDefault();
	}
});



function validateInputs() {

	const userNameVal = userName.value.trim();
	const passWordVal = password.value.trim();
	let success = true;

	if (userNameVal === '') {
		success = false;
		setError(userName, 'username is required')
	}

	if (passWordVal === '') {
		success = false;
		setError(password, 'password is required')
	}

	else if (passWordVal.length < 6) {
		success = false;
		setError(password, 'Password must be atleast 6 characters')
	}
	else {
		success = loginValidator(userNameVal, passWordVal);
	}

	return success;

}


// ********************  login validation ***********************8

function loginValidator(userNameVal, passWordVal) {

	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = httpRequestObject.responseText;
			if (response == "admin") {
				response = true;
				launch_toast("Welcome Admin!", true);
				let signIn = document.querySelector('#signIn');
				signIn.style.display = "none";
				let signOut = document.querySelector('#signOut');
				signOut.style.display = "block";
				let adminButton = document.getElementById("adminPage");
				adminButton.style.display = "block";
				showAdminPage();
			}
			else if (response == "true") {
				launch_toast("You have successfully logged in", true);
				response = true;
				showSignOut();

			}
			else if (response == "false") {
				launch_toast("Invalid credentials", false)
				response = false;
			}
			else if ("alreadyLoggedIn") {
				launch_toast("You have already logged in another tab", false)
				response = false;
			}
			return response;
		}
	};

	httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/Login");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send("username=" + userNameVal + "&password=" + passWordVal);
}



/* *************************** session handling *************************** */

function getSession() {
	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = httpRequestObject.responseText;
			if (response == "true") {
				showSignOut()
			}
			else if (response == "adminLogin") {
				let signIn = document.querySelector('#signIn');
				signIn.style.display = "none";
				let myProfileButton = document.querySelector('#myProfileButton');
				myProfileButton.style.display = "none";
				let signOut = document.querySelector('#signOut');
				signOut.style.display = "block";
				let adminButton = document.getElementById("adminPage");
				adminButton.style.display = "block";

			}
			else {
				showSignIn()
			}
			return response;
		}
	};

	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/GetSession");
	httpRequestObject.send();

}

function invalidateSession() {
	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = httpRequestObject.responseText;
			if (response == "true")
				showSignIn()
		}
	};

	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/InvalidateSession");
	httpRequestObject.send();
}


/* ************************** show sign in sign out*************************** */

function showSignIn() {
	let signIn = document.querySelector('#signIn');
	signIn.style.display = "block";
	let signOut = document.querySelector('#signOut');
	signOut.style.display = "none";
	let myProfileButton = document.getElementById("myProfileButton");
	myProfileButton.style.display = "none";
	let adminButton = document.getElementById("adminPage");
	adminButton.style.display = "none";
	showHome();
}

function showSignOut() {
	let signIn = document.querySelector('#signIn');
	signIn.style.display = "none";
	let signOut = document.querySelector('#signOut');
	signOut.style.display = "block";
	let myProfileButton = document.querySelector('#myProfileButton');
	myProfileButton.style.display = "block";
	showHome();
}



// element - html element username or password 
function setError(element, message) {
	const inputGroup = element.parentElement;
	//we have the div input group in the above statement
	// to choose the class error within this div we do the following
	const errorElement = inputGroup.querySelector('.error');

	errorElement.innerText = message;
	inputGroup.classList.add('error');
	inputGroup.classList.remove('success');
}

function setSuccess(element) {
	const inputGroup = element.parentElement;
	const errorElement = inputGroup.querySelector('.error');

	errorElement.innerText = '';
	inputGroup.classList.add('success');
	inputGroup.classList.remove('error');
}




// ******************************** Sign up **************************************

const formsignUp = document.querySelector('#formsignUp');
const nameSignUp = document.querySelector('#nameSignUp');
const email = document.querySelector('#email');
const passwordSignUp = document.querySelector('#PasswordSignUp');
const confirmPasswordSignUp = document.querySelector('#ConfirmPassword');

function validateSignUpInputs() {
	const nameSignUpVal = nameSignUp.value.trim();
	const emailVal = email.value.trim();
	const passwordSignUpVal = passwordSignUp.value.trim();
	const confirmPasswordSignUpVal = confirmPasswordSignUp.value.trim();
	const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$/;
	const nameRegex = /^[a-zA-Z0-9]+$/;


	let success = true;

	if (nameSignUpVal === '') {
		success = false;
		setError(nameSignUp, 'name is required');
	}
	else if (!nameRegex.test(nameSignUpVal)) {
		success = false;
		setError(nameSignUp, 'Name can contain only upper case,lower case letters and numbers');
	}
	else {
		setSuccess(nameSignUp);
	}
	if (emailVal === '') {
		success = false;
		setError(email, 'email is required');
	}
	else if (!validateEmail(emailVal)) {
		success = false;
		setError(email, 'please enter a valid email');
	}
	else {
		setSuccess(email);
	}
	if (passwordSignUpVal === '') {
		success = false;
		setError(passwordSignUp, 'password is required');
	}
	else if (passwordSignUpVal.length < 6) {
		success = false;
		setError(passwordSignUp, 'password must be atleat 6 characters')
	}
	else if (!passwordRegex.test(passwordSignUpVal)) {
		success = false;
		setError(passwordSignUp, 'password must contain at least 1 numeric digit,1 uppercase,1 lowercase and 1 special character');
	}
	else {
		setSuccess(passwordSignUp);
	}
	if (confirmPasswordSignUpVal === '') {
		success = false;
		setError(confirmPasswordSignUp, 'please confirm your password');
	}
	else if (passwordSignUpVal !== confirmPasswordSignUpVal) {
		success = false;
		setError(confirmPasswordSignUp, 'passwords do not match');
	}
	else {
		setSuccess(confirmPasswordSignUp);
		addUser(nameSignUpVal, emailVal, passwordSignUpVal);
	}

	return success;
}



// **************************** add user on sign up *****************************

function addUser(nameSignUpVal, emailVal, passwordSignUpVal) {
	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = httpRequestObject.responseText;
			if (response == "true") {
				launch_toast("Sign up successfull,please login", true);
				showLoginHome();
			}
			else if (response == "userExist") {
				launch_toast("User already exist please login", false);
				showLoginHome();
			}
			else {
				launch_toast("Sign up failure", false);
			}
		}
	};
	httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/SignUpUser");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send("name=" + nameSignUpVal + "&email=" + emailVal + "&password=" + passwordSignUpVal);
}


// ************************** email validation regex *******************************
const validateEmail = (email) => {
	return String(email).toLowerCase().match(
		/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	);
};


// ********************************* Launch Toast ***********************************


function launch_toast(message, check) {

	if (check) {
		document.getElementById("img").innerHTML = `<i class="fa-solid fa-check fa-beat"></i>`;
	} else {
		document.getElementById("img").innerHTML = `<i class="fa-solid fa-triangle-exclamation fa-beat"></i>`;
	}

	document.getElementById("desc").innerHTML = message;
	var x = document.getElementById("toast")
	x.className = "show";
	setTimeout(function() { x.className = x.className.replace("show", ""); }, 4000);
}




// ************************************ Filter functions****************************************************************

function populateAuthors() {

	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = JSON.parse(httpRequestObject.responseText);
			let selectTagAuthors = document.getElementById("authorOptions");
			for (var i = 0; i < response.length; i++) {
				let authorOptions = `<option value="${response[i]}">${response[i]}</option>`;

				selectTagAuthors.innerHTML += (authorOptions);

			}
		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/PopulateAuthors");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send();

}



function populateGenres() {

	let httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			let response = JSON.parse(httpRequestObject.responseText);
			let selectTagGenres = document.getElementById("genreOptions");
			for (var i = 0; i < response.length; i++) {
				let genreOptions = `<option value="${response[i]}">${response[i]}</option>`;

				selectTagGenres.innerHTML += (genreOptions);

			}
		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/PopulateGenres");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send();

}


function fetchFilteredBooks() {
	document.querySelector('#filteredBooks').innerHTML = "";
	let authorOptions = document.getElementById("authorOptions").value;
	let genreOptions = document.getElementById("genreOptions").value;

	if (authorOptions == "none" && genreOptions == "none") {
		let allBooks = document.getElementById("allBooks");
		allBooks.style.display = "grid";
	}
	else {
		document.getElementById("allBooks").style.display = "none";
		var httpRequestObject = new XMLHttpRequest();
		httpRequestObject.onreadystatechange = function() {
			if (httpRequestObject.readyState == 4) {
				var res = httpRequestObject.responseText;
				if (res != "null") {
					var response = JSON.parse(res);
					for (var i = 0; i < response.length; i++) {
						var eachObject = response[i];
						var templateTag = document.getElementsByTagName("template")[1];
						var eachItemDiv = templateTag.content;
						var copiedDiv = eachItemDiv.cloneNode(true);

						copiedDiv.querySelector("img").src = eachObject.image;
						copiedDiv.querySelector(".card-title").innerText = eachObject.title;
						copiedDiv.querySelector(".card-text").innerText = eachObject.author;
						copiedDiv.querySelector(".btn-primary").setAttribute('onclick', "fetchAboutBook(" + eachObject.bookID + ")");
						document.querySelector('#filteredBooks').append(copiedDiv);

					}
				}
				else {
					let copiedDiv2 = `<div style="margin-left:200px"  class="jumbotron">
                                      <h1 class="display-4">No books available for the applied filters</h1>
                                      <p class="lead">You can try other filters</p>
                                  </div>`;
					let jumbotron = document.getElementById("filteredBooks");
					jumbotron.innerHTML += (copiedDiv2);
				}

			}
		};
		httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/FetchFilteredBooks");
		httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequestObject.send("authorOptions=" + authorOptions + "&genreOptions=" + genreOptions);
	}

}




// *****************************  search book *********************************8


function showSearchResult() {
	let searchValue = document.getElementById("search").value.trim();
	if (searchValue != "") {
		var httpRequestObject = new XMLHttpRequest();
		httpRequestObject.onreadystatechange = function() {
			if (httpRequestObject.readyState == 4) {
				var response = JSON.parse(httpRequestObject.responseText);
				document.getElementById("allBooks").style.display = "none";
				if (response == "null") {
					let copiedDiv3 = `<div style="margin-left:200px"  class="jumbotron">
                                        <h1 class="display-4">The requested book is not available</h1>
                                        <p class="lead">You can some other books</p>
                                      </div>`;
					let jumbotron = document.getElementById("searchedBooks");
					jumbotron.innerHTML += (copiedDiv3);
				}
				else {
					document.querySelector('#searchedBooks').innerHTML = "";
					var templateTag = document.getElementsByTagName("template")[1];
					var eachItemDiv = templateTag.content;
					var copiedDiv = eachItemDiv.cloneNode(true);
					copiedDiv.querySelector("img").src = response.image;
					copiedDiv.querySelector(".card-title").innerText = response.title;
					copiedDiv.querySelector(".card-text").innerText = response.author;
					copiedDiv.querySelector(".btn-primary").setAttribute('onclick', "fetchAboutBook(" + response.bookID + ")");
					document.querySelector('#searchedBooks').append(copiedDiv);
					document.querySelector('#searchedBooks').style.display = "grid";
					
				}
			}
		};
		httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/SearchBooks");
		httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequestObject.send("searchValue=" + searchValue);
	}

}



// *********************************** add book review ****************************************

function addBookReview(bookId, i) {
	var review = document.getElementById("userReviewInput").value;
	var httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			var response = httpRequestObject.responseText;
			if (response == "notLoggedIn") {
				launch_toast("You should sign in to add review", false);
				showLoginHome()
			}
			else if (response == "true") {
				launch_toast("Thankyou for your valuable review !", true);
				fetchAboutBook(i);
			}
			else if (response == "false") {
				launch_toast("Review couldn't be added", false);
			}
		}
	};
	httpRequestObject.open("POST", "http://localhost:8080/KumaranBookWorld/AddBookReview");
	httpRequestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequestObject.send("review=" + review + "&bookId=" + bookId);

}



// ****************************   MyProfile  *************************************



function fetchMyProfile() {
	var userReviewDiv = document.getElementById("myBookReview");

	var httpRequestObject = new XMLHttpRequest();
	httpRequestObject.onreadystatechange = function() {
		if (httpRequestObject.readyState == 4) {
			var res = httpRequestObject.responseText;


			if (res == "noReview") {
				let noReview = `<p>No reviews yet</p>`;
				userReviewDiv.innerHTML = "";
				userReviewDiv.innerHTML += (noReview);
			}
			else if (res == "noScheduleList") {
				let scheduleList = `No Registrations yet`;
				document.getElementById('tableBodyMyProfile').innerHTML = "";
				document.getElementById('tableBodyMyProfile').append(scheduleList);
			}
			else if (res == "noReviewnoScheduleList") {
				let noReview = `<p>No reviews yet</p>`;
				userReviewDiv.innerHTML = "";
				userReviewDiv.innerHTML += (noReview);

				let scheduleList = `No Registrations yet`;
				document.getElementById('tableBodyMyProfile').innerHTML = "";
				document.getElementById('tableBodyMyProfile').append(scheduleList);

			}
			else {
				var response = JSON.parse(res);
				var scheduleResponse = response[0];
				var reviewResponse = response[1];


				document.getElementById('tableBodyMyProfile').innerHTML = "";

				for (var i = 0; i < scheduleResponse.length; i++) {
					var eachObject = scheduleResponse[i];
					var templateTag = document.getElementsByTagName("template")[3];
					var eachItemDiv = templateTag.content;
					var copiedDiv = eachItemDiv.cloneNode(true);

					copiedDiv.querySelector("#scheduleId").innerText = eachObject.scheduleId;
					copiedDiv.querySelector("#date").innerText = eachObject.date;
					copiedDiv.querySelector("#address").innerText = eachObject.address;
					copiedDiv.querySelector("#city").innerText = eachObject.city;
					copiedDiv.querySelector("#timings").innerText = eachObject.timings;
					document.getElementById('tableBodyMyProfile').append(copiedDiv);
				}
				userReviewDiv.innerHTML = "";
				for (var j = 0; j < reviewResponse.length; j++) {

					let myBookReview = `<h5>Book name: ${reviewResponse[j].bookName} </h5>
                                    <h5> Review: ${reviewResponse[j].bookReview}</h5>
                                    <br>
                                    <br>`;


					userReviewDiv.innerHTML += (myBookReview);

				}

			}
		}
	};
	httpRequestObject.open("GET", "http://localhost:8080/KumaranBookWorld/FetchMyProfile");
	httpRequestObject.send();

}



