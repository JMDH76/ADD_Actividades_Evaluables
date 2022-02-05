<?php
    if(isset($_POST["nameId"])){
        $nameId = $_POST["nameId"];
        $specie = $_POST["specie"];
        $gender = $_POST["gender"];
        $origin = $_POST["origin"];
        $locat = $_POST["locat"];
        $stat = $_POST["stat"];
        $imagen = $_POST["imagen"];
        $chapters = $_POST["chapters"];

        $servidor = "localhost";
        $usuario = "root";
        $password = "";
        $dbname = "rick_morty";

        $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
        if (!$conexion) {
            echo "MySQL connection error: ".mysqli_connect_error();
            exit();
        } 
        $sql = "INSERT INTO personaje (nameId, specie, gender, origin, locat, stat, imagen, chapters) 
        VALUES ('".addslashes($nameId)."','".addslashes($specie)."', '".addslashes($gender)."' , 
        '".addslashes($origin)."' , '".addslashes($locat)."', '".addslashes($stat)."', '".addslashes($imagen)."' , 
        '".addslashes($chapters)."')";
       
            if (mysqli_query($conexion, $sql)) {
            echo "Record inserted correctly.";
        } else {
            echo "Error: ".mysqli_error($conexion);
        }
    }
?>