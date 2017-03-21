function recluster() {
	$.ajax({
		url:'ReClusterWithSameSettingsServlet',
		type:'POST',
		
		success:function() {
			location.reload();
		}
	});
}