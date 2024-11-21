var express = require('express')
var router = express.Router();
var utilizadoresService = require('../microservices/utilizadoresService')
const validate = require('../middleware/index')
const multer = require('multer');
const FormData = require('form-data');
const fs = require('fs');
const upload = multer();

router.post('/signin', async function(req,res,next){
    const username = req.body.username;
    const password = req.body.password;
    try {
        const resp = await utilizadoresService.signIn(username, password);
        //console.log(resp.response);
        if(resp.response) res.status(resp.response.status).jsonp(resp);
        else res.jsonp(resp);
    } catch (err) {
        //console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/signup/user', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero = req.body.genero;
    const morada = req.body.morada;
    const nrTelemovel= req.body.nrTelemovel;

    try {
        const resp = await utilizadoresService.signUpUser(username, nome, password, email,genero,morada,nrTelemovel);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/estilista', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero= req.body.genero;
    const bio = req.body.bio;

    try {
        const resp = await utilizadoresService.signUpEstilista(username, nome, password, email,genero,bio);
        console.log(resp);
        res.jsonp(resp);
    } catch (err) {
        console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/tecnico', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const nomeLoja = req.body.nomeLoja;
    try {
        const resp = await utilizadoresService.signUpTecnico(username, nome, password, email,nomeLoja);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/estilistas',validate.verifyToken, async function(req,res,next){
    try {
        const page = req.query.page;
        const number = req.query.number;    
        const resp = await utilizadoresService.getEstilistas(req.token,page,number);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/profileImg/:username', async function(req,res,next){
    try {
        const username = req.params.username;
        const resp = await utilizadoresService.getProfileImg(username);
        res.set('Content-Type', resp.headers['content-type']);
        res.send(resp.data); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.get('/profileInfo/:username', async function(req,res,next){
    try {
        const username = req.params.username;
        const resp = await utilizadoresService.getProfileInfo(username);
        res.send(resp); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/updateImage', upload.single('profile_image'), async function(req,res,next){
    try {
        if (!req.file || !req.body.username) { throw new Error("Missing file or username"); }

        const form = new FormData();
        
        form.append('profile_image', fs.createReadStream(req.file.originalname), {
            filename: req.file.originalname, 
            contentType: req.file.mimetype   
        });
        form.append('username', req.body.username);
        console.log(form);
        const resp = await utilizadoresService.saveImage(form);
        console.log(resp);
        res.send(resp); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/updateProfile', async (req, res) => {
    try {
      const { username, ...updatedFields } = req.body;
  
      if (!username) {
        return res.status(400).json({ error: 'Username is required.' });
      }
  
      if (Object.keys(updatedFields).length === 0) {
        return res.status(400).json({ error: 'No fields to update.' });
      }
  
      // Assuming you have a `User` model with an `updateProfile` method
      const result = await utilizadoresService.updateProfile(username, updatedFields);
  
      if (result) {
        res.status(200).json({ message: 'Profile updated successfully.', updatedFields });
      } else {
        res.status(404).json({ error: 'User not found.' });
      }
    } catch (error) {
      console.error('Error updating profile:', error);
      res.status(500).json({ error: 'Internal Server Error.' });
    }
  });
  

module.exports = router;
