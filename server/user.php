<?php	
	date_default_timezone_set("UTC");
	define("USAGE_WWW_OUTPUT",false);
	define("TABLE_NAME","user");
	
	include_once('config.php');	// $sqlDB; $sqlDBi;

	function printresult($res) {
		if (USAGE_WWW_OUTPUT)  {
			echo "<pre>"; print_r($res); echo "</pre>"; die();
		}
		echo json_encode($res);
		die();
	}
	
	function getusage() {
		$Usage[] = Array(
				'Header' => 'Available Commands',
				'Commands' => array( 
					array(
					'CommandName' => 'ADD',
					'Params' => 'NickName(String), Password(String), Email(String), Sex(int), Birthday(String)',
					'Result' => 'A JSON with the new ID or error or a string (FAILED1)'
					),
					array(
					'CommandName' => 'GET',
					'Params' => 'NickName(String), Password(String)',
					'Result' => 'A JSON list of the '.TABLE_NAME.' table or error or a string (FAILED2)'
					),
				)						
		);
		printresult($Usage);
	}

	$command = 'GetUsage';
	// Get Post Data
	if (isset($_POST['action'])) {
		$command = $_POST['action'];
	}
	
	switch ($command) {
		case 'GetUsage' : getusage();
			break;
		case 'ADD' : 
			if (!isset($_POST['NickName'])) {
				$error = Array('ERROR' => 'POST_NickName_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Password'])) {
				$error = Array('ERROR' => 'POST_Password_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Email'])) {
				$error = Array('ERROR' => 'POST_Email_NOT_SET');
				printresult($error);
			}
			//if email is already used
			$email_check_sql = "SELECT * FROM ".TABLE_NAME." WHERE email ='".$_POST['Email']."';";
			$email_check_QRes = mysql_query ($email_check_sql,$sqlDB); 
			if ( mysql_num_rows( $email_check_QRes ) ) {	
				die("FAILED - EMAIL ALREADY USED");
			}
			if (!isset($_POST['Sex'])) {
				$error = Array('ERROR' => 'POST_Sex_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Birthday'])) {
				$error = Array('ERROR' => 'POST_Birthday_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Last_updated'])) {
				$error = Array('ERROR' => 'POST_Last_updated_NOT_SET');
				printresult($error);
			}
			$sql = "insert into ".TABLE_NAME."(nick_name,password,email,sex,birthday,last_updated) Values ('".$_POST['NickName']."','".$_POST['Password']."','".$_POST['Email']."',".$_POST['Sex'].",'".$_POST['Birthday']."','".$_POST['Last_updated']."');";
			$QRes = mysql_query ($sql,$sqlDB);
			if (!$QRes) {		
				die("FAILED3 ".$sql);
			}
			//Get last inserted ID
			$res = array( 'NewID' => mysql_insert_id($sqlDB));
			printresult($res);
			break;
		
		case 'GET' :
			if (!isset($_POST['Email'])) {
				$error = Array('ERROR' => 'POST_Email_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Password'])) {
				$error = Array('ERROR' => 'POST_Password_NOT_SET');
				printresult($error);
			} 
			$sql = "select * from ".TABLE_NAME." WHERE email='".$_POST['Email']."' and password='".$_POST['Password']."';";
			$QRes = mysql_query ($sql,$sqlDB); 
			if (!$QRes) {		
				die("FAILED4");
			}
			$result = Array();
			if ( mysql_num_rows( $QRes ) ) {	
				while ($row = mysql_fetch_assoc($QRes)) {
					$result[] = $row;
				}
				mysql_free_result($QRes);
				printresult($result);
			} else {
				die("FAILED5");
			}
			break;
		case 'UPDATEPASSWORD' : 
			if (!isset($_POST['userid'])) {
				$error = Array('ERROR' => 'POST_UserID_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['password'])) {
				$error = Array('ERROR' => 'POST_Password_NOT_SET');
				printresult($error);
			}
			$sql = "update ".TABLE_NAME." set password='".$_POST['password']."'   where id = ".$_POST['userid'];
			$QRes = mysql_query ($sql,$sqlDB);
			if (!$QRes) {		
				die("FAILED6");
			}
			die("OK");
			break;
		case 'UPDATEUSER' : 
			if (!isset($_POST['UserID'])) {
				$error = Array('ERROR' => 'POST_UserID_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Email'])) {
				$error = Array('ERROR' => 'POST_Email_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Sex'])) {
				$error = Array('ERROR' => 'POST_Sex_NOT_SET');
				printresult($error);
			}
			if (!isset($_POST['Birthday'])) {
				$error = Array('ERROR' => 'POST_Birthday_NOT_SET');
				printresult($error);
			}
			$sql = "update ".TABLE_NAME." set email='".$_POST['Email']."', sex=".$_POST['Sex'].", birthday='".$_POST['Birthday']."'   where id = ".$_POST['UserID'].";";
			$QRes = mysql_query ($sql,$sqlDB);
			if (!$QRes) {		
				die("FAILED7");
			}
			die("OK");
			break;
		default : getusage();
	}
?>