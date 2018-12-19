<?php
// define variables and set to empty values
$reportErr = "";
$report = "";
$successMsg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  if (empty($_POST["report"])) {
		$reportIsValid = false;
    $reportErr = "You can't post an empty report!";
  } else {
		$reportIsValid = true;
    $report = test_report_input($_POST["report"]);
  }

}

function test_report_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

if ($reportIsValid) {
	require "db_connect.php";
	$report = str_replace("'", "''", $report); // Escape single quotes in SQL statement.  

	$sql = "INSERT INTO REPORT (BODY, USER_ID) VALUES ('$report', " . $_SESSION["user_id"] . ");";

  if ($conn->query($sql) === TRUE) {
    //echo "New record created successfully";
		$successMsg = "New report created!";
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
	require "db_disconnect.php";
}

?>
