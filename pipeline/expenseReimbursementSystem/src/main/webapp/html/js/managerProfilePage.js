window.onload = () => {	
	
employee();

viewAllEmployees();
	
viewAllReimbursements();	

selectAllStatus();
	
	
	//document.getElementById("approveReimbursement").addEventListener("click",approveReimbursement());
	document.getElementById("editBTN").addEventListener("click",edit);
	document.getElementById("search").addEventListener("click",viewEmployee);
	document.getElementById("searchReim").addEventListener("click",viewUserReimbursement);
	document.getElementById("approveReim").addEventListener("click",approveReimbursement);
	document.getElementById("insertEmployee").addEventListener("click",checkUserName);
	document.getElementById("insertStatus").addEventListener("click", insertStatus);
	document.getElementById("editUser").addEventListener("click",editUser);
	
	document.getElementById("close").addEventListener("click",close);
	document.getElementById("close2").addEventListener("click",close);
	document.getElementById("close3").addEventListener("click",close);
	
}

/*------------------------------------------------This is where the function start for view all selectAllStatus-----------------------*/
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
/*------------------------------------------------This is where the function start for view all reimbursements-----------------------*/
function viewAllReimbursements(){
	var xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange = function(){
		if((xhr3.readyState == 4) && (xhr3.status == 200)){
			let obj3 = JSON.parse(xhr3.responseText);
			//console.log(obj3);
			let reimTable = document.getElementById("reimbursementTable");
			let counta = 0;
			let countp = 0;
			let countur = 0;
			let countd = 0;

			for(let i = 0; i < obj3.length; i++){
				let row = reimTable.insertRow(0+i);
		
				let cell1 = row.insertCell(0);
				let cell2 = row.insertCell(1);
				let cell3 = row.insertCell(2);
				let cell4 = row.insertCell(3);
				let cell5 = row.insertCell(4);
				let cell6 = row.insertCell(5);
				let cell7 = row.insertCell(6);
				let cell8 = row.insertCell(7);
				
				cell1.innerHTML = obj3[i].reim_id;
				cell2.innerHTML = obj3[i].ras_descr;
				cell3.innerHTML = obj3[i].reim_descr;
				cell4.innerHTML = obj3[i].rtype_descr;
				cell5.innerHTML = obj3[i].emp_username;
				cell6.innerHTML = `$ ${obj3[i].reim_amount}`;
				cell7.innerHTML = obj3[i].approvalMgr_id;
				//cell8.innerHTML = 
				
				var btn = document.createElement('input');
				btn.type = "button";
			    btn.className = "btn btn-info";
			    btn.value = "Update";
			    btn.addEventListener("click", function(){ 
			    	myScript(obj3[i].reim_id);
			    	});
			    cell8.appendChild(btn);
				
				let approved = document.getElementById("Approved");
				let pending = document.getElementById("Pending");
				let underReview = document.getElementById("UnderReview");
				let denied = document.getElementById("Denied");
				
				
				if(obj3[i].ras_descr === 'Approved'){
					counta +=1;
				}
				if(obj3[i].ras_descr === 'Pending'){
					countp +=1;
				}
				if(obj3[i].ras_descr === 'Under Review'){
					countur +=1;
				}
				if(obj3[i].ras_descr === 'Denied'){
					countd +=1;
				}
			}
			document.getElementsByName("bapproved")[0].textContent = counta;	
			document.getElementsByName("bpending")[0].textContent = countp;
			document.getElementsByName("bdenied")[0].textContent = countd;
			document.getElementsByName("bunderReview")[0].textContent = countur;	
		}
	};
	xhr3.open("POST","http://localhost:8080/expenseReimbursementSystem/viewAllReimbursements");
	xhr3.send();

}

/*------------------------------------------------This is where the function start for view all employee-----------------------*/
function viewAllEmployees(){
	var xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function(){
		if((xhr2.readyState == 4) && (xhr2.status == 200)){
			
			let obj2 = JSON.parse(xhr2.responseText);
			//console.log(obj2);
			let employeeTable = document.getElementById("employeeTable");
			
			
			for(let i = 0; i < obj2.length; i++){
			let row = employeeTable.insertRow(0+i);
	
			let cell1 = row.insertCell(0);
			let cell2 = row.insertCell(1);
			let cell3 = row.insertCell(2);
			let cell4 = row.insertCell(3);
			let cell5 = row.insertCell(4);
			
			cell1.innerHTML = obj2[i].emp_id;
			cell2.innerHTML = obj2[i].emp_FirstName;
			cell3.innerHTML = obj2[i].emp_LastName;
			cell4.innerHTML = obj2[i].emp_Email;
			cell5.innerHTML = obj2[i].emp_UserName;
			}
		}
	};
	xhr2.open("POST","http://localhost:8080/expenseReimbursementSystem/viewAllEmployees");
	xhr2.send();
}
/*------------------------------------------------This is where the function start for employee-----------------------*/
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

