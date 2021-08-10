

window.onload = function(){
	loadCurrUser();
	document
		.getElementById("signoutBtn")
		.addEventListener('click', signout)
}


function loadCurrUser(){
	console.log("starting currUser ajax request");
	let userRole = undefined;
	
	fetch('http://localhost:8080/project_1_v2/json/curr_user')
		.then(function(myResponse){
					const convertedResponse = myResponse.json();
					return convertedResponse;
				})
		.then(function(mySecondResponse){
					//console.log(mySecondResponse);
					userRole = printCurrUser(mySecondResponse);
						//console.log("LoadCurrUser Role: " + userRole);
					if(userRole == "Employee"){
						employeeLoadReimbs();
						loadEmployeeForm();
					}else {
						loadManagerForm();
						managerLoadReimbs();
					}
				})
}


function signout(){
	fetch('http://localhost:8080/project_1_v2/forwarding/signout')
	window.location.replace("http://localhost:8080/project_1_v2/index.html");
}




function printCurrUser(userJSON){
	//console.log("In getCurruser.");
	let myUser = document.getElementById("myWelcomeHeader")
	let username = userJSON.username;
	let role = userJSON.role;
	let welcomeMessage = `<h1>Welcome back ${username}!</h1>`
	myUser.innerHTML = welcomeMessage;
	return role;
}































function employeeLoadReimbs(){
	console.log("starting the ajax request");

	fetch('http://localhost:8080/project_1_v2/json/reimbursements')
	.then(function(myResponse){
		const convertedResponse = myResponse.json();
		return convertedResponse;
	})
	.then(function(mySecondResponse){
		//console.log(mySecondResponse);
		if(document.getElementById("reimb_author1") != undefined){
			document.getElementById("reimb_author1").remove();
			document.getElementById("reimb_author2").remove();
			document.getElementById("reimb_author3").remove();
		}
		employeeDOMManipulation(mySecondResponse);
	})
}

function managerLoadReimbs(){
	console.log("starting the ajax request");

	fetch('http://localhost:8080/project_1_v2/json/managerReimbursements')
	.then(function(myResponse){
		const convertedResponse = myResponse.json();
		return convertedResponse;
	})
	.then(function(mySecondResponse){
		//console.log(mySecondResponse);
		ManagerDOMManipulation(mySecondResponse);
	})
}

function employeeDOMManipulation(ourJSON){
	let myUser = document.getElementById("#myWelcomeHeader")
	
	
	for(let i = 0; i< ourJSON.length; i++){
		////////////CREATE ELEMENTS DYNAMICALLY////////////////
		
		//step1: creating our new element
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		newTR.setAttribute("class", "table-row");
		
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		
		
		//step 2: populate creations
		newTH.setAttribute("scope", "row");
		let myTextH = document.createTextNode(ourJSON[i].reimbId);
		let myTextD1 = document.createTextNode(`$${ourJSON[i].reimbAmount}`);
		let myTextD2 = document.createTextNode(ourJSON[i].reimbSubmitted);
		let myTextD3 = document.createTextNode(ourJSON[i].reimbResolved);
		let myTextD4 = document.createTextNode(ourJSON[i].reimbDescription);
		let myTextD5 = document.createTextNode(ourJSON[i].reimbResolver);
		let myTextD6 = document.createTextNode(ourJSON[i].reimbStatus);
		let myTextD7 = document.createTextNode(ourJSON[i].reimbType);
		
		//all appending
		newTH.appendChild(myTextH);
		newTD1.appendChild(myTextD1);
		newTD2.appendChild(myTextD2);
		newTD3.appendChild(myTextD3);
		newTD4.appendChild(myTextD4);
		newTD5.appendChild(myTextD5);
		newTD6.appendChild(myTextD6);
		newTD7.appendChild(myTextD7);
		
		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);

		
		let pendingSelection = document.querySelector("#pendingReimbTable");
		let approvedSelection = document.querySelector("#approvedReimbTable");
		let deniedSelection = document.querySelector("#deniedReimbTable");
		switch(ourJSON[i].reimbStatus){
			case "Pending":
				pendingSelection.appendChild(newTR);
				break;
			case "Approved":
				approvedSelection.appendChild(newTR);
				break;
			case "Denied":
				deniedSelection.appendChild(newTR);
				break;
			default:
				break;
		}
	}
}





