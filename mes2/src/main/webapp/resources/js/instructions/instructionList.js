/**
 * 
 */

function updateProgressing(isCode){
	console.log(isCode);
	
	$.ajax({
		type:"GET",
		url:"/restInstruction/updateProgressing",
		contentType :"application/json; charset=UTF-8",
		dataType:"text",
		data:{"isCode" :isCode},
		//asyn:false,
		success : function(data){
			Swal.fire({
				text: "생산을 시작합니다.",
				confirmButtonColor: "#577D71",
				icon: "success"
			}).then(function(){
					location.reload();
				}
			);
		},
		error : function(){
			Swal.fire({
				text: "생산을 시작에 실패했습니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			}).then(function() {
					location.reload();
				}
			);
		}
	});
};

//나중에 생각합시다
function getinstructionsList(startDate, EndDate,searchName , state){
	
	alert("(js)함수가 실행되었군요? 넘어온 값 : " + state);
	
	
	$.ajax({
		type:"POST",
		url:"/restInstruction/getInstructions",
		dataType:"json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify({'salesQuantity' : salesQuantity, 'sopCode' : sopCode}),
		success:function(data){
			removeTable();
			showMaterials(data);
		}
	});
};


function removeTable() {
 // 이전 테이블 삭제
var materialsTable = document.getElementById("materialsTable");
if (materialsTable) {
	materialsTable.remove();
}
}

//이거 될지는 모르겠음
function showMaterials(data){
var bottomContent = document.getElementById('bottomContent');

var table = document.createElement("table");
table.id = "materialsTable";
table.classList.add("table");
table.classList.add("table-hover");
var thead = table.createTHead();
var headerRow = thead.insertRow(0);
var header1 = document.createElement("th");
var header2 = document.createElement("th");
var header3 = document.createElement("th");
var header4 = document.createElement("th");
var header5 = document.createElement("th");
header1.textContent ="원자재 번호";
header2.textContent ="원자재 명";
header3.textContent ="소요량";
header4.textContent ="단위";
header5.textContent ="총 수량";
headerRow.appendChild(header1);
headerRow.appendChild(header2);
headerRow.appendChild(header3);
headerRow.appendChild(header4);
headerRow.appendChild(header5);

var tbody = document.createElement("tbody");
var count =0;
data.materialList.forEach(function (material) {
    var row = tbody.insertRow(count);
    var td1 = row.insertCell(0);
    var td2 = row.insertCell(1);
    var td3 = row.insertCell(2);
    var td4 = row.insertCell(3);
    var td5 = row.insertCell(4);
    td1.textContent=material.materialCode;
    td2.textContent=material.materialCode;
    td3.textContent=material.amount;
    td4.textContent=material.amountUnit;
    td5.textContent=material.totalAmount;
    
    count++;
});
table.appendChild(thead);
table.appendChild(tbody);

bottomContent.appendChild(table);

}