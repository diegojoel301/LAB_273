const express = require("express");
const bodyParser = require("body-parser");
const fs = require('fs');
const app = express();
const formidable = require('formidable');
const path = require('path')

app.listen(3000, () => {
    console.log("Application started and Listening on port 3000");
});

app.use(express.static(__dirname));

app.use(bodyParser.urlencoded({ extended: true }))


app.get("/", (req, res) => {
    res.sendFile(__dirname + "/index.html");
});

app.post('/upload', (req, res, next) => {
 
    const form = new formidable.IncomingForm();
    form.parse(req, function (err, fields, files) {
 
        let date_ob = new Date();

        let date = ("0" + date_ob.getDate()).slice(-2);

        // current month
        let month = ("0" + (date_ob.getMonth() + 1)).slice(-2);

        // current year
        let year = date_ob.getFullYear();

        // current hours
        let hours = date_ob.getHours();

        // current minutes
        let minutes = date_ob.getMinutes();

        // current seconds
        let seconds = date_ob.getSeconds();

        let oldPath = files.filetoupload.filepath;

        console.log(oldPath);

        let newPath = path.join(__dirname, 'uploads')
            + '/' + date + month + year + hours + minutes + seconds;
        let rawData = fs.readFileSync(oldPath);
 
        fs.writeFile(newPath, rawData, function (err) {
            if (err) console.log(err)
            return res.send("Successfully uploaded")
        })
    })
});
