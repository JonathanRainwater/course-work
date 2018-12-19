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
  Camera-View page for viewing all cameras that are currently registered with the site.
-->

<?php require "bootstrap_a.php"; ?>

<meta name="author" content="Jonathan Rainwater">
<title>Camera View</title>

<?php require "bootstrap_b.php"; ?>

<link rel="stylesheet" type="text/css" href="css/main.css">
 
<style>
  #cameras-all img {
    color:#fff;
    max-width:296px;
		//width:296px;
  }
  .camera {
    color:#aaa;
    text-align:center;
    max-width:300px;
		//width:300px;
		//height:250px;
    border-style:solid;
    border-width:2px;
    border-color:#444;
    //float:left;
    margin:4px;
  }
	.camera-spacer {
		width:308px;
		height:250px;
		float:left;
	}
</style>

</head>


<body role="document" style="background-color:#222;">

  <div class="container">

    <br>
    <!-- Navigation bar -->
		<?php
			$thisPage = $_SERVER["PHP_SELF"];
			include "navbar.php";
		?>

  </div> <!-- End container -->


  <div class="container-fluid">

    <!-- Section to display cameras -->
    <br>
    <div id="cameras-all">
    <!-- Test streams found at https://www.insecam.org -->

			<?php
				include "camera_get.php";
			?>

    </div> <!-- End cameras-all -->

  </div> <!-- End container-fluid -->

<br>

</body>
</html>
