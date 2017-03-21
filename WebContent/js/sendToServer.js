function postToServer(latlon) {           
   var value=latlon;
   $.ajax({
     url:'ClusterSelectionServlet',
     type:'POST',
     data:{"cluster_id" : parseInt(value)},
   });
}


