<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => 0);


if (isset($_POST['username']) && isset($_POST['passHash'])) { 
 
    // receiving the post params
    $username = $_POST['username'];
    $passHash = $_POST['passHash'];
 
    // get the user details
    $user = $db->getUserDetails($username);

    if ($user != NULL) {
        // user is found , now check for the correct login credentials 
        $salt = $user["salt"];
        if ( $db->checkPassHash( $salt, $passHash )  == $user["password"]) {
            $response["error"] = 0;
            $response["user"]["uuid"] = $user["uuid"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["phoneno"] = $user["phoneno"];
            $response["user"]["wallet"] = $user["wallet"];
            echo json_encode($response);
        }
        else {
            $passwordRecv = $db->checkPassHash( $salt, $passHash );
            // wrong login credentials
            $response["error"] = 1;
            $response["error_msg"] = "Login credentials wrong.";
            $response["passHash"] = $passHash;
            $response["salt"] = $salt;
            $response["passwordRecv"] = $passwordRecv;
            
            
            echo json_encode($response);
        }
       
    } else {
        // user is not found
        $response["error"] = 2;
        $response["error_msg"] = "Username doesn't exists.";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = 3;
    $response["error_msg"] = "Required parameters Username or Password is missing!";
    echo json_encode($response);
}
?>