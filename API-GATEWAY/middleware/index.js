var utilizadoresService = require('../microservices/utilizadoresService')

module.exports.authenticateToken = async (req, res, next) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (!token) {
     return res.status(401).json({ message: 'Access token is missing' });
    }
    try{
        let r = await utilizadoresService.validateToken(token);
        req.token = token;
        //console.log("yesss");
        //console.log(r);
    }catch(err){
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
    next();
};  
