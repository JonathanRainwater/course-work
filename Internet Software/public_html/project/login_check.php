<?php

	require "db_connect.php";

	$IP = $_SERVER["REMOTE_ADDR"];

	$intervalExpr = "5";
	$intervalUnit = "minute";
	$allowedFails = "5";
	$redirectUser;

	$sql = "SELECT COUNT(LOGIN.LOGIN_TIME) AS FAILED_LOGINS FROM LOGIN WHERE (CURRENT_TIMESTAMP(6) - INTERVAL $intervalExpr $intervalUnit) < LOGIN_TIME AND IP = '$IP' AND VALID = FALSE;";
	$result = $conn->query($sql);
	if ($result->num_rows != 1) {
   	echo "SELECT STATEMENT ERROR";
 	} else {
		$row = $result->fetch_assoc();
		if ($row["FAILED_LOGINS"] >= $allowedFails) {
			// Redirect the user to an error page.
			$redirectUser = true;
			header("location: login_error.php");
		}
	}
	require "db_disconnect.php";
	if ($redirectUser) {
		exit;
	}

?>
