let arrow = document.querySelectorAll(".fa-chevron-down"); /* 드롭다운할 때 여기도 아이콘명 변경해야함 */
for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e)=>{
 let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
 arrowParent.classList.toggle("showMenu");
  });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".fa-bars"); /* 햄버거클릭할 때 여기도 아이콘명 변경해야함 */
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", ()=>{
  sidebar.classList.toggle("close");
});
