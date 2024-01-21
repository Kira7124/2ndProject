const getJSON = function(url,callback){
    const xhr = new XMLHttpRequest();
    xhr.open('GET',url,true);
    xhr.responseType='json';
    xhr.onload = function(){
        const status = xhr.status;
        if(status === 200){
           callback(null,xhr.response); 
        }else{
           callback(status,xhr.response); 
        }
    };
    xhr.send();
};

getJSON('http://api.openweathermap.org/data/2.5/weather?q=busan&appid=d712984be391d2ae9e74b8bb92b17786&units=metric',
    function(err,data){
        if(err != null){
            alert('죄송합니다 오류발생했습니다!');
        } else{
            loadWeather(data);
        }

    });


function loadWeather(data){
    let location = document.querySelector('.location');
    let currentTime = document.querySelector('.current-time');
    let currentTemp = document.querySelector('.current-temp');
	let WeatherLike = document.querySelector('.weather-like');

    let date = new Date();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hours = date.getHours();
    let minutes = date.getMinutes();
	

	
	
    location.innerHTML = data.name;
    
    let weatherMain = data.weather[0].main;
	let additionalText = "";
	let iconHTML = ""; 
		
		if (weatherMain === "Clear") {
		    additionalText = "맑음";
		    iconHTML = '<i class="fa-solid fa-sun"></i>';
		    
		} else if (weatherMain === "Clouds") {
		    additionalText = "흐림";
		    iconHTML = '<i class="fa-solid fa-cloud"></i>';
		    
		} else if (weatherMain === "Rain") {
		    additionalText = "비";
		    iconHTML = '<i class="fa-solid fa-umbrella"></i>';
		    
		} 
    
    
    currentTemp.innerHTML = `${data.main.temp}℃`;
    currentTime.innerHTML = `${month}월 ${day}일 ${hours}:${minutes}`;
    WeatherLike.innerHTML = `${weatherMain} - ${additionalText} ${iconHTML}`;
   	document.querySelector('.location').innerHTML = `${data.name} ${iconHTML}`;
   
	
	
	

}

