var axios = require('axios')
const ap = "http://localhost:8081/api/favoritos"

/*


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

*/

module.exports.getPerGenderItems = (username, gender, page, number) => {
    return axios.get(`${ap}/genero/${username}?genero=${gender}&page=${page}&number=${number}`).then(resp => {
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


module.exports.getPerSizeItems = (username, size, page, number) =>{
    return axios.get(`${ap}/genero/${username}?size=${size}&page=${page}&number=${number}`).then(resp => {
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

module.exports.getPerPriceItems = (username, min, max, page, number) =>{
    return axios.get(`${ap}/price/${username}?min=${min}&max=${max}&page=${page}&number=${number}`)
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

module.exports.addItemFavorite = (codigo, username, designacao, preco, disponibilidade, tipo, imagem, subclasse, dimensao) => {

    return axios.post(`${ap}/addItem`,
        {
            "codigoItem":codigo,
            "clienteUsername":username,
            "designacao":designacao,
            "preco":preco,
            "disponibilidade":disponibilidade,
            "tipo":tipo,
            "imagem":imagem,
            "subclasse":subclasse,
            "dimensao":dimensao
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


module.exports.removePecaFavorite = (username, itemCode) => {
    
    data = {
        "username":username,
        "itemCode":itemCode
        }
    headers = {
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
        }
    }
    return axios.delete(`${ap}/removeItem`,
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

module.exports.getUserFavsByPage = (page,number) => {
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