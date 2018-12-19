<?php
	// Initialize the session
	session_start();
	// Check if the user is already logged in and if user is admin.
	if( (! isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] !== true) || $_SESSION["is_admin"] == false){
    header("location: index.php");
    exit;
	}
?>

<!DOCTYPE html>
<html lang="en"><head>

<!-- Project for c2375 by Jonathan Rainwater
  Admin page for adding/deleting users, changing user passwords, and deleting any camera or report entry from any user.
-->

<?php require "bootstrap_a.php"; ?>

<meta name="author" content="Jonathan Rainwater">
<title>Admin</title>

<?php require "bootstrap_b.php"; ?>

<link rel="stylesheet" type="text/css" href="css/main.css">
 
</head>


<body role="document" style="background-color:#222;">

<?php
	$formNumber = $_POST["formNumber"];
  switch ($formNumber) {
    case "1":
      include "validate_user.php";
      break;
    case "2":
			include "delete_user.php";
      break;
    case "3":
			include "delete_camera.php";
      break;
    case "4":
			include "delete_report.php";
      break;
	}
?>

  <div class="container">


    <br>
    <!-- Navigation bar -->
    <?php
			$thisPage = $_SERVER["PHP_SELF"];
      include "navbar.php";
    ?>

    <!-- Introduction info for page -->
    <br>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="intro">Admin Controls</h3>
      </div>
      <div class="panel-body">
        <h4 class="intro">Here you can add new users, delete current users, change user passwords, or delete any user's camera or report entry.</h4>
      </div>
    </div>


    <!-- Form for creating new users or changing existing user's passwords -->
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"] . "#userForm");?>" id="userForm">
      <div class="form-group">
				<label>Add a new user or change an existing user's password:</label>
				<br>
        <label>First Name:</label>
        <input type="text" class="form-control inline" style="max-width:200px;" name="firstName" maxlength="50">
        &nbsp;<label>Middle Initial:</label>
        <input type="text" class="form-control inline" style="max-width:40px;" name="middleInitial" maxlength="1">
        &nbsp;<label>Last Name:</label>
        <input type="text" class="form-control inline" style="max-width:200px;" name="lastName" maxlength="50">
				<span class="alert-danger"><?php if ($formNumber == "1") {echo $namesErr;}?></span>
        <br>
        <br>
        <label>Password:</label>
        <input type="password" class="form-control inline" style="max-width:200px;" name="password" maxlength="32">
				<span class="alert-danger"><?php if ($formNumber == "1") {echo $passwordErr;}?></span>
				<br>
				<br>
				<label class="radio-inline">
					<input type="radio" name="userRadio" value="addUser">Add as a new user
				</label>
				<br>
				<label class="radio-inline">
					<input type="radio" name="userRadio" value="changePassword">Change existing user's password
				</label>
				<span class="alert-danger"><?php if ($formNumber == "1") {echo $userRadioErr;}?></span>
      </div>
			<input type="hidden" name="formNumber" value="1">
      <button type="submit" class="btn btn-default">Submit</button>
      <button type="reset" class="btn btn-default">Reset</button>
    </form>
		<br>

    <!-- List of all users currently registered with the site with links to remove them -->
		<label style="margin-bottom:0px">All users that are currenty registered with the site:</label>
		<span class="alert-success"><?php if ($formNumber == "1" || $formNumber == "2") {echo $successMsg;}?></span>
		<br>
		<label class="sm-norm-label">(Deleting a user will delete all their cameras and reports.)</label>
    <div class="my-lists" style="height:200px;">
      <table class="table table-striped table-condensed">
        <tbody>
					<?php
						include "user_list.php";
					?>
        </tbody>
      </table>
    </div>

    <hr>

    <!-- List of all users' cameras with links to remove them -->
		<label id="cameraList">All cameras that are currenty registered with the site:</label>
		<span class="alert-success"><?php if ($formNumber == "3") {echo $successMsg;}?></span>
    <div class="my-lists" style="height:200px;">
      <table class="table table-striped table-condensed">
        <tbody>
					<?php
						include "camera_list_admin.php";
					?>
        </tbody>
      </table>
    </div>

    <hr>

    <!-- List of all users' incident reports with links to remove them -->
		<label id="reportList">All incident reports that are currenty registered with the site:</label>
		<span class="alert-success"><?php if ($formNumber == "4") {echo $successMsg;}?></span>
    <div class="my-lists" style="max-width:400px;">
      <table class="table table-striped table-condensed">
        <tbody>
					<?php
						include "reports_list_admin.php";
					?>
        </tbody>
      </table>
    </div>
    <br>


  </div> <!-- End container -->

</body>
</html>
