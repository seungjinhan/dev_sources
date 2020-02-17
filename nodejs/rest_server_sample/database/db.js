const dotenv = require('dotenv')
const mysql = require('mysql')

dotenv.config()

const db = mysql.createPool({
    connectionLimit: 100,
    host: process.env.MYSQL_URL,
    port: process.env.MYSQL_PORT,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PW,
    database: process.env.MYSQL_DB,
    debug: false,
});

module.exports = db;