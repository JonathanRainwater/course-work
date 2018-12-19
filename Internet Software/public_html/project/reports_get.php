<?php

require "db_connect.php";

$sql = "SELECT REPORT.UPLOAD_DATE, USER.F_NAME, USER.M_INITIAL, USER.L_NAME, REPORT.BODY FROM REPORT INNER JOIN USER ON REPORT.USER_ID = USER.USER_ID ORDER BY REPORT.UPLOAD_DATE DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	$i = 0; // Loop counter.
	while($row = $result->fetch_assoc()) {

		echo '<div class="panel panel-default">';
		echo '	<div class="panel-heading">';
		echo '		<h4 class="panel-title">';
		echo '			<a data-toggle="collapse" data-parent="#accordion" href="#collapse' . ($i + 1) . '">';
		echo 					$row["UPLOAD_DATE"] . ' from ' . $row["F_NAME"] . ' ' . $row["M_INITIAL"] . '. ' . $row["L_NAME"];
		echo '			</a>';
		echo '		</h4>';
		echo '	</div>';
		echo '	<div id="collapse' . ($i + 1) . '" class="panel-collapse collapse';
			if ($i == 0) {echo ' in';} // To make the first panel open by default.
			echo '	">';

		echo '		<div class="panel-body">' . $row["BODY"] . '</div>';
		echo '	</div>';
		echo '</div>';

		$i++;
	}
} else {
	echo "0 results";
}

require "db_disconnect.php";

?> 
