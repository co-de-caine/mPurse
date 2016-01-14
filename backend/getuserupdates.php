<?php
require_once 'include/DB_Functions.php'
$db = new DB_Functions();

//json array response
$response = array('error' => , 0);

if(isset($_POST['phoneno'])) {

	$phoneno = $_POST['phoneno'];
	// get the user details
    $user = $db->getUserDetails($phoneno);
    if($user != NULL) {
    	$response["error"] = 0;
        $response["user"] = $user;
		if($db->updateFlag($phoneno))
			echo json_encode($response);
		else {
			$response["error"] = 1;
			$response["error_msg"] = 'Error in DB updation';
			echo json_encode($response)
		}
    }
    else {
    	$response["error"] = 2;
    	$response["error_msg"] = 'User not found';
    	echo json_encode($response);
    }
}
?>