window.onload = () => {
	
	employee();	
	
	reimbursements();
	
	countReimbursements();
	
	selectAllStatus();

	
	document.getElementById("reimSubmit").addEventListener("click",insertReimbursement);
	
	
	document.getElementById("editBTN").addEventListener("click",edit);
	
	document.getElementById("editUser").addEventListener("click",editUser);
	
	document.getElementById("close").addEventListener("click", close);
	
}

function employee(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if((xhr.readyState == 4) && (xhr.status == 200)){
			let welcome = document.getElementById("welcome");
			let firstName = document.getElementsByName("firstName")[0];
			let lastName = document.getElementsByName("lastName")[0];
			let email = document.getElementsByName("email")[0];
			let userName = document.getElementsByName("userName")[0];
			let profileFirstName = document.getElementById("profileFirstName");
			let profileLastName = document.getElementById("profileLastName");
			let profileEmail = document.getElementById("profileEmail");
		    let profileUserName = document.getElementById("profileUserName");
			
			//Initializing the object
			let obj = JSON.parse(xhr.responseText);
			//console.log(obj);
			
			//Setting up home
			welcome.innerHTML = `Welcome ${obj.emp_FirstName} ${obj.emp_LastName}`
			
			//setting up values for profile
			firstName.value = obj.emp_FirstName;
			lastName.value = obj.emp_LastName;
			userName.value = obj.emp_UserName;
			email.value = obj.emp_Email;
			
			//setting homepage profile
			profileFirstName.innerHTML = `First Name: ${obj.emp_FirstName}`
			profileLastName.innerHTML = `Last Name: ${obj.emp_LastName}`
			profileEmail.innerHTML = `Email: ${obj.emp_Email}` 
			profileUserName.innerHTML = `UserName: ${obj.emp_UserName}`
			
		}
	};
	xhr.open("POST","http://localhost:8080/expenseReimbursementSystem/employee");
	xhr.send();

}

function reimbursements(){
	
	var xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function(){
		if((xhr2.readyState == 4) && (xhr2.status == 200)){
			let obj2 = JSON.parse(xhr2.responseText);
			
			let approved = document.getElementById("Approved");
			let pending = document.getElementById("Pending");
			let underReview = document.getElementById("UnderReview");
			let denied = document.getElementById("Denied");
		
			let counta = 0;
			let countp = 0;
			let countur = 0;
			let countd = 0;
			for(let i = 0; i < obj2.length; i++){
				if(obj2[i].ras_descr === 'Approved'){
					let row = approved.insertRow(counta);
					counta += 1;
					let cell1 = row.insertCell(0);
					let cell2 = row.insertCell(1);
					let cell3 = row.insertCell(2);
					let cell4 = row.insertCell(3);
					let cell5 = row.insertCell(4);
					
					cell1.innerHTML = obj2[i].reim_id;
					cell2.innerHTML = obj2[i].ras_descr;
					cell3.innerHTML = obj2[i].reim_descr;
					cell4.innerHTML = obj2[i].rtype_descr;
					cell5.innerHTML = obj2[i].reim_amount;
				}
			}
			
			for(let x = 0; x < obj2.length; x++){
				if(obj2[x].ras_descr === 'Pending'){
					let row = pending.insertRow(countp);
					countp += 1;
					let cell1 = row.insertCell(0);
					let cell2 = row.insertCell(1);
					let cell3 = row.insertCell(2);
					let cell4 = row.insertCell(3);
					let cell5 = row.insertCell(4);
					
					cell1.innerHTML = obj2[x].reim_id;
					cell2.innerHTML = obj2[x].ras_descr;
					cell3.innerHTML = obj2[x].reim_descr;
					cell4.innerHTML = obj2[x].rtype_descr;
					cell5.innerHTML = obj2[x].reim_amount;
				}
			}
			
			for(let y = 0; y < obj2.length; y++){
				if(obj2[y].ras_descr === 'Under Review'){
					let row = underReview.insertRow(countur);
					countur += 1;
					let cell1 = row.insertCell(0);
					let cell2 = row.insertCell(1);
					let cell3 = row.insertCell(2);
					let cell4 = row.insertCell(3);
					let cell5 = row.insertCell(4);
					
					cell1.innerHTML = obj2[y].reim_id;
					cell2.innerHTML = obj2[y].ras_descr;
					cell3.innerHTML = obj2[y].reim_descr;
					cell4.innerHTML = obj2[y].rtype_descr;
					cell5.innerHTML = obj2[y].reim_amount;
					
				}
			}
			
			for(let j = 0; j < obj2.length; j++){
				if(obj2[j].ras_descr === 'Denied'){
					let row = denied.insertRow(countd);
					countd += 1;
					let cell1 = row.insertCell(0);
					let cell2 = row.insertCell(1);
					let cell3 = row.insertCell(2);
					let cell4 = row.insertCell(3);
					let cell5 = row.insertCell(4);
					
					cell1.innerHTML = obj2[j].reim_id;
					cell2.innerHTML = obj2[j].ras_descr;
					cell3.innerHTML = obj2[j].reim_descr;
					cell4.innerHTML = obj2[j].rtype_descr;
					cell5.innerHTML = obj2[j].reim_amount;
				}
			}
				
		}
	};
	xhr2.open("POST","http://localhost:8080/expenseReimbursementSystem/reimbursements");
	xhr2.send();
}

