let submitBtn = document.getElementById("submitRequest");
let reimbTable = document.getElementById("reimTable");
let reimBtn = document.getElementById("getRequests");
let loginBtn = document.getElementById("loginBtn");

const userName = "flodev";
const passWord = "password";
const url = "http://localhost:7002/"

submitBtn.addEventListener("click", sendRequest);
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

async function sendRequest() {
   

    // sending reimbursement request
    let status = {
        
    amount:document.getElementById("amount").value,
    description:document.getElementById("description").value,
    typeId:document.querySelector('#select1').value

    } 

    let response = await fetch(url + "reimbursements", {
        method:"POST",
        body:JSON.stringify(sendRequest),
        credentials:"include"
    })

    if(response.status === 201){

        document.querySelector('.response').textContent = "Success!"; // figure out if these work

        getAllRequests(); 
        console.log("Reimbursement request sent successfully!");
        
    }else {

        document.querySelector('.response').textContent = "Failure :("; // figure out how to make these work

        console.log("Request didn't go through!");
        failure();
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

    reimbTable.innerHTML ="";

    for(let request of requests){

      let row = document.createElement("tr");

      for(let data in request){

        let td = document.createElement("td");
        td.innerText = request[data];
        row.appendChild(td); 

      }

      reimbTable.appendChild(row); // fix this!!!!
    }
}