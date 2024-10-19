var axios = require('axios')
const ap = "http://localhost:8081/api/catalogo"

module.exports.getItemsByPage = (page,number) => {
    return axios.get(`${ap}/?page=${page}&number=${number}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        throw err;
    });
}

module.exports.getAllItems = () => {
    return axios.get(`${ap}/all`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        throw err;
    });
}

module.exports.showItemDetails = (id) => {
    return axios.get(`${ap}/items/${id}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        throw err;
    });
}

module.exports.getReviews = (id,page,number) => {
    return axios.get(`${ap}/items/${id}/reviews?page=${page}&number=${number}`).then(resp => {
        return resp.data;
    })
    .catch(err => {
        throw err;
    });
}

module.exports.adicionaReview = (id,username,nome,profileImg,texto,timestamp,rating) => {
    
    return axios.post(`${ap}/items/${id}/review`,
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
        throw err;
    })
}