function countReimbursements(){
	
	var xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange = function(){
		if((xhr3.readyState == 4) && (xhr3.status == 200)){
			let obj3 = JSON.parse(xhr3.responseText);

			
			if(obj3.Approved != undefined){
				let bapproved = document.getElementsByName("bapproved")[0].textContent = obj3.Approved;	
			} 
			if(obj3.Pending != undefined){
				let bpending = document.getElementsByName("bpending")[0].textContent = obj3.Pending;
				
			}
			if(obj3.Denied != undefined){
				let bdenied  = document.getElementsByName("bdenied")[0].textContent = obj3.Denied;
				
			}
			if(obj3['Under Review'] != undefined){
				let bunderReview = document.getElementsByName("bunderReview")[0].textContent = obj3['Under Review'];
			}
			
			
		}
		
	};
	xhr3.open("POST","http://localhost:8080/expenseReimbursementSystem/countReimbursements");
	xhr3.send();
}

function selectAllStatus(){
	var xhr6 = new XMLHttpRequest();
	xhr6.onreadystatechange = function(){
		if((xhr6.readyState == 4) && (xhr6.status == 200)){
			let obj6 = JSON.parse(xhr6.responseText);
			//console.log(obj6);
			
			for(let i = 0; i < obj6.length; i++){
				
			let x = document.createElement("LI");
			
			let att = document.createAttribute("class");
			att.value = "list-group-item";
			
			let y = document.createTextNode(`${obj6[i].st_type} :${obj6[i].st_descr}`);
			x.setAttributeNode(att);
			x.appendChild(y);
		
			document.getElementById("statusTable").appendChild(x);
			}
		}
	};
	xhr6.open("POST","http://localhost:8080/expenseReimbursementSystem/selectAllStatus");
	xhr6.send();
	
}

