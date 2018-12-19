<?php

require "db_connect.php";

$sql = "SELECT USER_ID, L_NAME, F_NAME, M_INITIAL, IS_ADMIN FROM USER ORDER BY USER_ID DESC;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo '<tr><td><b>User ID:</b> ' . $row["USER_ID"] . '<br>';
			echo '<b>Name:</b> ' . $row["F_NAME"] . " " . $row["M_INITIAL"] . " " . $row["L_NAME"] . '<br>';
			if ($row["IS_ADMIN"] == true) {
				echo '<b>Permissions: </b>Admin</td></tr>';
			} else {
				echo '<b>Permissions: </b>User<br>';
      	echo '<form method="post" action="admin.php#userForm">';
        	echo '<input type="hidden" name="formNumber" value="2">';
        	echo '<input type="hidden" name="user_id" value="' . $row["USER_ID"] . '">';
        	echo '<input type="submit" value="Delete User">';
      	echo '</form></td></tr>';

//				echo '<a>Remove User</a></td></tr>';
			}
	}
} else {
	echo "0 results";
}

require "db_disconnect.php";

?> 
