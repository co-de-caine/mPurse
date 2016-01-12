<?php
 
class DB_Functions {
 
    private $conn;
    // constructor
    function __construct() {
        require_once 'include/DB_Connect.php';
        // connecting to database
        $db = new DB_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * @param name, password, phoneno, email,
     * returns boolean for successful addition
     */
    public function addUser($name, $passHash, $phoneno, $email) {

        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $password = base64_encode($passHash . $salt); // encrypted password

        $stmt = $this->conn->prepare("INSERT INTO users(uuid, name, phoneno, email, password, salt, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?, NOW(), NOW())");
        $stmt->bind_param("ssisss", uniqid('mp'), $name, $phoneno, $email, $password, $salt);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check for valid password
     * @param salt, password hash sent
     * returns encrypted_pass
     */
    public function checkPassHash($salt, $hash) {
        $checkEncyptPass = base64_encode($hash . $salt);
        return $checkEncyptPass;
    }

    /**
     * Fetching user details
     * @param regno
     * returns user details
     */

    public function getUserDetails($phoneno) {
        $stmt = $this->conn->prepare("SELECT * FROM users WHERE phoneno = ? LIMIT 1");
        $stmt->bind_param("i",$phoneno);
        if($stmt->execute()) { 
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        }
        else {
            return NULL;
        }
    }
 
    /**
     * Check user is existed or not
     * @param regno
     * returns boolean for existence 
     */
    public function isUserExisted($phoneno) {
        $stmt = $this->conn->prepare("SELECT phoneno from users WHERE phoneno = ? LIMIT 1");
        $stmt->bind_param("i", $phoneno);
        $stmt->execute();
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
}
 
?>