function ManagerDOMManipulation(ourJSON){
	let myUser = document.getElementById("#myWelcomeHeader")
	
	
	for(let i = 0; i< ourJSON.length; i++){
		////////////CREATE ELEMENTS DYNAMICALLY////////////////
		
		//step1: creating our new element
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		newTR.setAttribute("class", "table-row");
		
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		let newTD8 = document.createElement("td");
		
		
		//step 2: populate creations
		newTH.setAttribute("scope", "row");
		let myTextH = document.createTextNode(ourJSON[i].reimbId);
		let myTextD1 = document.createTextNode(`$${ourJSON[i].reimbAmount}`);
		let myTextD2 = document.createTextNode(ourJSON[i].reimbSubmitted);
		let myTextD3 = document.createTextNode(ourJSON[i].reimbResolved);
		let myTextD4 = document.createTextNode(ourJSON[i].reimbDescription);
		let myTextD5 = document.createTextNode(ourJSON[i].reimbAuthor);
		let myTextD6 = document.createTextNode(ourJSON[i].reimbResolver);
		let myTextD7 = document.createTextNode(ourJSON[i].reimbStatus);
		let myTextD8 = document.createTextNode(ourJSON[i].reimbType);
		
		//all appending
		newTH.appendChild(myTextH);
		newTD1.appendChild(myTextD1);
		newTD2.appendChild(myTextD2);
		newTD3.appendChild(myTextD3);
		newTD4.appendChild(myTextD4);
		newTD5.appendChild(myTextD5);
		newTD6.appendChild(myTextD6);
		newTD7.appendChild(myTextD7);
		newTD8.appendChild(myTextD8);
		
		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);
		newTR.appendChild(newTD8);

		
		let pendingSelection = document.querySelector("#pendingReimbTable");
		let approvedSelection = document.querySelector("#approvedReimbTable");
		let deniedSelection = document.querySelector("#deniedReimbTable");
		switch(ourJSON[i].reimbStatus){
			case "Pending":
				pendingSelection.appendChild(newTR);
				break;
			case "Approved":
				approvedSelection.appendChild(newTR);
				break;
			case "Denied":
				deniedSelection.appendChild(newTR);
				break;
			default:
				break;
		}
		
		
	}
	createIdSelector();
}

