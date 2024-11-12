const axios = require('axios');
var ap = process.env.NOTIFICACOES_SERVICE_URL || "http://localhost:8086/api/notificacoes"
module.exports.addInterested = async (codigo,designacao,disponibilidade,idLoja,username,nomeuser,email) =>{
    try{
        const resp = await axios.post(`${ap}/addItemInterested`, {
            codigo: codigo,
            designacao: designacao,
            disponibilidade: disponibilidade,
            idLoja: idLoja,   
            username: username,   
            nomeuser: nomeuser,   
            email: email   
        }, 
            {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
        );
        return resp.data;
    }catch(err){
        if (err.response) {
            throw { error: err.response.data, status: err.response.status };
        } else {
            throw { error: "Unknown error", status: 500 };
        }
    }
}
