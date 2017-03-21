function delim(latlon) {           
   var value=latlon;
   $.ajax({
     url:'UploadServlet',
     type:'POST',
     data:{"delimiter" : parseInt(value)},
   });
}


