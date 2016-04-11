<?php

	/*
	 * @package WebService
	 */

	$jurl = file_get_contents('http://data.iledefrance.fr/explore/dataset/carte-des-pharmacies-de-seine-et-marne/download?format=json'); //url du Json , il est content.

	$json_obj = json_decode($jurl); //Rendons le lisible

	$tab=array();


	  //suppression des objets, il est content.

	foreach($json_obj as $obj)
		{

	  		unset($obj->datasetid);

	  		unset($obj->recordid);

	  		unset($obj->geometry);

	  		unset($obj->record_timestamp);

	  		unset($obj->fields->departement);

	  		unset($obj->fields->libdepartement);

	  		unset($obj->fields->wgs);


	  		$tab[]=$obj;

		}





 	$json = json_encode($tab); //encodage json_obj
	    
	header('Content-Type: application/json');

	echo $json;//affichage du json





