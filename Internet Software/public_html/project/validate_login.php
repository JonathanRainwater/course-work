<?php
// define variables and set to empty values
$defaultErr = "";
$firstName = $middleInitial = $lastName = $password = $hashedPassword = "";
$namesAreValid;
$passwordIsValid;
$loginSuccessful;
$showDefaultErr = false;

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  $firstName = test_camera_input($_POST["firstName"]);
  $middleInitial = test_camera_input($_POST["middleInitial"]);
  $lastName = test_camera_input($_POST["lastName"]);

  if ( empty($_POST["firstName"]) || empty($_POST["middleInitial"]) || empty($_POST["lastName"]) ) {
		// One of the name fields was empty.
		$namesAreValid = false;
		$showDefaultErr = true;
		setDefaultErr("Name fields cannot be empty!");
		
  } else {
		// None of the name fields were empty.
    // Check if each name consists of only letters.
    if (!preg_match("/^[a-zA-Z]*$/",$firstName) || !preg_match("/^[a-zA-Z]*$/",$middleInitial) ||
			!preg_match("/^[a-zA-Z]*$/",$lastName) ) {
			// At least one of the name fields had a character that was not a letter.
			$showDefaultErr = true;
    } else {
			// All name fields contained only letters.
			$namesAreValid = true;
		}
  }

  if (empty($_POST["password"])) {
		// The password field was empty.
		$passwordIsValid = false;
		$showDefaultErr = true;
		setDefaultErr("Password field cannot be empty!");
	} else {
		// The password field was not empty.
    $password = test_camera_input($_POST["password"]);
    // Check if the password has correct number of characters.
    if ( strlen($password) < 8 || strlen($password) > 32 ) {
			// Number of password characters is out of bounds.
			$passwordIsValid = false;
			$showDefaultErr = true;
		} else {
			// Number of password characters is correct.
			$passwordIsValid = true;
		}
  }

}

function test_camera_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

function setDefaultErr($err = "The username and/or password does not match.") {
	global $defaultErr;
	$defaultErr = $err;
}

if ($namesAreValid && $passwordIsValid) {
	// All inputs were validated.
	require "db_connect.php";

	$sql = "SELECT USER_ID, L_NAME, F_NAME, M_INITIAL, PASSWORD, IS_ADMIN FROM USER WHERE L_NAME = '$lastName' AND F_NAME = '$firstName' AND M_INITIAL = '$middleInitial'";
	$result = $conn->query($sql);
	if ($result->num_rows == 1) {
		// The entered name was found in the database.
		$row = $result->fetch_assoc();
		if ( password_verify($password, $row["PASSWORD"]) ) {
			// The entered password matches the stored hash for the entered user's password.
			$loginSuccessful = true;
			// Start session and store login values.
			session_start();
			$_SESSION["loggedin"] = true;
			$_SESSION["user_id"] = $row["USER_ID"];
			$_SESSION["username"] = $row["F_NAME"] . " " . $row["M_INITIAL"] . " " . $row["L_NAME"];
			$_SESSION["is_admin"] = ($row["IS_ADMIN"] == true) ? true : false;
			header("location: camera_view.php");
		} else {
			// The entered password did NOT match the stored hash for the entered user's password.
			$loginSuccessful = false;
			$showDefaultErr = true;
		}
	} else {
		// The entered name was NOT found in the database, or there are somehow duplicate entries.
		$loginSuccessful = false;
		$showDefaultErr = true;
	}

	require "db_disconnect.php";
}

require "login_tracker.php";

if ($loginSuccessful) {
	exit;
}

if ($showDefaultErr && $defaultErr == "") {
	setDefaultErr();
}

?>