/*------------------------------------------------This is where the function myScript Starts-----------------------*/
function myScript(p){
	//console.log('This is the id :'+ p);
	$('#reimModalCenter').modal('show');
	document.getElementById("bookId").value = p;	
}

/*------------------------------------------------This is where the function start-----------------------*/
function approveReimbursement(){
	
	let reimID = document.getElementById("bookId");
	let rasDescr = document.getElementsByName("optionsStatus")[0];

	
	let objusr ={
			reim_id:reimID.value,
			ras_id:rasDescr.value
		}
	let myjsonappreim = JSON.stringify(objusr);
	
	if(reimID.value == "" || rasDescr.value == ""){
		document.getElementById("reimApproval").hidden = false;
	}
	else{
	var xhr4 = new XMLHttpRequest();
		xhr4.onreadystatechange = function(){
			if((xhr4.readyState == 4) && (xhr4.status == 200)){
				document.getElementById("reimApproval").hidden = true;
				let reimTable = document.getElementById("reimbursementTable");
				clearTable(reimTable);
				viewAllReimbursements();
				 $('#reimModalCenter').modal('hide');
			}
		};
		xhr4.open("POST","http://localhost:8080/expenseReimbursementSystem/approveReimbursement");
		xhr4.setRequestHeader("Content-Type", "application/json");
		xhr4.send(myjsonappreim);
		
		/*window.location.href="http://localhost:8080/expenseReimbursementSystem/html/mgrHome.html";*/	
	}
}

/*-------------------------This is where the function starts for user reimbursement-----------------------*/
function viewUserReimbursement(){
	let searchReim = document.getElementsByName("searchUserReim")[0].value;
	//console.log(searchReim);
	
	let objreim = {
			emp_UserName : searchReim
	};
	let myjsonreim = JSON.stringify(objreim);
	
	var xhr8 = new XMLHttpRequest();
		xhr8.onreadystatechange = function(){
			if((xhr8.readyState == 4) && (xhr8.status == 200)){
				let obj8 = JSON.parse(xhr8.responseText);
				//console.log(obj8);
				
				let employeeTable = document.getElementById("reimbursementTable");
				while(employeeTable.rows.length > 0){
					employeeTable.deleteRow(0);
				}
					
					for(let i = 0; i < obj8.length; i++){
						let row = employeeTable.insertRow(0+i);
							
						let cell1 = row.insertCell(0);
						let cell2 = row.insertCell(1);
						let cell3 = row.insertCell(2);
						let cell4 = row.insertCell(3);
						let cell5 = row.insertCell(4);
						let cell6 = row.insertCell(5);
						let cell7 = row.insertCell(6);
						let cell8 = row.insertCell(7);
								
						cell1.innerHTML = obj8[i].reim_id;
						cell2.innerHTML = obj8[i].ras_descr;
						cell3.innerHTML = obj8[i].reim_descr;
						cell4.innerHTML = obj8[i].rtype_descr;
						cell5.innerHTML = obj8[i].emp_username;
						cell6.innerHTML = `$ ${obj8[i].reim_amount}`;
						cell7.innerHTML = obj8[i].approvalMgr_id;
						
						var btn = document.createElement('input');
						btn.type = "button";
					    btn.className = "btn btn-info";
					    btn.value = "Update";
//					    btn.onclick = function(){console.log(obj3[i].reim_id);};
					    btn.addEventListener("click", function(){ 
					    	myScript(obj8[i].reim_id);
					    	});
					    cell8.appendChild(btn);
								
					};
			}
		};
		
		if(searchReim != "All"){
		xhr8.open("POST","http://localhost:8080/expenseReimbursementSystem/viewRequestsFromUser");
		xhr8.setRequestHeader("Content-Type", "application/json");
		xhr8.send(myjsonreim);
		}
		else{
		xhr8.open("POST","http://localhost:8080/expenseReimbursementSystem/viewAllReimbursements");
		xhr8.send();	
		}
}

