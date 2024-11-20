var express = require('express');
var router = express.Router();
const encomendaService = require('../microservices/encomendaService');


/* Get All Encomendas with /all endpoint */
router.get("/all", (req, res, next) => {
  encomendaService.getAllEncomendas()
    .then(encomendas => res.json(encomendas))
    .catch(err => res.status(500).json(err));
});

/* Create Encomenda */
router.post("/", (req, res, next) => {
  encomendaService.createEncomenda(req.body)
    .then(encomenda => res.status(201).json(encomenda))
    .catch(err => res.status(500).json(err));
});

/* Get Encomenda by ID */
router.get("/:id", (req, res, next) => {
  encomendaService.getEncomendaById(req.params.id)
    .then(encomenda => res.json(encomenda))
    .catch(err => res.status(500).json(err));
});

/* Update Encomenda */
router.put("/:id", (req, res, next) => {
  encomendaService.updateEncomenda(req.params.id, req.body)
    .then(encomenda => res.json(encomenda))
    .catch(err => res.status(500).json(err));
});

/* Delete Encomenda */
router.delete("/:id", (req, res, next) => {
  encomendaService.deleteEncomenda(req.params.id)
    .then(() => res.status(204).send())
    .catch(err => res.status(500).json(err));
});

/* Get Encomendas by Cliente ID */
router.get("/cliente/:idCliente", (req, res, next) => {
    encomendaService.getEncomendasByCliente(req.params.idCliente)
      .then(encomendas => res.json(encomendas))
      .catch(err => res.status(500).json(err));
  });

/* Get Encomendas by Cliente Username */
router.get("/cliente/username/:username", (req, res, next) => {
    encomendaService.getEncomendasByClienteUsername(req.params.username)
      .then(encomendas => res.jsonp(encomendas.data))
      .catch(err => res.status(500).json(err));
});

/* Get Encomendas by loja id */
router.get("/loja/:lojaid", (req, res, next) => {
  encomendaService.getEncomendasByLojaID(req.params.lojaid)
    .then(encomendas => res.jsonp(encomendas))
    .catch(err => res.status(500).json(err));
});

/* Get Items by Encomenda ID */
router.get("/:idEncomenda/items", (req, res, next) => {
    encomendaService.getItemsByEncomenda(req.params.idEncomenda)
      .then(items => res.json(items))
      .catch(err => res.status(500).json(err));
  });

/* Novo Endpoint: Get Encomenda by Username and Codigo Encomenda */
router.get("/cliente/username/:username/codigoEncomenda/:codigoEncomenda", (req, res, next) => {
  const { username, codigoEncomenda } = req.params;
  encomendaService.getEncomendaByClienteUsernameAndCodigoEncomenda(username, codigoEncomenda)
    .then(encomenda => {
        if (encomenda) {
            res.json(encomenda);
        } else {
            res.status(404).json({ message: "Encomenda não encontrada." });
        }
    })
    .catch(err => res.status(500).json(err));
});

/* Get Encomenda by Codigo Encomenda */
router.get("/codigo/:codigoEncomenda", (req, res, next) => {
  encomendaService.getEncomendaByCodigoEncomenda(req.params.codigoEncomenda)
    .then(encomenda => res.json(encomenda))
    .catch(err => res.status(500).json(err));
});


/* Novo Endpoint: Get Encomendas por Nome da Loja */
router.get("/loja/nome/:nomeLoja", (req, res, next) => {
  const nomeLoja = req.params.nomeLoja;
  encomendaService.getEncomendasPorNomeLoja(nomeLoja)
    .then(encomendas => {
      if (encomendas && encomendas.length > 0) {
        res.json(encomendas);
      } else {
        res.status(204).send(); // No Content
      }
    })
    .catch(err => res.status(500).json(err));
});

/* Novo Endpoint: Atualizar Status da Encomenda */
router.put("/status/:codigo/:novoStatus", (req, res, next) => {
  const { codigo, novoStatus } = req.params;
  encomendaService.updateEncomendaStatus(codigo, novoStatus)
  .then(updatedStatus => res.json(updatedStatus))
  .catch(err => {
      if (err.error) {
      // Verificar a mensagem de erro e status enviado pelo backend
      if (err.error.includes("não encontrada")) {
          res.status(404).json({ message: err.error });
      } else if (err.error.includes("inválido")) {
          res.status(400).json({ message: err.error });
      } else {
          res.status(500).json({ message: "Erro interno do servidor." });
      }
      } else {
      res.status(500).json({ message: "Erro interno do servidor." });
      }
  });
});

module.exports = router;