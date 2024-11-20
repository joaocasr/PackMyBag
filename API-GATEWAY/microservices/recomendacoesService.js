const axios = require('axios')
var ap = process.env.RECOMENDACOES_SERVICE_URL || "http://localhost:8084/api/recomendacoes"


module.exports.getPedidosEstilista =  (username) =>{
    axios.get(`${ap}/pedidosE/${username}`).then(pedidos=>{
        return pedidos.data;
    }).catch(err =>{
        return err;
    })
}

module.exports.getPedidosCliente =  (username) =>{
    axios.get(`${ap}/pedidosE/${username}`).then(pedidos=>{
        return pedidos.data;
    }).catch(err =>{
        return err;
    })
}

module.exports.insertPedido =  async (usernamecliente,usernameestilista,
    estilos,cores,nrOutfits,orcamento,peçasExcluidas,fabricsPreferences,occasions) =>{

        try{
            const resp = await axios.post(`${ap}/pedidos`, 
                {
                    "usernamecliente":usernamecliente,
                    "usernameestilista":usernameestilista,
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
