<?php
$successMsg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	require "db_connect.php";
	$sql = 'DELETE FROM CAMERA WHERE CAMERA_ID = ' . $_POST["camera_id"] . ';';
  if ($conn->query($sql) === TRUE) {
    //echo "Record deleted";
		$successMsg = "Camera deleted!";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
	require "db_disconnect.php";
}

?>
