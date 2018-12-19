<?php

require "db_connect.php";

$sql = "SELECT REPORT.REPORT_ID, REPORT.UPLOAD_DATE, USER.F_NAME, USER.M_INITIAL, USER.L_NAME, REPORT.BODY FROM REPORT INNER JOIN USER ON REPORT.USER_ID = USER.USER_ID WHERE USER.USER_ID = " . $_SESSION["user_id"] . " ORDER BY REPORT.UPLOAD_DATE DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<tr><td><b>Uplodaed:</b> ' . $row["UPLOAD_DATE"];
			echo '<form method="post" action="my_profile.php#reportForm">';
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
