<?php
	session_start();
	// Check if the user is already logged in.
	if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true){
    header("location: camera_view.php");
    exit;
	}
	// Check if user has too many failed logins. If so, redirect to error page.
	require "login_check.php";
?>

<!DOCTYPE html>
<html lang="en"><head>

<!-- Project for c2375 by Jonathan Rainwater
  Index page for login
-->

<?php require "bootstrap_a.php"; ?>

<meta name="description" content="Home page for the local neighborhood watch">
<meta name="author" content="Jonathan Rainwater">
<title>Login</title>

<?php require "bootstrap_b.php"; ?>

<link rel="stylesheet" type="text/css" href="css/main.css">
 
<style>
  h3 {
    margin-top:5px;
    margin-bottom:5px;
  }
</style>

</head>


<body role="document" style="background-color:#222;">

	<?php
		if ($_SERVER["REQUEST_METHOD"] == "POST") {
			include "validate_login.php";
			$formNumber = $_POST["formNumber"];
		}
	?>

  <div class="container">

    <!-- Introduction info for site -->
    <br>
    <div class="panel panel-default">
    <div class="panel-heading"><h3>Local Neighborhood Watch Home Page</h3></div>
      <div class="panel-body">This is the home page for the local neighborhood watch.<br>
         Only authorized local residents are allowed access to this site.<br>
         If you are a local resident that wishes to access this site, contact your neighborhood watch administrator directly to apply for access credentials.</div>
    </div>


    <!-- Form for email and password entry -->
		<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
      <div class="form-group">
        <label>Enter your name and password to login:</label>
        <br>
        <label>First Name:</label>
        <input type="text" class="form-control inline" style="max-width:200px;" name="firstName" maxlength="50">
        &nbsp;<label>Middle Initial:</label>
        <input type="text" class="form-control inline" style="max-width:40px;" name="middleInitial" maxlength="1">
        &nbsp;<label>Last Name:</label>
        <input type="text" class="form-control inline" style="max-width:200px;" name="lastName" maxlength="50">
        <br>
        <br>
        <label>Password:</label>
        <input type="password" class="form-control inline" style="max-width:200px;" name="password" maxlength="32">
      </div>
        <span class="alert-danger"><?php if ($formNumber == "1" && $showDefaultErr) {echo $defaultErr . "<br><br>";}?></span>
        <span class="alert-danger"><?php if ($formNumber == "1" && $failedLoginMsgA != "") {echo $failedLoginMsgA . "<br>";}?></span>
        <span class="alert-danger"><?php if ($formNumber == "1" && $failedLoginMsgB != "") {echo $failedLoginMsgB . "<br><br>";}?></span>
      <input type="hidden" name="formNumber" value="1">
      <button type="submit" class="btn btn-default">Login</button>
      <button type="reset" class="btn btn-default">Reset</button>
    </form>
    <br>

  </div> <!-- End container -->

</body>
</html>
