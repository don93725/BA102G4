var count = 0;
$("#add").click(plus);
$("#delete").click(minus);

function plus() {
	count++;
	if (count > 3) {
		swal("一次最多新增四堂課", "At most four times", "error");
		return;
	}
	$("#addtime")
			.append(
					'<span id="sp'
							+ count
							+ '"'
							+ '>報名截止日期:<input type="text" id="start'+count+'" name="deadline" class="deadline" required="required"><br>課程日期:<input type="text" id="end'+count+'" name="date" class="date"> &nbsp&nbsp時段:<select name="time'+count+'" class="time'+count+'" id=""><option value="1">08:00-09:30</option><option value="2">10:00-11:30</option><option value="3">13:00-14:30</option><option value="4">15:00-16:30</option><option value="5">18:00-19:30</option><option value="6">20:00-21:30</option></select><br>開課人數:<input type="text" name="class_num" required="required"> &nbsp&nbsp 人數上限:<input type="text" name="limit" required="required"><br>價錢:<input type="text" name="price" required="required">'
							+ $("#place").html() +'<br><br></span>');
	$("#count").val(count);
	addDatepicker(count);
}

function minus() {
	if (count >= 1) {
		var sp = "#sp" + (count);
		$(sp).remove();
		count--;
		$("#count").val(count);
	}
}

function addDatepicker(c){
	var end = '#end' + c;
	var start = '#start' + c;
	$(end).datepicker();
	  $(start).datepicker({
	    minDate: +3,
	    onSelect: function (dat, inst) {
	      $(end).datepicker('option', 'minDate', dat);
	    }
	  });
	$.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' }); 
}