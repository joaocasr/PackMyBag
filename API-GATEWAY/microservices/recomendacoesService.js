var axios = require('axios')
var ap = process.env.RECOMENDACOES_SERVICE_URL || "http://localhost:8088/api/recomendacoes"


module.exports.getPedidosEstilista = (username) => {
    return axios.get(`${ap}/pedidosE/${username}`).then(resp => {
        return resp.data;
    }).catch(err => {
        throw { error: err.response.data };
    });
}


module.exports.getPedidosCliente = (username) => {
    return axios.get(`${ap}/pedidosC/${username}`).then(resp => {
        return resp.data;
    }).catch(err => {
        throw { error: err.response.data };
    });
}

module.exports.createNewPedido = async (itemBody) => {
    try{
        let r = await axios.post(`${ap}/pedidos`,
                itemBody,
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                    }
                })
        return r.data;
    }catch(err){
        return err;
    } 
        
}

