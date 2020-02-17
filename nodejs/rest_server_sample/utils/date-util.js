
function now( delim='' ){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
    return yyyy+delim+ mm+delim+dd;    
}

function year(){
    var today = new Date();    
    var yyyy = today.getFullYear();    
    return yyyy;    
}

function month(){
    var today = new Date();    
    var mm = today.getMonth()+1;  
    if(mm<10) {
        mm='0'+mm
    } 
    return mm;    
}

function day(){
    var today = new Date();    
    var dd = today.getDate();  
    if(dd<10) {
        dd='0'+dd
    }
    return dd;    
}

module.exports = { now, year, month, day }