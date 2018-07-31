var show = true;

function toggle() {
	show = !show;
	var left_navbar = document.getElementById("left-navbar");
	var toggleObj = document.getElementById("toggle");
	if(show == true) {
		left_navbar.style.display = "none";
		toggleObj.innerHTML = "展开";
	} else {
		left_navbar.style.display = "inline-block";
		toggleObj.innerHTML = "收起";
	}
}