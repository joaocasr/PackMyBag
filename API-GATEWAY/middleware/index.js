var utilizadoresService = require('../microservices/utilizadoresService')

module.exports.verifyToken = async (req, res, next) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (!token) {
     return res.status(401).json({ message: 'You are not logged in.' }); //access token missing
    }
    try{
        let r = await utilizadoresService.validateToken(token);
        req.token = token;
    }catch(err){
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
    next();
};  
