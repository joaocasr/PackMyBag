const axios = require('axios')
var ap = process.env.RECOMENDACOES_SERVICE_URL || "http://localhost:8084/api/recomendacoes"


module.exports.getPedidosEstilista =  (username,page,number) =>{
    return axios.get(`${ap}/pedidosE/${username}?page=${page}&number=${number}`).then(pedidos=>{
        return pedidos.data;
    }).catch(err =>{
        return err;
    })
}

module.exports.getPedidosCliente =  (username) =>{
    return axios.get(`${ap}/pedidosC/${username}?page=${page}&number=${number}`).then(pedidos=>{
        return pedidos.data;
    }).catch(err =>{
        return err;
    })
}


module.exports.changeStatus =  async (codigo,status) =>{

        try{
            const resp = await axios.patch(`${ap}/changeStatusPedido`,
                {
                    "nome":codigo,
                    "status":status
                }, 
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            );
            return resp.data;
        }catch(err){
            return err;
        }
}


module.exports.removePedido = (nome) => {
    return axios.delete(`${ap}/removePedido/${nome}`,
        {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }
    ).then(resp=>{
        return resp.data
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })  
}

module.exports.insertPedido =  async (usernamecliente,usernameestilista,nome,
    estilos,cores,nrOutfits,orcamento,peçasExcluidas,fabricsPreferences,occasions) =>{

        try{
            const resp = await axios.post(`${ap}/pedidos`, 
                {
                    "usernameEstilista":usernameestilista,
                    "usernameCliente":usernamecliente,
                    "nome":nome,
                    "estilos":estilos,
                    "cores":cores,
                    "nrOutfits":nrOutfits,
                    "orcamento":orcamento,
                    "peçasExcluidas":peçasExcluidas,
                    "fabricsPreferences":fabricsPreferences,
                    "occasions":occasions
                }, 
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            );
            return resp.data;
        }catch(err){
            return err;
        }
}

module.exports.editPedido =  async (descricao,itemsEditType,conjunto,status) =>{

        try{
            const resp = await axios.patch(`${ap}/pedidoEdit`, 
                {
                    "descricao":descricao,
                    "itemsEditType":itemsEditType,
                    "conjunto":conjunto,
                    "status":status
                }, 
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            );
            return resp.data;
        }catch(err){
            return err;
        }
}
