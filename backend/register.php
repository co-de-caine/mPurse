<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => 0);

if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['phoneno']) && isset($_POST['passHash'])) { 
 
    // receiving the post params
    $name = $_POST['name'];
    $passHash = $_POST['passHash'];
    $email = $_POST['email'];
    $phoneno = $_POST['phoneno'];

    if($db->isUserExisted($phoneno) == true) {
    	$response["error"] = 1;
		$response["error_msg"] = "User already exists!";
		echo json_encode($response);
    }
    else {
    	if($db->addUser($name, $passHash, $phoneno, $email)) {
			 $user  = $db->getUserDetails($phoneno);
			 if($user != NULL) {
			 	 $response["error"] = 0;
			 	 $response["error_msg"] = "Register successfully done! ";
			 	 echo json_encode($response);
			 }
			 else {
			$response["error"] = 2;
            $response["error_msg"] = "User not registered! DB Error!";
            echo json_encode($response);
			 }
		}
	}
}
else {
    // required post params is missing
    $response["error"] = 3;
    $response["error_msg"] = "Required parameters User Details is missing!";
    echo json_encode($response);
}
?>