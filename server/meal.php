<?php

date_default_timezone_set("UTC");
define("USAGE_WWW_OUTPUT", false);
define("TABLE_NAME", "user");

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
                'Result' => 'A JSON list of the ' . TABLE_NAME . ' table or error or a string (FAILED2)'
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
    case 'GetByDays' :
        if (!isset($_POST['user_id'])) {
            $error = Array('ERROR' => 'POST_User_ID_NOT_SET');
            printresult($error);
        }
        if (!isset($_POST['begin_date'])) {
            $error = Array('ERROR' => 'POST_Begin_Date_NOT_SET');
            printresult($error);
        }
        if (!isset($_POST['end_date'])) {
            $error = Array('ERROR' => 'POST_End_Date_NOT_SET');
            printresult($error);
        }
        $sql = "SELECT name, id, date FROM meal WHERE user_id = " . $_POST['user_id'] . " AND date BETWEEN '" . $_POST['begin_date'] . "' AND '" . $_POST['end_date'] . "';";

        $QRes = mysql_query($sql, $sqlDB);
        if (!$QRes) {
            die("FAILED1 ". $sql);
        }
        $result = Array();
        if (mysql_num_rows($QRes)) {
            while ($row = mysql_fetch_assoc($QRes)) {
                $ID = $row["id"];
                $sql1 = "SELECT id, name, unit, quantity, fat, sugar, energy, carbohidrate, protein, daily_category, resource_category FROM food WHERE meal_id = " . $ID . ";";
                $QRes1 = mysql_query($sql1, $sqlDB);

                if (!$QRes1) {
                    die("FAILED2");
                }

                $result1 = Array();
                if (mysql_num_rows($QRes1)) {
                    while ($row1 = mysql_fetch_assoc($QRes1)) {
                        $result1[] = $row1;
                    }
                    mysql_free_result($QRes1);

                    $row["foods"] = $result1;
                    $result[] = $row;
                } else {
                    $result[] = $row;
                }
            }
            mysql_free_result($QRes);
            $a = array();
            $a["meals"] = $result;
            printresult($a);
        } else {
            die("FAILED4 ". $sql);
        }
        break;
    case 'GET' :
        if (!isset($_POST['user_id'])) {
            $error = Array('ERROR' => 'POST_User_ID_NOT_SET');
            printresult($error);
        }
        $sql = "SELECT name, id, date FROM meal WHERE user_id = " . $_POST['user_id'] . ";";

        $QRes = mysql_query($sql, $sqlDB);
        if (!$QRes) {
            die("FAILED1");
        }
        $result = Array();
        if (mysql_num_rows($QRes)) {
            while ($row = mysql_fetch_assoc($QRes)) {
                $ID = $row["id"];
                $sql1 = "SELECT id, name, unit, quantity, fat, sugar, energy, carbohidrate, daily_category, resource_category FROM food WHERE meal_id = " . $ID . ";";
                $QRes1 = mysql_query($sql1, $sqlDB);

                if (!$QRes1) {
                    die("FAILED2");
                }

                $result1 = Array();
                if (mysql_num_rows($QRes1)) {
                    while ($row1 = mysql_fetch_assoc($QRes1)) {
                        $result1[] = $row1;
                    }
                    mysql_free_result($QRes1);

                    $row["foods"] = $result1;
                    $result[] = $row;
                } else {
                    $result[] = $row;
                }
            }
            mysql_free_result($QRes);
            $a = array();
            $a["meals"] = $result;
            printresult($a);
        } else {
            die("FAILED4");
        }
        break;
    case 'ADD' :
        if (!isset($_POST['user_id'])) {
            $error = Array('ERROR' => 'POST_User_ID_NOT_SET');
            printresult($error);
        }
        if (!isset($_POST['date'])) {
            $error = Array('ERROR' => 'POST_Date_NOT_SET');
            printresult($error);
        }

        $sql = "insert into " . TABLE_NAME . "(user_id,date) Values ('" . $_POST['user_id'] . "','" . $_POST['date'] .  "');";
        $QRes = mysql_query($sql, $sqlDB);
        if (!$QRes) {
            die("FAILED - INSERT INTO");
        }
        //Get last inserted ID
        $res = array('NewID' => mysql_insert_id($sqlDB));
        printresult($res);

        break;
    default : getusage();
}
?>
