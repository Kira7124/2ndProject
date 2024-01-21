/**
 * 
 */
var dateListener = document.getElementById("startDate");

dateListener.addEventListener("change", function(){
	var startDate = document.getElementById("startDate").value;
	getEnableLine(startDate);
});

function getEnableLine(searchDate){
	
	$.ajax({
		type:"POST",
		url:"/RestProductionLine/getEnableLine",
		data :{'searchDate' : searchDate},
		success:function(data){
            let selectLine = $("#selectLine"); 
            selectLine.empty();
            for (let i = 0; i < data.length; i++) {
                let option = $("<option>").val(data[i]).text(data[i]);
                selectLine.append(option);
            }
		},
		error:function(){
			alert("그냥 실패");
		}
		
	});
	
};
