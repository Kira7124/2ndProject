/**
 * 
 */

function getMaterials(sopCode, salesQuantity){

    	
    	$.ajax({
    		type:"GET",
    		url:"/restInstruction/getMaterials",
    		dataType:"json",
    		contentType: "application/json; charset=UTF-8",
    		data :{'salesQuantity' : salesQuantity, 'sopCode' : sopCode},
    		success:function(data){
    			removeTable();
    			showMaterials(data);
    		}
    	});
    };

    
function removeTable() {
     // 이전 테이블 삭제
    var sopCodeH3 = document.getElementById("sopCode");
    if (sopCodeH3) {
    	sopCodeH3.remove();
    }
	
    var productCodeH4 = document.getElementById("productCode");
    if (productCodeH4) {
    	productCodeH4.remove();
    }
    
    var materialsTable = document.getElementById("materialsTable");
    if (materialsTable) {
    	materialsTable.remove();
    }
    var requestButton = document.getElementById("requestButton");
    if (requestButton) {
    	requestButton.remove();
    }
}
    
//이거 될지는 모르겠음
function showMaterials(data){
	var bottomContent = document.getElementById('bottomContent');
	var detailSopCode = data.sopCode;
	var salesQuantity = data.salesQuantity;
	
	var h3 = document.createElement("h3");
	h3.id = "sopCode";
	h3.textContent="[수주번호 : " +data.sopCode+"]";
	bottomContent.appendChild(h3);
	
	var h4 = document.createElement("h4");
	h4.id = "productCode";
	h4.textContent="제품번호 : " +data.productCode;
	bottomContent.appendChild(h4);
	
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
	thead.classList.add("table-success");
	
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
	
	if(data.status==='empty'){
		var requestButton = document.createElement("button");
		requestButton.id = "requestButton";
		requestButton.textContent ="자재요청";
		requestButton.classList.add("btn");
		requestButton.classList.add("btn-secondary");
		bottomContent.appendChild(requestButton);
	}
	
	if (requestButton) {
	    requestButton.onclick = function() {
	        requestMaterials(detailSopCode, salesQuantity);
	    };
	}
	
}

function requestMaterials(sopCode, salesQuantity){
	
	$.ajax({
		type:"POST",
		url:"/restInstruction/requestMaterials",
		dataType:"json",
		contentType: "application/json; charset=UTF-8",
		dataType :"text",
		data : JSON.stringify({'salesQuantity' : salesQuantity, 'sopCode' : sopCode}),
		success:function(data){
			Swal.fire({
				title: '자재 요청이 완료되었습니다.',
				text: '자재상황 요청상황은 화면에 표시됩니다.',
				icon: 'success'
			}).then((result) => {
		        // SweetAlert이 닫힐 때 호출되는 콜백
		        if (result.isConfirmed) {
		            location.reload();
		        }
		    });
		},
		error:function(data){
			console.log(data)
			alert("마 실패했다");
		}
	});
	
}