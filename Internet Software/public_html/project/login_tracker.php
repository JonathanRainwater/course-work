<?php

	require "db_connect.php";

	// Record login attempt to database.
	$IP = $_SERVER["REMOTE_ADDR"];
	$valid = ($loginSuccessful) ? "true" : "false";
	$sql = "INSERT INTO LOGIN (F_NAME, M_INITIAL, L_NAME, IP, VALID) VALUES ('$firstName', '$middleInitial', '$lastName', '$IP', $valid);";
  if ($conn->query($sql) === TRUE) {
    //echo "login logged";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
		echo "<br>" . $IP;
  }


	$intervalExpr = "5";
	$intervalUnit = "minute";
	$allowedFails = "5";
	$failedLoginMsgA = "";
	$failedLoginMsgB = "";
	$redirectUser;

	if (! $loginSuccessful) {
		// The login attempt failed. Warn or block the user based on number of failed attempts.
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
			} else {
				// Warn the user.
    		//echo "SELECT STATEMENT WORKED " . $row["FAILED_LOGINS"] . "<br>";
				$failedLoginMsgA = "You have " . $row["FAILED_LOGINS"] . " failed logins within the past $intervalExpr $intervalUnit" . "s.";
				$failedLoginMsgB = "You have ". ($allowedFails - $row["FAILED_LOGINS"]) . " attempts remaining.";
			}
			
		}
	}

	require "db_disconnect.php";

	if ($redirectUser) {
		exit;
	}

?>
