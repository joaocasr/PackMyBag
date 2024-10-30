var axios = require('axios')
const ap = "http://localhost:8081/api/catalogo"

module.exports.getItemsByPage = (page,number) => {
    return axios.get(`${ap}/?page=${page}&number=${number}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
      });
}

module.exports.getAllItems = () => {
    return axios.get(`${ap}/all`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
      });
}

module.exports.getRandomItems = () =>{
    return axios.get(`${ap}/random`).then(resp=>{
        return resp.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}

module.exports.showItemDetails = (id) => {
    return axios.get(`${ap}/items/${id}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    });
}

module.exports.getReviews = (id,page,number) => {
    return axios.get(`${ap}/items/${id}/reviews?page=${page}&number=${number}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    });
}

module.exports.adicionaReview = (id,username,nome,profileImg,texto,timestamp,rating) => {
    
    return axios.post(`${ap}/items/${id}/addreview`,
        {
            "username":username,
            "name":nome,
            "profileImg":profileImg,
            "texto":texto,
            "timestamp":timestamp,
            "rating":rating
        }, 
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

module.exports.removeReview = (id,username) => {
    return axios.delete(`${ap}/items/${id}/delreview/${username}`).then(resp=>{
        return resp.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}

module.exports.getItemsByShop = (idloja,page,number) =>{
    return axios.get(`${ap}/lojas/${idloja}?page=${page}&number=${number}`)
    .then(items =>{
        return items.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }

    })
}

module.exports.getItemsByType = (type,page,number) =>{
    return axios.get(`${ap}/type/${type}?page=${page}&number=${number}`)
    .then(items =>{
        return items.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}

module.exports.getPerPriceandTypeItems = (type,min,max,page,number) =>{
    return axios.get(`${ap}/type/${type}/price?min=${min}&max=${max}&page=${page}&number=${number}`)
    .then(items =>{
        return items.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}

module.exports.getPerPriceItems = (min,max,page,number) =>{
    return axios.get(`${ap}/price?min=${min}&max=${max}&page=${page}&number=${number}`)
    .then(items =>{
        return items.data;
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}

module.exports.adicionaPeca = (codigo,designacao,
    preco,estilo,cores,tamanho,tipo,disponibilidade,imagem,
    idLoja,nrdisponiveis) => {
    
    return axios.post(`${ap}/addItem/Peca`,
        {
            "codigo":codigo,
            "designacao":designacao,
            "preco":preco,
            "estilo":estilo,
            "cores":cores,
            "tamanho":tamanho,
            "tipo":tipo,
            "disponibilidade":disponibilidade,
            "imagem":imagem,
            "idLoja":idLoja,
            "nrdisponiveis":nrdisponiveis
        }, 
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

module.exports.adicionaSet = (codigo,designacao,
    preco,estilo,tamanho,tipo,disponibilidade,imagem,
    idLoja,codigoPecas) => {
    
    return axios.post(`${ap}/addItem/Set`,
        {
            "codigo":codigo,
            "designacao":designacao,
            "preco":preco,
            "estilo":estilo,
            "tamanho":tamanho,
            "tipo":tipo,
            "disponibilidade":disponibilidade,
            "imagem":imagem,
            "idLoja":idLoja,
            "codigoPecas":codigoPecas
        }, 
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

module.exports.adicionaCalcado = (codigo,designacao,
    preco,estilo,cores,tamanho,tipo,disponibilidade,imagem,
    idLoja,nrdisponiveis) => {
    
    return axios.post(`${ap}/addItem/Calcado`,
        {
            "codigo":codigo,
            "designacao":designacao,
            "preco":preco,
            "estilo":estilo,
            "cores":cores,
            "tamanho":tamanho,
            "tipo":tipo,
            "disponibilidade":disponibilidade,
            "imagem":imagem,
            "idLoja":idLoja,
            "nrdisponiveis":nrdisponiveis
        }, 
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


module.exports.removeItem = (code,lojaid) => {
    
    data = {
        "code":code,
        "lojaid":lojaid
        }
    headers = {
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
        }
    }
    return axios.delete(`${ap}/deleteItem`,
        {
            data,
            headers
        }).then(resp=>{
        return resp.data
    }).catch(err=>{
        if (err.response) {
            throw { error: err.response.data };
        }else {
            throw err
        }
    })
}