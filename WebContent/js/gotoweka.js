function gotoweka() {
	$.ajax({
		url:'GenerateArff',
		type:'GET',
		
		success:function() {
			window.location.replace("weka.jsp")
		}
	});
}