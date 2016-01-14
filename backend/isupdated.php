<?php
require_once 'include/DB_Functions.php'
$db = new DB_Functions();

if(isset($_POST['phoneno'])) {
	$phoneno = $_POST['phoneno'];
	  // get the user details
    $user = $db->getUserDetails($phoneno);
    if($user != NULL) {
    	$flag = $user["update_flag"];
    	if($flag == 1)
    		echo 'true';
    	else
    		echo 'false';
    }
    else
    	echo 'Not Found';
}

?>