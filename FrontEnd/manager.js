const url = "http://localhost:7002/"

if (sessionStorage.getItem("userSession") == null){
    window.location.replace(url + "login.html");
} else{
    let user = JSON.parse(sessionStorage.getItem("userSession"));
    if(user.userRoleId != 2){
        window.location.replace(url + "employee.html");
    }
}  



let statusUpdate = document.querySelector('#select1');
let submitBtn = document.getElementById("updateStatusBtn");
let reimTable = document.getElementById("reimTabl");
let reimBtn = document.getElementById("getRequests");
let loginBtn = document.getElementById("loginBtn");
let filterUpdate = document.getElementById("#select2");
let getListBtn = document.getElementById("getList");
let id_notify = document.getElementById("id_verify");


submitBtn.addEventListener("click", setStatus);
reimBtn.addEventListener("click", getAllRequests);
logoutBtn.addEventListener("click", logoutFunc);
getListBtn.addEventListener("click", getAllRequestsById);

async function logoutFunc(){
    
  
    let response = await fetch(
      url+"logout",
      {
        method : "POST",   
        credentials: "include"
      }
    );
  
    if(response.status===200){
        sessionStorage.clear();
        window.location.replace(url + "login.html");
    }else{
      console.log("Logout unsuccessful. Returned status code of:"+response.status);
    }
}

async function setStatus() {
   
    // visual feeback of status update choice
    output = statusUpdate.value;
    document.querySelector('.output').textContent = output;

    let statusId_value = 1;

    if (document.querySelector('#select1').value == "denied"){
      statusId_value = 2;
    } else if(document.querySelector('#select1').value == "approved") {
      statusId_value = 3;
    } 

    console.log(document.querySelector('#select1').value);

    // setting status update choice as value for put request
    let status = {
        reimId: parseInt(document.getElementById("reimId").value),

        statusId: statusId_value
    } 

    console.log(id_notify);
    console.log(status.reimId);

    if (isNaN(status.reimId) || status.reimId < 0){
      console.log("plz enter valid id");
      id_notify.style.fontSize = "12px";
      id_notify.style.color = "red";
      id_notify.innerHTML = "Please enter a valid reimbursement Id";
      id_notify.style.fontWeight = "bold";  
      
      return;
    }

    if(status.reimId != ""){
      id_notify.innerHTML = "";
    }

    let response = await fetch(url + "reimbursements", {
        method:"PUT",
        body:JSON.stringify(status),
        credentials:"include"
    })

    if(response.status === 200){
        getAllRequests(); 
        console.log("Request status updated successfully!");
    }else {
        console.log("Request update didn't go through!");
    }
}

// get request for reimbursement list
async function getAllRequests(){
    

    let response = await fetch(url + "reimbursements", {
      credentials:"include"

    });
  
    if(response.status === 200){

      let requests = await response.json();
      populateRequests(requests);
    
    } else{

      console.log("Can't get the reimbursement list ??? ugh !!!")

    }
}

// get request for reimbursement list by id
async function getAllRequestsById(){
  let statusId_value = 1;
  
  if (document.querySelector('#select2').value == "denied"){
      statusId_value = 2;
  } else if(document.querySelector('#select2').value == "approved") {
      statusId_value = 3;
  } 

  let response = await fetch(url + "reimbursements/" + statusId_value , {
    credentials:"include"
  });

  if(response.status === 200){

    let requests = await response.json();
    populateRequests(requests);
  
  } else{

    console.log("Can't get the reimbursement list ??? ugh !!!")

  }
}

// create rows and fill them with data from reimbursement request
function populateRequests(requests){

    reimTable.innerHTML ="";

    for(let request of requests){

      let row = document.createElement("tr");

      for(let data in request){
        // We're skipping the information we don't need to show
        if(data != "authorId" && data != "resolverId" && data != "statusId" && data != "typeId"){            
          // data = Key
          // request[data] = Value
          let request_data = request[data];
          // We need to format the dates that come from our request
          let td = document.createElement("td");
          if (data == "dateSubmitted" || data == "dateResolved") {       
            request_data = formatDate(request_data);
          }
          if (data == "receipt" && request_data != null) {
            let img = new Image();
            img.src = request_data;
            img.height = 150;
            img.width = 150;
            td.appendChild(img);
            console.log("RECEIPT = " + request_data);
          }
          if (data == "amount"){
            request_data = "$" + request_data;
          }
          if (data == "status" || data == "type") {
            let btn = styleStatus(request_data);            
            td.appendChild(btn);
          } else {
            if (data != "receipt") {
              td.innerText = request_data; 
            }
          }
          
          row.appendChild(td); 
        }
      }
      reimTable.appendChild(row); 
    }
}

function styleStatus(statusData) {
  let btn = document.createElement("input");
  btn.type = "button";
  btn.className = "btn";
  btn.value = statusData;
  btn.style.borderRadius = "25px";
  btn.style.height = "30px";
  btn.style.width = "80px";
  btn.style.fontSize = "10px";
  btn.style.fontWeight = "bold";
  btn.disabled = true;

  switch(statusData){
    case "APPROVED":
      btn.style.backgroundColor = "#42f587";
      break;
    case "DENIED":
      btn.style.backgroundColor = "#f54242";
      break;
    case "PENDING":
      btn.style.backgroundColor = "#f5d442";
      break;
    case "OTHER":
      btn.style.backgroundColor = "gray";
      break;
    case "TRAVEL":
      btn.style.backgroundColor = "#99ccff";
      break;
    case "FOOD":
      btn.style.backgroundColor = "#cc6699";
      break;
    case "LODGING":
      btn.style.backgroundColor = "#669999";
      break;
    default:
      btn.style.backgroundColor = "blue";
      break;
  }
  return btn;
}

function formatDate(dateData){
  // Our Result
  var result = "";
  // Ensures data is not null
  if (dateData != null) {
    // Creates new Date from data
    var d = new Date(dateData);
    // Uses ternary operator
    var ampm = (d.getHours() >= 12) ? "PM" : "AM";    
    // Creates our formatting
    var m = d.getMinutes();
    if (m < 10) {
      m = "0" + m;
    }
    result = ((d.getMonth() + 1) + "/"  + d.getDate() + "/" + d.getFullYear() +
      " " + ((d.getHours() + 11) % 12 + 1) + ":" + m + " " + ampm);
  }
  // Returns the result if data is null string will be empty
  return result;
}