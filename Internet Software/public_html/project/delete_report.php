<?php
$successMsg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	require "db_connect.php";
	$sql = 'DELETE FROM REPORT WHERE REPORT_ID = ' . $_POST["report_id"] . ';';
  if ($conn->query($sql) === TRUE) {
    //echo "Record deleted";
		$successMsg = "Report deleted!";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
	require "db_disconnect.php";
}

?>
