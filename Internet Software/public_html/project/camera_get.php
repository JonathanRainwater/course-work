<?php

require "db_connect.php";

$sql = "SELECT URL, TITLE FROM CAMERA";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<div class="camera-spacer">';
		echo '<div class="camera">';
		echo '	<a href="' . $row["URL"] . '">';
		echo '		<img src="' . $row["URL"] . '" alt="Camera">';
		echo '	</a>';
		echo '	' . $row["TITLE"];
		echo '</div>';
		echo '</div>';

	}
} else {
	echo "0 results";
}

require "db_disconnect.php";
?> 
