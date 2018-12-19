<?php

require "db_connect.php";

$sql = "SELECT CAMERA_ID, URL, TITLE, L_NAME, F_NAME, M_INITIAL FROM CAMERA INNER JOIN USER ON CAMERA.USER_ID = USER.USER_ID ORDER BY CAMERA_ID DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<tr><td><b>Title:</b> ' . $row["TITLE"] . '<br>';
			echo '<b>URL:</b> ' . $row["URL"] . '<br>';
			echo '<b>User Name: </b>' . $row["F_NAME"] . " " . $row["M_INITIAL"] . " " . $row["L_NAME"] . '<br>';
      echo '<form method="post" action="admin.php#cameraList">';
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
