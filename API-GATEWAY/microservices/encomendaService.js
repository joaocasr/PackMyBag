const axios = require('axios');
const baseURL = "http://127.0.0.1:8082/api/encomendas";

module.exports = {
  getAllEncomendas: () => {
    return axios.get(`${baseURL}/all`)
        .then(resp => resp.data)
        .catch(err => {
        if (err.response) {
            throw { error: err.response.data };
        } else {
            throw err;
        }
        });
    },

  createEncomenda: (encomenda) => {
    return axios.post(baseURL, encomenda)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  getEncomendaById: (id) => {
    return axios.get(`${baseURL}/${id}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  updateEncomenda: (id, encomenda) => {
    return axios.put(`${baseURL}/${id}`, encomenda)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  deleteEncomenda: (id) => {
    return axios.delete(`${baseURL}/${id}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  getEncomendasByCliente: (idCliente) => {
    return axios.get(`${baseURL}/cliente/${idCliente}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  getItemsByEncomenda: (idEncomenda) => {
    return axios.get(`${baseURL}/${idEncomenda}/items`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  getEncomendaByCodigoEncomenda: (codigoEncomenda) => {
    return axios.get(`${baseURL}/codigo/${codigoEncomenda}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  getEncomendasByClienteUsername: (username) => {
    return axios.get(`${baseURL}/cliente/username/${username}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  },

  /* Novo Método: Get Encomenda by Cliente Username and Codigo Encomenda */
  getEncomendaByClienteUsernameAndCodigoEncomenda: (username, codigoEncomenda) => {
    return axios.get(`${baseURL}/cliente/username/${username}/codigoEncomenda/${codigoEncomenda}`)
      .then(resp => resp.data)
      .catch(err => {
        if (err.response) {
          if (err.response.status === 404) {
            return null; // Retorna null se não encontrar a encomenda
          }
          throw { error: err.response.data };
        } else {
          throw err;
        }
      });
  }
  
};