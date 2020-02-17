const dotenv = require('dotenv')
const path = require('path')

if (!process.env.MYSQL_URL) {
  dotenv.config({
    path: path.join(__dirname, "..", ".env")
  })
}

module.exports = {
    host:process.env.MYSQL_URL,
    port:process.env.MYSQL_PORT,
    username:process.env.MYSQL_USER,
    password:process.env.MYSQL_PW,
    database:process.env.MYSQL_DB,    
}