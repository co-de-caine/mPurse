<?php
require_once 'include/DB_Functions.php'
$db = new DB_Functions();

//json array response
$response = array('error' => , 0);

if(isset($_POST['uuid']) && isset($_POST['amount']) && isset($_POST['recvr']) ) {

	$uuid = $_POST['uuid'];
    $amount = $_POST['amount'];
    $recvr = $_POST['recvr'];
    $message = $db->requestForMoney($uuid,$recvr,$amount); 
    if ($message == 'unsuccessful') {
        $response["error"] = 2;
        $response["error_msg"] = "Unsuccesful Request Submission";
        echo json_encode($response);
    }
    elseif($message == 'User not found'){
        $response["error"] = 1;
        $response["error_msg"] = "Reciever Not Found";
        echo json_encode($response);
    }
    else {
        $response["error"] = 0;
        $response["error_msg"] = "Succesful Request Submission";
        $response["refid"] = $message;
        echo json_encode($response);
    }
}
else {
    // required post params is missing
    $response["error"] = 3;
    $response["error_msg"] = "Required POST parameters are missing!";
    echo json_encode($response);
}
?>