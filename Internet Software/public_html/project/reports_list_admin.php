<?php

require "db_connect.php";

$sql = "SELECT REPORT.REPORT_ID, REPORT.UPLOAD_DATE, USER.F_NAME, USER.M_INITIAL, USER.L_NAME, REPORT.BODY FROM REPORT INNER JOIN USER ON REPORT.USER_ID = USER.USER_ID ORDER BY REPORT.UPLOAD_DATE DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<tr><td><b>Uplodaed:</b> ' . $row["UPLOAD_DATE"] . '<br>';
			echo '<b>User Name: </b>' . $row["F_NAME"] . " " .  $row["M_INITIAL"] . " " .  $row["L_NAME"] . '<br>';
      echo '<form method="post" action="admin.php#reportList">';
        echo '<input type="hidden" name="formNumber" value="4">';
        echo '<input type="hidden" name="report_id" value="' . $row["REPORT_ID"] . '">';
        echo '<input type="submit" value="Delete Report">';
      echo '</form></td></tr>';

	}
} else {
	echo "0 results";
}

require "db_disconnect.php";

?> 
