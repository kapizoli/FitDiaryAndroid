<?php

date_default_timezone_set("UTC");
define("USAGE_WWW_OUTPUT", false);

include_once('config.php'); // $sqlDB; $sqlDBi;

function printresult($res) {
    if (USAGE_WWW_OUTPUT) {
        echo "<pre>";
        print_r($res);
        echo "</pre>";
        die();
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
                //'Result' => 'A JSON list of the ' . TABLE_NAME . ' table or error or a string (FAILED2)'
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
    case 'GETFOOD' :
        $sql = "SELECT * FROM food_plan;";

        $QRes = mysql_query($sql, $sqlDB);
        if (!$QRes) {
            die("FAILED1");
        }
        $result = Array();
			if ( mysql_num_rows( $QRes ) ) {	
				while ($row = mysql_fetch_assoc($QRes)) {
					$result[] = $row;
				}
				mysql_free_result($QRes);
				printresult($result);
			} else {
				die("FAILED");
			}
        break;
    default : getusage();
}
?>
