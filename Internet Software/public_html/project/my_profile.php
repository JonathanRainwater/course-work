<?php
	// Initialize the session
	session_start();
	// Check if the user is already logged in.
	if(! isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] !== true){
    header("location: index.php");
    exit;
	}
?>

<!DOCTYPE html>
<html lang="en"><head>

<!-- Project for c2375 by Jonathan Rainwater
  My-Profile page for viewing all cameras and reports that have been uploaded by the current user, deleting those cameras and reports if desired, and adding new cameras and reports.
-->

<?php require "bootstrap_a.php"; ?>

<meta name="author" content="Jonathan Rainwater">
<title>My Profile</title>

<?php require "bootstrap_b.php"; ?>

<link rel="stylesheet" type="text/css" href="css/main.css">
 
</head>


<body role="document" style="background-color:#222;">

<?php
	$formNumber = $_POST["formNumber"];
	switch ($formNumber) {
		case "1":
			include "validate_camera.php";
			break;
		case "2":
			include "validate_report.php";
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
        <h3 class="intro"><?php echo $_SESSION["username"]; ?>'s Profile</h3>
      </div>
      <div class="panel-body">
        <h4 class="intro">Here you can view all cameras that you have added to the site, add new cameras, and delete old ones.<br>
          You can also view all incident reports that you have added to the site, and new reports, and delete old ones.</h4>
      </div>
    </div>


    <!-- Form for entering a new camera -->
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]) . "#cameraForm";?>" id="cameraForm">
      <div class="form-group">
        <label>To add a new camera, enter the url for the mjpg stream of your network camera:</label>
        <input type="text" class="form-control" placeholder="e.g. 111.222.333.444:55/mjpg/video.mjpg (for Axis cameras)" name="cameraURL" maxlength="255">
				<span class="alert-danger"><?php if ($formNumber == "1") {echo $cameraURLErr;}?></span>
        <br>
        <label>Enter a title for your camera (optional):</label>
        <input type="text" class="form-control" placeholder="A name for the camera that you will recognize" name="cameraName" maxlength="25">
				<span class="alert-danger"><?php if ($formNumber == "1") {echo $cameraNameErr;}?></span>
      </div>
			<input type="hidden" name="formNumber" value="1">
      <button type="submit" class="btn btn-default">Add Camera</button>
    </form>


    <!-- List of user's currently added cameras with links to remove them -->
		<br>
		<label>All my cameras:</label>
		<span class="alert-success"><?php if ($formNumber == "1" || $formNumber == "3") {echo $successMsg;}?></span>
    <div class="my-lists" style="height:200px;">
      <table class="table table-striped table-condensed">
        <tbody>
					<?php
						include "camera_list.php";
					?>
        </tbody>
      </table>
    </div>

    <hr>

    <!-- Form for entering a new report -->
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]) . "#reportForm";?>" id="reportForm">
      <div class="form-group">
        <label>To add a new incident report, fill out the following form:</label>
        <textarea name="report" class="form-control" rows="5" maxlength="65000"></textarea>
				<span class="alert-danger"><?php  if ($formNumber == "2") {echo $reportErr;}?></span>
      </div> 
			<input type="hidden" name="formNumber" value="2">
      <button type="submit" class="btn btn-default">Add Report</button>
    </form>


    <!-- List of user's currently added incident reports with links to remove them -->
		<br>
		<label>All my reports:</label>
		<span class="alert-success"><?php if ($formNumber == "2" || $formNumber == "4") {echo $successMsg;}?></span>
    <div class="my-lists" style="max-width:400px;">
      <table class="table table-striped table-condensed">
        <tbody>
					<?php
						include "reports_list.php";
					?>
        </tbody>
      </table>
    </div>
    <br>


  </div> <!-- End container -->

</body>
</html>