/*--------------------------Edit user function ---------------------*/
function editUser(){

	let firstName = document.getElementsByName("firstName")[0];
	let lastName = document.getElementsByName("lastName")[0];
	let email = document.getElementsByName("email")[0];
	let userName = document.getElementsByName("userName")[0];
	
//	console.log(firstName.value);
//	console.log(lastName.value);
//	console.log(email.value);
//	console.log(userName.value);
	
	let objusr ={
		emp_FirstName:firstName.value,
		emp_LastName:lastName.value,
		emp_Email:email.value,
		emp_UserName:userName.value
	}
	
	if(firstName.value == "" || lastName.value == "" || email.value == "" || userName.value == ""){
		console.log("hello");
		document.getElementById("statusEmptyAdd").hidden = false;
	}
	else{
	let myjsonuser = JSON.stringify(objusr);

	var xhr5 = new XMLHttpRequest();
	xhr5.onreadystatechange = function(){
		if((xhr5.readyState == 4) && (xhr5.status == 200)){
				let pass = JSON.parse(xhr5.responseText);

			}
		};
		xhr5.open("POST","http://localhost:8080/expenseReimbursementSystem/updateUserInfo");
		xhr5.setRequestHeader("Content-Type", "application/json");
		xhr5.send(myjsonuser);
		
		window.location.href="http://localhost:8080/expenseReimbursementSystem/html/index.html";
	}
}

function edit(){
	let pressed = document.getElementsByName("firstName")[0].disabled;

	let firstName = document.getElementsByName("firstName")[0];
	let lastName = document.getElementsByName("lastName")[0];
	let email = document.getElementsByName("email")[0];
	let userName = document.getElementsByName("userName")[0];
	
	if(pressed === true){
	firstName.disabled = false;
	lastName.disabled = false;
	email.disabled = false;
	//userName.disabled = false;
	}else{
		firstName.disabled = true;
		lastName.disabled = true;
		email.disabled = true;
		//userName.disabled = true;
		employee();	
	}
}


/*--------------------------insert reimbursement function ---------------------*/
function insertReimbursement(){
	//console.log("hello");
	let description = document.getElementsByName("description")[0].value;
	let amount = document.getElementsByName("amounts")[0].value;
	let type = document.getElementsByName("types")[0].value;
	
	let objreim = {
			reim_descr : description,
			reim_amount : amount,
			rtype_id : type
	};
	
	let myjson = JSON.stringify(objreim);
	
	//console.log(myjson);
	
	if(description == "" || amount == "" || type == ""){
		document.getElementById("reimbursementEmptyAdd").hidden = false;
	}
	else{
		var xhr4 = new XMLHttpRequest();
		xhr4.onreadystatechange = function(){
			if((xhr4.readyState == 4) && (xhr4.status == 200)){
				
				 $('#reimModalCenter').modal('hide');
				 document.getElementById("reimbursementEmptyAdd").hidden = true;
				 
				document.getElementsByName("description")[0].value = "";
				document.getElementsByName("amounts")[0].value = "";
				document.getElementsByName("types")[0].value = "";
				 
				let approved = document.getElementById("Approved");
				let pending = document.getElementById("Pending");
				let underReview = document.getElementById("UnderReview");
				let denied = document.getElementById("Denied");
				clearTable(approved);
				clearTable(pending);
				clearTable(underReview);
				clearTable(denied);
				 
				reimbursements();
				countReimbursements();
				}
			};
		xhr4.open("POST","http://localhost:8080/expenseReimbursementSystem/insertReimbursement");
		xhr4.setRequestHeader("Content-Type", "application/json");
		xhr4.send(myjson);

	}
}

/*--------------------------Clear table function ---------------------*/
function clearTable(table) {	
	while(table.rows.length > 0){
		table.deleteRow(0);
	}
  }

/*--------------------------Close Modal ---------------------*/
function close(){
//	document.getElementById("fieldsEmpty").hidden = true;
//	document.getElementById("userExist2").hidden = true;
//	 $('#employeeModalCenter').modal('hide');
	 
//	 document.getElementById("fieldsEmptyAdd").hidden = true;
//	 $('#employeeModalCenter').modal('hide');
	 
	 document.getElementById("reimbursementEmptyAdd").hidden = true;
	 $('#reimModalCenter').modal('hide');
		document.getElementsByName("description")[0].value = "";
		document.getElementsByName("amounts")[0].value = "";
		document.getElementsByName("types")[0].value = "";
	 
//	 document.getElementById("statusEmptyAdd").hidden = true;
//	 $('#statusModalCenter').modal('hide');
}
