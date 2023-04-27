const express = require("express");
const bodyParser = require("body-parser");
const fs = require('fs');
const app = express();

app.listen(3000, () => {
    console.log("Application started and Listening on port 3000");
});

app.use(express.static(__dirname));

app.use(bodyParser.urlencoded({ extended: true }))


app.get("/", (req, res) => {
    res.sendFile(__dirname + "/index.html");
});

app.post("/", (req, res) => {

    let date_ob = new Date();

    let date = ("0" + date_ob.getDate()).slice(-2);

    // current month
    let month = ("0" + (date_ob.getMonth() + 1)).slice(-2);

    // current year
    let year = date_ob.getFullYear();
/*
    // current hours
    let hours = date_ob.getHours();

    // current minutes
    let minutes = date_ob.getMinutes();

    // current seconds
    let seconds = date_ob.getSeconds();

    // prints date in YYYY-MM-DD format
*/
    preguntas = "Pregunta 1: " + req.body.pregunta_1 + "\n" +
                "Pregunta 2: " + req.body.pregunta_2 + "\n" +
                "Pregunta 3: " + req.body.pregunta_3 + "\n" +
                "Pregunta 4: " + req.body.pregunta_4 + "\n" +
                "Pregunta 5: " + req.body.pregunta_5;
    
    fs.writeFile("encuesta" + year + "-" + month + "-" + date + "", preguntas, 'utf8', function (err) {
        if (err) {
            console.log("An error occured while writing JSON Object to File.");
            return console.log(err);
        }
     
        console.log("Encuesta almacenada");
    });
    
    res.send("Gracias por enviar la encuesta");
});
