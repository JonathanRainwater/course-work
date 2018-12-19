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
  Reports-View page for viewing all incident reports that have been uploaded by all users.
-->

<?php require "bootstrap_a.php"; ?>

<meta name="author" content="Jonathan Rainwater">
<title>Reports View</title>

<?php require "bootstrap_b.php"; ?>

<link rel="stylesheet" type="text/css" href="css/main.css">

</head>


<body role="document" style="background-color:#222;">

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
        <h4 class="intro">Below is a list of all incident reports that have been uploaded by members of this site.</h4>
      </div>
    </div>


    <!-- Accordion for displaying reports -->
    <div class="panel-group" id="accordion">

			<?php
				include "reports_get.php";
			?>

  	</div> 

  </div> <!-- End container -->

</body>
</html>
