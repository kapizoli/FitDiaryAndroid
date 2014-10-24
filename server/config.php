<?php
sleep(0);
header("Content-Type: text/html; charset=UTF-8");
include_once('config.inc.php');
$sqlDB;
$sqlDBi;

dbConnect();

function dbConnect() {
	global $sqlDB;
	global $sqlDBi;

	$sqlDB = @mysql_connect(DB_HOST,DB_USERNAME,DB_PASSWORD);	
	if ( !$sqlDB )
		die("FAILED1 " . DB_HOST . " " . DB_USERNAME . " " . DB_PASSWORD );
	mysql_select_db(DB_NAME,$sqlDB) or die ( "FAILED2" );
	mysql_query("SET NAMES 'utf8';",$sqlDB);
	$sqlDBi = mysqli_connect(DB_HOST,DB_USERNAME,DB_PASSWORD);		
	if ( !$sqlDBi )
		die("FAILED3" );
	$sqlDBi->query("SET NAMES 'utf8';");
	$sqlDBi->query("SET character_set_results = 'utf8', character_set_client = 'utf8', character_set_connection = 'utf8', character_set_database = 'utf8', character_set_server = 'utf8'");
    mysql_query("SET character_set_results = 'utf8', character_set_client = 'utf8', character_set_connection = 'utf8', character_set_database = 'utf8', character_set_server = 'utf8'", $sqlDB);	
	mysqli_select_db($sqlDBi,DB_NAME) or die ( "Can't select the database: ".mysqli_error() );
}

?>
