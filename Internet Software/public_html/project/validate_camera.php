<?php
// define variables and set to empty values
$cameraURLErr = $cameraNameErr = "";
$cameraURL = $cameraName = "";
$successMsg = "";
$cameraURLIsValid;
$cameraNameIsEmpty;



if ($_SERVER["REQUEST_METHOD"] == "POST") {

  if (empty($_POST["cameraURL"])) {
		$cameraURLIsValid = false;
    $cameraURLErr = "The camera's URL is required";
  } else {
    $cameraURL = test_camera_input($_POST["cameraURL"]);
    // check if URL address syntax is valid
    if (!preg_match("/\b(?:(?:https?|ftp):\/\/|www\.)[-a-z0-9+&@#\/%?=~_|!:,.;]*[-a-z0-9+&@#\/%=~_|]/i",$cameraURL)) {
      $cameraURLErr = "Invalid URL";
    } else {
			$cameraURLIsValid = true;
		}
  }
 
	$cameraNameIsValid = true;
  if (empty($_POST["cameraName"])) {
		$cameraNameIsEmpty = true;
	} else {
		$cameraNameIsEmpty = false;
    $cameraName = test_camera_input($_POST["cameraName"]);
    // check if cameraName contains only letter, digits, or whitespace.
    if (! preg_match("/^[a-zA-Z0-9 ]*$/",$cameraName)) {
			$cameraNameIsValid = false;
      $cameraNameErr = "Only letters, digits, or whitespaces are allowed";
		}
  }

}

function test_camera_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

if ($cameraURLIsValid && $cameraNameIsValid) {
	// All inputs are valid, so enter the new record into the database.
	$successMsg = "New camera created!";	
	require "db_connect.php";
	if ($cameraNameIsEmpty) {
		$sql = "INSERT INTO CAMERA (URL, USER_ID) VALUES ('$cameraURL', " . $_SESSION["user_id"] . ")";
  	//echo "url and name are valid and camera is null";
	} else {
		$sql = "INSERT INTO CAMERA (URL, TITLE, USER_ID) VALUES ('$cameraURL', '$cameraName', " . $_SESSION["user_id"] . ")";
  	//echo "url and name are valid and camera is NOT null";
	}

	if ($conn->query($sql) === TRUE) {
   	//echo "New record created successfully";
	} else {
   	echo "Error: " . $sql . "<br>" . $conn->error;
	}
	require "db_disconnect.php";

}

?>