function createIdSelector(){
	idSelectorSize();
	let mySelect = document.querySelectorAll(".table-row");
	for(let n = 0; n < mySelect.length; n++){
		if(mySelect[n].children[7].outerText == "Pending"){
			let newSelect = document.createElement("option");
			newSelect.setAttribute("value", mySelect[n].children[0].outerText);
			newSelect.textContent = mySelect[n].children[0].outerText;
			
			let mySelector = document.getElementById("reimb_req_selector");
			mySelector.appendChild(newSelect);
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
function loadEmployeeForm(){
	//document.getElementById("userForm").innerHTML = '<div id="empFormDivDiv"><div id="empFormDiv"></div></div>';
	//let formTotal = '<form id="reimb_form" action="/project_1_v2/forwarding/reimb_request" method="post"> <div class="container"> <div class="row"> <div class="col"> <label for="amount">Amount</label> <input type="number" name="reimb_req_amount" id="reimb_req_amount" step="0.01" placeholder="ex: 105.75" required><br> <br> <br> </div> <div class="col"> <label for="description">Description</label> <input type="text" name="reimb_req_description" id="reimb_req_description" placeholder="ex: Gas money for trip to MD branch office." maxlength="200" required><br> </div> <div class="col"> <label for="type">Type</label> <select name="reimb_req_type" id="reimb_req_type" required> <option value=0>Food</option> <option value=1>Travel</option> <option value=2>Lodging</option> <option value=3>Other</option> </select> <input type="submit" value="submit" class="btn btn-primary" id="reimb_form_submit"> </div> </div> </div> </form>'
	//document.getElementById('empFormDiv').innerHTML = formTotal;
	
	document
		.getElementById("reimb_form_submit")
		.addEventListener('click', noRedirect);

}
	
	
	
	
	
function noRedirect(mySubmit){
	mySubmit.preventDefault();
	//console.log('im here');
	submitReimb();	

}
	
	
function submitReimb(){
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if(xhttp.status===200){
			//console.log('200 status');
		}
	};
	
	xhttp.open("POST", `http://localhost:8080/project_1_v2/forwarding/reimb_request`);
	
	xhttp.setRequestHeader("content-type", "application/json");
	
	let reimb = {
	  	"reimbAmount": document.getElementById('reimb_req_amount').value,
	  	"reimbDescription": document.getElementById('reimb_req_description').value,
		"reimbType": document.getElementById('reimb_req_type').value
	};
	
	//console.log(reimb);
	xhttp.send(JSON.stringify(reimb));
	//deleteAll();
	//employeeLoadReimbs()
	window.location.replace("http://localhost:8080/project_1_v2/forwarding/reimbursement");
}




















function loadManagerForm(){
	document.getElementById("userForm").innerHTML= '<div id="formDivDiv"> <div id="formDiv"></div> </div>';
	let formTotal = '<form id="reimb_resolver_form" method="post"> <div><label>Reimbursement id.</label><div> <select name="reimb_req_selector" id="reimb_req_selector" style="width:72px;"> </select> </div></div><input type="radio" name="raadioSelect" id="reimb_approved" value="1"> <label for="reimb_approved">Approve</label><br> <input type="radio" name="raadioSelect" id="reimb_denied" value="2"> <label for="reimb_denied">Deny</label><br><div><input type="submit" class="btn btn-primary" value="submit" id="manager_reimb_form_submit"></div> </form>';
	document.getElementById('formDiv').innerHTML = formTotal;
	
	document
		.getElementById("manager_reimb_form_submit")
		.addEventListener('click', managerNoRedirect);

}

function managerNoRedirect(mySubmit){
	mySubmit.preventDefault();
	//console.log('im here: line 247');
	managerSubmitReimb();	

}
	
	
function managerSubmitReimb(){
	//console.log('In managerSubmitReimb');

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if(xhttp.status===200){
			//console.log('200 status');
		}
	};
	
	xhttp.open("POST", `http://localhost:8080/project_1_v2/forwarding/update_reimb_status`);
	
	xhttp.setRequestHeader("content-type", "application/json");
	
	if(document.getElementById('reimb_approved').checked) {   
    	let update_reimb_status = {
	  	"reimbId": document.getElementById('reimb_req_selector').value,
	  	"reimbStatus": document.getElementById('reimb_approved').value,
		};
		//console.log(update_reimb_status);
		xhttp.send(JSON.stringify(update_reimb_status));
		window.location.replace("http://localhost:8080/project_1_v2/forwarding/reimbursement");

    }else if(document.getElementById('reimb_denied').checked) {   
    	let update_reimb_status = {
	  	"reimbId": document.getElementById('reimb_req_selector').value,
	  	"reimbStatus": document.getElementById('reimb_denied').value,
		};
		//console.log(update_reimb_status);
		xhttp.send(JSON.stringify(update_reimb_status));
		window.location.replace("http://localhost:8080/project_1_v2/forwarding/reimbursement");
    } else {
    	//console.log("An error occured");
    }
}


function deleteAll(){
	document.getElementById("pendingReimbTable").innerHTML = "";
	document.getElementById("approvedReimbTable").innerHTML = "";
	document.getElementById("deniedReimbTable").innerHTML = "";
}



function idSelectorSize(){
	document.getElementById("reimb_req_selector").innerHTML = "";
}

