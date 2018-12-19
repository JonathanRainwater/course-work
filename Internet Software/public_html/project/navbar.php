<?php
	session_start();

$cameraActive = $reportsActive = $profileActive = $adminActive = "";

	if (!!strpos($thisPage, "camera_view.php")) {
		$cameraActive = ' class="active"';
	} elseif (!!strpos($thisPage, "reports_view.php")) {
		$reportsActive = ' class="active"';
	} elseif (!!strpos($thisPage, "my_profile.php")) {
		$profileActive = ' class="active"';
	} elseif (!!strpos($thisPage, "admin.php")) {
		$adminActive = ' class="active"';
	}

    echo '<ul class="nav nav-pills nav-justified">';
      echo '<li' . $cameraActive . '>';
        echo '<a href="camera_view.php" data-toggle="tooltip" title="Shows all cameras that are currently registered with this site.">Camera View</a>';
      echo '</li>';
      echo '<li' . $reportsActive . '>';
        echo '<a href="reports_view.php" data-toggle="tooltip" title="Shows all users\' incident reports.">Reports View</a>';
      echo '</li>';
      echo '<li' . $profileActive . '>';
        echo '<a href="my_profile.php" data-toggle="tooltip" title="Add new cameras or incident reports, or delete your existing cameras or incedent reports.">My Profile</a>';
      echo '</li>';
			if ($_SESSION["is_admin"] == true) {
				echo '<li' . $adminActive . '><a href="admin.php" data-toggle="tooltip" title="Administrator controls.">Admin Controls</a></li>';
			}
      echo '<li><a href="logout.php">Log Out</a></li>';
    echo '</ul>';
?> 
