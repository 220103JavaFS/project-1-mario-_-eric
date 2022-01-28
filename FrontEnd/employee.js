const url = "http://localhost:7002/"

if (sessionStorage.getItem("userSession") == null){
  window.location.replace(url + "login.html");;
}

let submitBtn = document.getElementById("submitRequest");
let reimbTable = document.getElementById("reimTable");
let reimBtn = document.getElementById("getRequests");
let logoutBtn = document.getElementById("logoutBtn");

submitBtn.addEventListener("click", sendRequest);
reimBtn.addEventListener("click", getAllRequests);
logoutBtn.addEventListener("click", logoutFunc);


async function logoutFunc(){
    
  
    let response = await fetch(
      url+"logout",
      {
        method : "POST",   
        credentials: "include"
      }
    );
  
    if(response.status===200){
      
    }else{
      console.log("Login unsuccessful. Returned status code of:"+response.status);
    }
}

async function sendRequest() {
   
    let typeId_value = 1;

    if (document.querySelector('#select1').value == "Travel"){
      typeId_value = 2;
    } else if(document.querySelector('#select1').value == "Food") {
      typeId_value = 3;
    } else if (document.querySelector('#select1').value == "Otherg") {
      typeId_value = 4;
    }

    // sending reimbursement request
    console.log(document.querySelector('#select1').value);
    let status = {
        
      amount:document.getElementById("amount").value,
      description:document.getElementById("description").value,
      typeId:typeId_value
    } 

    let response = await fetch(url + "reimbursements", {
        method:"POST",
        body:JSON.stringify(status),
        credentials:"include"
    })

    if(response.status === 201){

        document.querySelector('.response').textContent = "Success!"; // figure out if these work

        getAllRequests(); 
        console.log("Reimbursement request sent successfully!");
        
    }else {

        document.querySelector('.response').textContent = "Failure :("; // figure out how to make these work

        console.log("Request didn't go through!");
        //failure();
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
      // We're skipping the information we don't need to show
      if(data != "authorId" && data != "resolverId" && data != "receipt" && data != "statusId" && data != "typeId"){            
        // data = Key
        // request[data] = Value
        let request_data = request[data];
        // We need to format the dates that come from our request
        if (data == "dateSubmitted" || data == "dateResolved") {       
          request_data = formatDate(request_data);
        }
        let td = document.createElement("td");
        td.innerText = request_data;
        row.appendChild(td); 
      }
    }
    reimTable.appendChild(row); 
  }
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
    result = ((d.getMonth() + 1) + "/"  + d.getDate() + "/" + d.getFullYear() +
      " " + ((d.getHours() + 11) % 12 + 1) + ":" + d.getMinutes() + " " + ampm);
  }
  // Returns the result
  return result;
}