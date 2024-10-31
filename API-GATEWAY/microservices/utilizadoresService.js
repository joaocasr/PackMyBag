var axios=require('axios');
var ap = "http://localhost:8087/api/utilizadores"

module.exports.signUpUser = async (username,nome,password,email) =>{
    try {
        const resp = await axios.post(`${ap}/signup/user`, {
                username: username,
                password: password,
                nome: nome,
                email: email   
            }, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
        );
        return resp.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data, status: err.response.status };
        } else {
            throw { error: "Unknown error", status: 500 };
        }
    }
}

module.exports.signIn = async (username,password) =>{
    try {
        const resp = await axios.post(`${ap}/signin`, {
                username: username,
                password: password,
            }, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
        );
        return resp.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data, status: err.response.status };
        } else {
            throw { error: "Unknown error", status: 500 };
        }
    }
}