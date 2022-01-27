let statusUpdate = document.querySelector('#select1');
let submitBtn = document.getElementById("updateStatusBtn");

const url = "http://localhost:8080/"

submitBtn.addEventListener("click", setStatus);

async function setStatus() {
   
    // visual feeback of status update choice
    output = statusUpdate.value;
    document.querySelector('.output').textContent = output;

    // setting status update choice as value for put request
    let status = {
        statusId: statusUpdate.value
    } 

    let response = await fetch(url + "reimbursements", {
        method:"PUT",
        body:JSON.stringify(setStatus),
        credentials:"include"
    })

    if(response.status === 201){
        getAllRequests(); // still need to write out this function
        console.log("Request status updated successfully!");
    }else {
        console.log("Request update didn't go through!");
    }
}