/*-------------------------This is where the function start for view employees-----------------------*/
function viewEmployee(){
	let search = document.getElementsByName("searchBar")[0].value;
	//console.log(search);
	
	let objsearch = {
			emp_UserName : search
	};
	let myjson = JSON.stringify(objsearch);
		
	if(search != "All"){
		var xhr5 = new XMLHttpRequest();
			xhr5.onreadystatechange = function(){
				if((xhr5.readyState == 4) && (xhr5.status == 200)){
					let obj5 = JSON.parse(xhr5.responseText);
					let empTable = document.getElementById("employeeTable")
					
					
					while(empTable.rows.length > 0){
						empTable.deleteRow(0);
					};

					if(obj5.emp_FirstName == null){
						let row = empTable.insertRow(0);
						let cell1 = row.insertCell(0);
						cell1.innerHTML = "No Data To Display. Please Try again";
					}
					else{
		
						let row = empTable.insertRow(0);
					
						let cell1 = row.insertCell(0);
						let cell2 = row.insertCell(1);
						let cell3 = row.insertCell(2);
						let cell4 = row.insertCell(3);
						let cell5 = row.insertCell(4);
							
						cell1.innerHTML = obj5.emp_id;
						cell2.innerHTML = obj5.emp_FirstName;
						cell3.innerHTML = obj5.emp_LastName;
						cell4.innerHTML = obj5.emp_Email;
						cell5.innerHTML = obj5.emp_UserName;
					}	
				}
			};
			xhr5.open("POST","http://localhost:8080/expenseReimbursementSystem/viewEmployee");
			xhr5.setRequestHeader("Content-Type", "application/json");
			xhr5.send(myjson);
	}
	else{
		let empTable = document.getElementById("employeeTable")
		
		while(empTable.rows.length > 0){
			empTable.deleteRow(0);
		};
		
		var xhr7 = new XMLHttpRequest();
		xhr7.onreadystatechange = function(){
			if((xhr7.readyState == 4) && (xhr7.status == 200)){
				
				let obj7 = JSON.parse(xhr7.responseText);

				
				for(let i = 0; i < obj7.length; i++){
				let row = employeeTable.insertRow(0+i);
		
				let cell1 = row.insertCell(0);
				let cell2 = row.insertCell(1);
				let cell3 = row.insertCell(2);
				let cell4 = row.insertCell(3);
				let cell5 = row.insertCell(4);
				
				cell1.innerHTML = obj7[i].emp_id;
				cell2.innerHTML = obj7[i].emp_FirstName;
				cell3.innerHTML = obj7[i].emp_LastName;
				cell4.innerHTML = obj7[i].emp_Email;
				cell5.innerHTML = obj7[i].emp_UserName;
				}
			}
		};
		xhr7.open("POST","http://localhost:8080/expenseReimbursementSystem/viewAllEmployees");
		xhr7.send();
	}
}


/*-------------------------This is where the function start for edit-----------------------*/
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

/*-------------------------This is the function for creating a new employee-----------------------*/
function insertEmployee(){
	
	let username = document.getElementById("newUserName");
	let firstname = document.getElementById("newFirstName");
	let lastname = document.getElementById("newLastName");
	let email = document.getElementById("newEmail");
	let password = document.getElementById("newPassword");
	let role = document.getElementById("newRole");
	
//	console.log(username.value);
//	console.log(firstname.value);
//	console.log(lastname.value);
//	console.log(email.value);
//	console.log(password.value);
//	console.log(role.value)
	
	objNewUser={
		emp_FirstName: firstname.value,
		emp_LastName:  lastname.value,
		emp_UserName:  username.value,
		emp_Password:  password.value,
		emp_Email:	   email.value,
		role_id:	   role.value
	}
	
	newuserjson = JSON.stringify(objNewUser);

	if(firstname.value == "" || lastname.value == "" || username.value == "" || password.value == "" || email.value == "" || role.value == ""){
		document.getElementById("fieldsEmptyAdd").hidden = false;
	}
	else{
	var xhr9 = new XMLHttpRequest();
	xhr9.onreadystatechange = function(){
		if((xhr9.readyState == 4) && (xhr9.status == 200)){
			document.getElementById("fieldsEmptyAdd").hidden = true;
			
			let obj9 = JSON.stringify(xhr9.responseText);
			let empTable = document.getElementById("employeeTable")
			clearTable(empTable);
			viewAllEmployees();
			 $('#employeeModalCenter').modal('hide');

		}
	};
	xhr9.open("POST","http://localhost:8080/expenseReimbursementSystem/insertEmployee");
	xhr9.setRequestHeader("Content-Type", "application/json");
	xhr9.send(newuserjson);
	
	}
}


/*-------------------------This is the function for inserting status-----------------------*/
function insertStatus(){
	let statusType = document.getElementsByName("typeStatus")[0];
	let statusDescr = document.getElementsByName("descrStatus")[0];
//	console.log(statusType.value);
//	console.log(statusDescr.value);
	
	objInsertStatus={
			st_descr : statusDescr.value,
			st_type	 : statusType.value
	}
	
	newInserStatus = JSON.stringify(objInsertStatus);
	
	if(statusDescr.value == ""){
		document.getElementById("statusEmptyAdd").hidden = false;
	}
	else{
	document.getElementById("statusEmptyAdd").hidden = true;
	var xhr10 = new XMLHttpRequest();
	xhr10.onreadystatechange = function(){
		if((xhr10.readyState == 4) && (xhr10.status == 200)){
			selectAllStatus();
			 $('#statusModalCenter').modal('hide');
		}
	};
	xhr10.open("POST","http://localhost:8080/expenseReimbursementSystem/insertStatus");
	xhr10.setRequestHeader("Content-Type", "application/json");
	xhr10.send(newInserStatus);
	
	window.location.href="http://localhost:8080/expenseReimbursementSystem/html/mgrHome.html";
	}
}

