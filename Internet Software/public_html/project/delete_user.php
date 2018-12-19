<?php
$successMsg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	require "db_connect.php";
	$sql = 'DELETE FROM USER WHERE USER_ID = ' . $_POST["user_id"] . ';';
  if ($conn->query($sql) === TRUE) {
    //echo "New record created successfully";
		$successMsg = "User deleted!";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
	require "db_disconnect.php";
}

?>
