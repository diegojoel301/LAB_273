const http = require('http');

if(process.argv[2] && process.argv[2] === '-p' && process.argv[3])
{
    const options = {
        method: 'HEAD',
        hostname: process.argv[3]
    };
    
    const request = http.request(options, (res) => {
    
        console.log(res.headers);
    
        res.on('data', (chunk) => {
            data += chunk;
          });
        
          res.on('end', () => {
            console.log("Completada");
          });
    });
    
    
    request.on('error', (error) => {
        console.error(error);
      });
      
      request.end();
}
else
{
    console.error("Aplica asi:\n\tnode client.js -p <hostname>");
}
