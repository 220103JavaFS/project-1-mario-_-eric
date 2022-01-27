function menuDropdown() {
    document.getElementById("myDropdown").classList.toggle("show");
}

var div = document.getElementsByClassName('dropdown-content');

for(var  i =0;i<div.length;i++){

    for(var  j =0;j<div[i].children.length;j++){

        div[i].children[j].addEventListener('click',function(){

            this.parentNode.previousElementSibling.innerHTML = this.innerHTML;
        })
    }
}


  
  // Close the dropdown if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
          openDropdown.classList.remove('show');
        }
      }
    }
  }