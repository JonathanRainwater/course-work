<?php
	// Initialize the session
	session_start();
	// Check if the user is already logged in.
	if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true){
    header("location: camera_view.php");
    exit;
	}
?>

<!DOCTYPE html>
<html lang="en"><head>

<!-- Project for c2375 by Jonathan Rainwater
  login_error page for users that fail too many login attempts.
-->

<?php require "bootstrap_a.php"; ?>

<meta name="author" content="Jonathan Rainwater">
<title>Login Error</title>

<?php require "bootstrap_b.php"; ?>

</head>


<body role="document" style="background-color:#222;">
	<h2 style="color:#fff;">Too many failed login attempts!<br>
	Try again later.</h2>

</body>
</html>
