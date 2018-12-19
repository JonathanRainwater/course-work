<?php
// define variables and set to empty values
$namesErr = $passwordErr = $userRadioErr = "";
$firstName = $middleInitial = $lastName = $password = $hashedPassword = $userRadio = "";
$namesAreValid;
$passwordIsValid;
$userRadioIsValid;
$successMsg = $tmpSuccessMsg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  if ( empty($_POST["firstName"]) || empty($_POST["middleInitial"]) || empty($_POST["lastName"]) ) {
		// One of the name fields was empty.
		$namesAreValid = false;
    $namesErr = "Name fields cannot be empty!";
  } else {
		// None of the name fields were empty.
    $firstName = test_camera_input($_POST["firstName"]);
    $middleInitial = test_camera_input($_POST["middleInitial"]);
    $lastName = test_camera_input($_POST["lastName"]);
    // Check if each name consists of only letters.
    if (!preg_match("/^[a-zA-Z]*$/",$firstName) || !preg_match("/^[a-zA-Z]*$/",$middleInitial) ||
			!preg_match("/^[a-zA-Z]*$/",$lastName) ) {
			// At least one of the name fields had a character that was not a letter.
      $namesErr = "Only letters are allowed for names!";
    } else {
			// All name fields contained only letters.
			$namesAreValid = true;
		}
  }

  if (empty($_POST["password"])) {
		// The password field was empty.
		$passwordIsValid = false;
		$passwordErr = "Password field cannot be empty!";
	} else {
		// The password field was not empty.
    $password = test_camera_input($_POST["password"]);
    // Check if the password has correct number of characters.
    if ( strlen($password) < 8 || strlen($password) > 64 ) {
			// Number of password characters is out of bounds.
			$passwordIsValid = false;
      $passwordErr = "Password mst have between 8 and 32 characters!";
		} else {
			// Number of password characters is correct.
			$passwordIsValid = true;
			$hashedPassword = password_hash($password, PASSWORD_DEFAULT);
		}
  }

  if (empty($_POST["userRadio"])) {
		// A userRadio value was not selected.
		$userRadioIsValid = false;
		$userRadioErr = "An option must be selected!";
	} else {
		// A userRadio value was selected.
		$userRadioIsValid = true;
		$userRadio = $_POST["userRadio"];
	}

}

function test_camera_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

if ($namesAreValid && $passwordIsValid && $userRadioIsValid) {
	// All inputs were validated.
	require "db_connect.php";

	$sql = "SELECT USER_ID FROM USER WHERE L_NAME = '$lastName' AND F_NAME = '$firstName' AND M_INITIAL = '$middleInitial'";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		// The name the user entered already exists in the database.
		if ($userRadio == "addUser") {
			// The user chose to add a new user.
			$userRadioErr = "Cannot add user since user already exists!";
		}	elseif ($userRadio == "changePassword") {
			// The user chose to change an existing user's password.
			$sql = "UPDATE USER SET PASSWORD = '$hashedPassword' WHERE L_NAME = '$lastName' AND F_NAME = '$firstName' AND M_INITIAL = '$middleInitial'";
			$tmpSuccessMsg = $firstName . " " . $middleInitial . " " . $lastName . "'s password was changed!";
		}
	} else {
		// The name that the user entered does NOT exist in the database.
		if ($userRadio == "addUser") {
			// The user chose to add a new user.
			$sql = "INSERT INTO USER (L_NAME, F_NAME, M_INITIAL, PASSWORD) VALUES ('$lastName', '$firstName', '$middleInitial', '$hashedPassword')";
		$tmpSuccessMsg = "New user added!";
		}	elseif ($userRadio == "changePassword") {
			// The user chose to change an existing user's password.
			$userRadioErr = "Cannot change password for a user that does not exist!";
		}
		
	}

	if ($conn->query($sql) === TRUE) {
 		//echo "New record created successfully";
		$successMsg = $tmpSuccessMsg;
	} else {
 		echo "Error: " . $sql . "<br>" . $conn->error;
	}

require "db_disconnect.php";
}

?>
