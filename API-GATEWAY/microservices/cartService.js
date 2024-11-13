var axios = require('axios')
var ap = process.env.CESTO_SERVICE_URL || "http://localhost:8085/api/cart"

module.exports.getCartItems = (username) => {
    return axios.get(`${ap}/${username}`).then(resp => {
        return resp.data;
    }).catch(err => {
        throw { error: err.response.data };
    });
}

module.exports.addToCart = (itemBody) => {
    return axios.post(`${ap}/addItem`,
        itemBody,
        {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }
    ).then(resp => {
        return resp.data;
    }).catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    });
}

module.exports.removeFromCart = (itemBody) => {
    return axios.post(`${ap}/removeItem`,
        itemBody,
        {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }
    ).then(resp => {
        return resp.data;
    }).catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    });
}

module.exports.getTransactions = (username) => {
    return axios.get(`${ap}/transactions/${username}`).then(resp => {
        return resp.data;
    }).catch(err => {
        throw { error: err.response.data };
    });
}



module.exports.createPayment = async (itemBody) => {
    try{
        let r = await axios.post(`${ap}/createPayment`,
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
