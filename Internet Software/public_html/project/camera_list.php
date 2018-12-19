<?php

require "db_connect.php";

$sql = "SELECT CAMERA_ID, URL, TITLE FROM CAMERA WHERE USER_ID = " . $_SESSION["user_id"] . " ORDER BY CAMERA_ID DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<tr><td><b>Title:</b> ' . $row["TITLE"] . '<br>';
			echo '<b>URL:</b> ' . $row["URL"] . '<br>';
			echo '<form method="post" action="my_profile.php#cameraForm">';
        echo '<input type="hidden" name="formNumber" value="3">';
        echo '<input type="hidden" name="camera_id" value="' . $row["CAMERA_ID"] . '">';
        echo '<input type="submit" value="Delete Camera">';
      echo '</form></td></tr>';
	}
} else {
	echo "0 results";
}

require "db_disconnect.php";

?> 
