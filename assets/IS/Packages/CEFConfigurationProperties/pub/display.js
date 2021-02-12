

var row = "even";
var other = "odd";
var swap = "";

function swapRows()
{
	swap = row;
	row = other;
	other = swap;
}

function writeTD (c, properties)
{
	document.write("<TD CLASS=\"");
	document.write(row);
	document.write(c);
	document.write("\" ");
	document.write(properties);
	document.write(" >");
	
	return true;
}

