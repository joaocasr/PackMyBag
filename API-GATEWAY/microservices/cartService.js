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

module.exports.getpayment = async (codigo) => {
    try{
        let r = await axios.get(`${ap}/transaction/${codigo}`)
        return r.data;
    }catch(err){
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    }
}

module.exports.changePaymentStatus = (itemBody) => {
    return axios.post(`${ap}/changePaymentStatus`,
        itemBody,
        {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            }
        }
    ).then(resp => {
        return {"status":200, "data": resp.data}
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

module.exports.getEncomendasByLojaID = (lojaid) => {
    return axios.get(`${ap}/loja/${lojaid}`).then(resp => {
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


module.exports.createPaymentForms = async (itemBody) => {
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


module.exports.createPayment = async (itemBody) => {
    try{
        let r = await axios.post(`${ap}/createPaymentCartClean`,
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


// // Método para criar pagamento
// module.exports.createPaypalPayment = async (paymentData) => {
//     try {
//         let response = await axios.post(`${ap}/paypal/create`, paymentData, {
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//             }
//         });
//         return response.data;
//     } catch (err) {
//         if (err.response) {
//             throw { error: err.response.data };
//         } else {
//             throw err;
//         }
//     }
// };

module.exports.createPaypalPayment = (method, amount, currency, description) => {
    return axios.post(`${ap}/paypal/create`,
        {
            "method": method,
            "amount": amount,
            "currency": currency,
            "description": description
        },
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
};

// Método para capturar pagamento
module.exports.capturePaypalPayment = async (paymentId, payerId) => {
    try {
        let response = await axios.get(`${ap}/paypal/success`, {
            params: {
                paymentId: paymentId,
                PayerID: payerId
            }
        });
        return response.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    }
};

// Método para cancelar pagamento
module.exports.cancelPaypalPayment = async () => {
    try {
        let response = await axios.get(`${ap}/paypal/cancel`);
        return response.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    }
};

// Método para tratar erro de pagamento
module.exports.errorPaypalPayment = async () => {
    try {
        let response = await axios.get(`${ap}/paypal/error`);
        return response.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
    }
};