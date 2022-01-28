let statusUpdate = document.querySelector('#select1');
let submitBtn = document.getElementById("updateStatusBtn");
let reimTable = document.getElementById("reimTabl");
let reimBtn = document.getElementById("getRequests");
let loginBtn = document.getElementById("loginBtn");

const userName = "marioj";
const passWord = "password";
const url = "http://localhost:7002/"

submitBtn.addEventListener("click", setStatus);
reimBtn.addEventListener("click", getAllRequests);
loginBtn.addEventListener("click", loginFunc);

async function loginFunc(){
    let user = {
      username: userName,
      password: passWord
    }
  
    let response = await fetch(
      url+"login",
      {
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"
      }
    );
  
    if(response.status===200){
      loginBtn.innerText = ""; 
    }else{
      console.log("Login unsuccessful. Returned status code of:"+response.status);
    }
}

async function setStatus() {
   
    // visual feeback of status update choice
    output = statusUpdate.value;
    document.querySelector('.output').textContent = output;

    // setting status update choice as value for put request
    let status = {
        reimId: document.getElementById("reimId"),
        statusId: statusUpdate.value
    } 


    let response = await fetch(url + "reimbursements", {
        method:"PUT",
        body:JSON.stringify(setStatus),
        credentials:"include"
    })

    if(response.status === 201){
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

// create rows and fill them with data from reimbursement request
function populateRequests(requests){

    reimTable.innerHTML ="";

    for(let request of requests){

      let row = document.createElement("tr");

      for(let data in request){

        let td = document.createElement("td");
        td.innerText = request[data];
        row.appendChild(td); 

      }

      homeTable.appendChild(row);
    }
}