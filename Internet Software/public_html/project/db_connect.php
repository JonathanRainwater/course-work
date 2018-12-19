<?php
$db_servername = "localhost";
$db_username = "c2375a25";
$db_password = ""; // Redacted!
$dbname = "c2375a25proj";

// Create connection
$conn = new mysqli($db_servername, $db_username, $db_password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

?> 