/*-------------------------This is the function for checking  username for unique-----------------------*/
function checkUserName(){
	let p1 = document.getElementById("newUserName");
	
	objusername ={
			emp_UserName : p1.value
	}
	
	chkUser = JSON.stringify(objusername);
	
	var xhr11 = new XMLHttpRequest();
	b = xhr11.onreadystatechange = function(){
		if((xhr11.readyState == 4) && (xhr11.status == 200)){
			let obj11 = JSON.parse(xhr11.responseText);

			if(obj11 == true ){
//				console.log(obj11);
				document.getElementById("userExist2").hidden = false;
			}
			else{
				document.getElementById("userExist2").hidden = true;
				insertEmployee();
			}
			
		}
	};
	xhr11.open("POST","http://localhost:8080/expenseReimbursementSystem/checkUserName");
	xhr11.setRequestHeader("Content-Type", "application/json");
	xhr11.send(chkUser);
	
}


/*-------------------------This is the function for updating user info-----------------------*/

function editUser(){

	let firstName = document.getElementsByName("firstName")[0];
	let lastName = document.getElementsByName("lastName")[0];
	let email = document.getElementsByName("email")[0];
	let userName = document.getElementsByName("userName")[0];
	
//	console.log(firstName.value);
//	console.log(lastName.value);
//	console.log(email.value);
//	console.log(userName.value);
	
	if(firstName.value == "" || lastName.value == "" || email.value == "" || userName.value == "" ){
		document.getElementById("fieldsEmpty").hidden = false;
	}
	else{
		document.getElementById("fieldsEmpty").hidden = true;
	let objusr ={
		emp_FirstName:firstName.value,
		emp_LastName:lastName.value,
		emp_Email:email.value,
		emp_UserName:userName.value
	}
	
	let myjsonuser = JSON.stringify(objusr);
		

	var xhr12 = new XMLHttpRequest();
	xhr12.onreadystatechange = function(){
		if((xhr12.readyState == 4) && (xhr12.status == 200)){
				let pass = JSON.parse(xhr12.responseText);

			}
		};
		xhr12.open("POST","http://localhost:8080/expenseReimbursementSystem/updateUserInfo");
		xhr12.setRequestHeader("Content-Type", "application/json");
		xhr12.send(myjsonuser);
		
		window.location.href="http://localhost:8080/expenseReimbursementSystem/html/index.html";
	}
}


/*--------------------------Clear table function ---------------------*/
function clearTable(table) {	
	while(table.rows.length > 0){
		table.deleteRow(0);
	}
  }

/*--------------------------Filter Table ---------------------*/
function filter(){	
	 // Declare variables
    var input, filter, table, tr, td, i;
    input = document.getElementById("inlineFormCustomSelect");
//    console.log(input.value);
    filter = input.value;
    table = document.getElementById("reimbursementTable");
    tr = table.getElementsByTagName("tr");
    
    if(filter.toLowerCase() === 'all'){
    	viewAllReimbursements();
    }
    else{
	    // Loop through all table rows, and hide those who don't match the search query
	    for (i = 0; i < tr.length; i++) {
	      td = tr[i].getElementsByTagName("td")[1];
	      if (td) {
	        if (td.innerHTML.indexOf(filter) > -1) {
	          tr[i].style.display = "";
	        } else {
	          tr[i].style.display = "none";
	        }
	      }
	    }
    }
}


/*--------------------------Filter Table ---------------------*/
function close(){
	document.getElementById("fieldsEmpty").hidden = true;
	document.getElementById("userExist2").hidden = true;
	 $('#employeeModalCenter').modal('hide');
	 
	 document.getElementById("fieldsEmptyAdd").hidden = true;
	 $('#employeeModalCenter').modal('hide');
	 let username = document.getElementById("newUserName");
		document.getElementById("newFirstName").value="";
		document.getElementById("newLastName").value="";
		document.getElementById("newEmail").value="";
		document.getElementById("newPassword").value="";
		document.getElementById("newRole").value="";
	 
	 document.getElementById("reimApproval").hidden = true;
	 $('#reimModalCenter').modal('hide');
		document.getElementsByName("description")[0].value = "";
		document.getElementsByName("amounts")[0].value = "";
		document.getElementsByName("types")[0].value = "";
	 
	 document.getElementById("statusEmptyAdd").hidden = true;
	 $('#statusModalCenter').modal('hide');
}
