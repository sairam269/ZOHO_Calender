<html>
<head>
	<style type="text/css">
		.center {
		  margin: auto;
		  width: 60%;
		  border: 3px solid green;
		  padding: 10px;
		}
		.form{
			padding-left: 60px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<p id="error"></p>
	<div class="center">
	    <form class="form">
	    	<h3>Date :</h3> <input type="date" id="date"/><br/><br/>
	    	<h3>Time :</h3> <input type="time" id="time"/><br/><br/>
	    	<h3>Repeat Every Year ? :</h3> <select multiple id="repeateYear">
			  <option value="Yes">Yes</option>
			  <option value="No">No</option>
			</select><br/><br/>
			<h3>Repeat on Days :</h3>
			<input type="checkbox" value="1" id="mon"/>Mon
			<input type="checkbox" value="2" id="tue"/>Tue
			<input type="checkbox" value="3" id="wed"/>Wed
			<input type="checkbox" value="4" id="thurs"/>Thurs
			<input type="checkbox" value="5" id="fri"/>Fri
			<input type="checkbox" value="6" id="sat"/>Sat
			<input type="checkbox" value="7" id="sun"/>Sun
			<br/><br/>
			<h3>Repeat on Months :</h3>
			<br/><br/>
			<input type="checkbox" value="1" id="jan"/>Jan
			<input type="checkbox" value="2" id="feb"/>Feb
			<input type="checkbox" value="3" id="march"/>March
			<input type="checkbox" value="4" id="april"/>April
			<input type="checkbox" value="5" id="may"/>May
			<input type="checkbox" value="6" id="june"/>June
			<br/>
			<input type="checkbox" value="7" id="july"/>July
			<input type="checkbox" value="8" id="aug"/>Aug
			<input type="checkbox" value="9" id="sept"/>Sept
			<input type="checkbox" value="10" id="oct"/>Oct
			<input type="checkbox" value="11" id="nov"/>Nov
			<input type="checkbox" value="12" id="dec"/>Dec
			<br/><br/>
			<h3>Message :</h3> 
			<br/>
			<textarea id="message" rows="10" cols="30"></textarea>
			<br/><br/>
	    	<button type="submit">Submit form</button>
	    </form>
    </div>
   
    
    <script>
    function sendJSON(){ 
        
        let result = document.querySelector('#error'); 
        let message = document.querySelector('#message'); 
        let targetDate = document.querySelector('#date'); 
        let targetTime = document.querySelector('#time'); 
        let repeateYear = document.querySelector('#repeateYear'); 
           
        // Creating a XHR object 
        let xhr = new XMLHttpRequest(); 
        let url = "./webapi/alarm"; 
    
        // open a connection 
        xhr.open("POST", url, true); 

        // Set the request header i.e. which type of content you are sending 
        xhr.setRequestHeader("Content-Type", "application/json"); 

        // Create a state change callback 
        xhr.onreadystatechange = function () { 
            if (xhr.readyState === 4 && xhr.status === 200) { 

                // Print received data from server 
                result.innerHTML = this.responseText; 

            } 
        }; 

        // Converting JSON data to string 
        var data = JSON.stringify({
            "alarmID": 0,
            "message": message.value,
            "repeateYear": repeateYear.value=="Yes" || repeateYear.value=="yes"?"1":"0",
            "target": targetDate.value+" "+targetTime.value
        }); 

        // Sending data with the request 
        xhr.send(data); 
    } 
	   window.onload=function(){
	    const form = document.getElementsByClassName('form')[0];
	    if(form){
	    	form.addEventListener('submit', sendJSON);
	    }
	   }
	    
	    
    </script>
</body>

</html>