var axios=require('axios');
var ap = process.env.UTILIZADORES_SERVICE_URL || "http://localhost:8087/api/utilizadores"

module.exports.signUpUser = async (username,nome,password,email,genero,morada,nrTelemovel) =>{
    try {
        const resp = await axios.post(`${ap}/signup/user`, {
                username: username,
                password: password,
                nome: nome,
                email: email,   
                genero: genero,   
                morada: morada,   
                nrTelemovel: nrTelemovel   
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


module.exports.validateToken = async (token) =>{
    try {
        const resp = await axios.post(`${ap}/verify`, 
            { accessToken: token },
            {
                headers: { 
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer '+token
                }
            }
        );
        return resp.data;
    } catch (err) {
        return err;
    }
}

module.exports.signUpEstilista = async (username, nome, password, email,genero,bio) =>{
    try {
        const resp = await axios.post(`${ap}/signup/estilista`, {
                username: username,
                password: password,
                nome: nome,
                email: email,   
                genero: genero,   
                bio: bio
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

module.exports.signUpTecnico = async (username, nome, password, email,nomeLoja) =>{
    try {
        const resp = await axios.post(`${ap}/signup/tecnico`, {
                username: username,
                nome: nome,
                password: password,
                nomeLoja: nomeLoja,   
                email: email
            }, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
        );
        return resp.data;
    } catch (err) {
        console.log(err);

    }
}

module.exports.getEstilistas = async (token,page,number) =>{
    try {
        const resp = await axios.get(`${ap}/estilistas?page=${page}&number=${number}`,
            {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer '+token
                    }
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

module.exports.getProfileImg = async (username) =>{
    try {
        const resp = await axios.get(`${ap}/image/${username}`, {
            responseType: 'arraybuffer'
        });
        return resp;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data, status: err.response.status };
        } else {
            throw { error: "Unknown error", status: 500 };
        }
    }
}

module.exports.getProfileInfo = async (username) =>{
    try {
        const resp = await axios.get(`${ap}/userinfo/${username}`);
        return resp.data;
    } catch (err) {
        if (err.response) {
            throw { error: err.response.data, status: err.response.status };
        } else {
            throw { error: "Unknown error", status: 500 };
        }
    }
}

module.exports.saveImage = async (form) => {
    try {
      console.log("cheguei aqui");
      const resp = await axios.post(`${ap}/image`, 
        form, 
        {
            headers: {
                ...form.getHeaders()
            }
        }    
    );
      console.log(resp);
      return resp.data;
    } catch (err) {
      if (err.response) {
        throw { error: err.response.data, status: err.response.status };
      } else {
        throw { error: "Unknown error", status: 500 };
      }
    }
  };

module.exports.updateProfile  = async (data) => {
    try {
      console.log("Starting profile update...");
      const resp = await axios.post(`${ap}/edit-profile`, data, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log("Field update response:", resp.data);
      return resp.data;
    } catch (err) {
      if (err.response) {
        console.error("Field update error:", err.response.data);
        throw { error: err.response.data, status: err.response.status };
      } else {
        console.error("Unknown error during field update");
        throw { error: "Unknown error", status: 500 };
      }
    }
};