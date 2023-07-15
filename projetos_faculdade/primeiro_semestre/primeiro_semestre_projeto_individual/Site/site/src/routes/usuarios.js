var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

router.get("/", function (req, res) {
    usuarioController.testar(req, res);
});
router.get("/listar", function (req, res) {
    usuarioController.listar(req, res);
});
//Recebendo os dados do html e direcionando para a função cadastrar de usuarioController.js
router.post("/cadastrar", function (req, res) {
    usuarioController.cadastrar(req, res);
});
router.post("/salvarPersona", function (req, res) {
    usuarioController.salvarPersona(req, res);
});
router.post("/cadastrarSistema", function (req, res) {
    usuarioController.cadastrarSistema(req, res);
});
router.get("/listarUsuario", function (req, res) {
    usuarioController.listarUsuario(req, res);
});
router.get("/listarPersonas/:fkUsuarioPersonaVar", function (req, res) {
    usuarioController.listarPersonas(req, res);
});
router.get("/persona/:personagem/:classe/", function (req, res) {
    usuarioController.persona(req, res);
});
router.get("/metricas", function (req, res) {
    usuarioController.metricas(req, res);
});

router.post("/autenticar", function (req, res) {
    usuarioController.entrar(req, res);
});
router.delete("/deletar/:deletPersonaVar", function (req, res) {
    usuarioController.deletar(req, res);
});



module.exports = router;

