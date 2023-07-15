var usuarioModel = require("../models/usuarioModel");

var sessoes = [];

function testar(req, res) {
    console.log("ENTRAMOS NA usuarioController");
    res.json("ESTAMOS FUNCIONANDO!");
}
function listar(req, res) {
    usuarioModel.listar()
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
function entrar(req, res) {
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;
    if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está indefinida!");
    } else {        
        usuarioModel.entrar(email, senha)
            .then(
                function (resultado) {
                    console.log(`\nResultados encontrados: ${resultado.length}`);
                    console.log(`Resultados: ${JSON.stringify(resultado)}`); // transforma JSON em String

                    if (resultado.length == 1) {
                        console.log(resultado);
                        res.json(resultado[0]);
                    } else if (resultado.length == 0) {
                        res.status(403).send("Ajude-me Obi-Wan Kenobi, pois esse e-mail ou senha não existem.");
                    } else {
                        res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                    }
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}
function cadastrar(req, res) {
    var nome = req.body.nomeServer;
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;
    var sistemaFav = req.body.sistemaFavServer;
    var freq = req.body.freqServer;
    if (nome == undefined) {
        res.status(400).send("Seu nome está undefined!");
    } else if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else if (sistemaFav == undefined) {
        res.status(400).send("Sua sistemaFav está undefined!");
    }else if (freq == undefined) {
        res.status(400).send("Sua racaFav está undefined!");
    }else {        
        usuarioModel.cadastrar(nome, email, senha, sistemaFav, freq)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}
function salvarPersona(req, res) {
    var nomePersonagem = req.body.nomePersonagemServer;
    var historiaPersonagem = req.body.historiaPersonagemServer;
    var idpersonagemEscolhido = req.body.idpersonagemEscolhidoServer;
    var idUsuariosLog = req.body.idUsuariosLogServer;
    if (nomePersonagem == undefined) {
        res.status(400).send("Seu nomePersonagem está undefined!");
    } else if (historiaPersonagem == undefined) {
        res.status(400).send("Seu historiaPersonagem está undefined!");
    } else if (idpersonagemEscolhido == undefined) {
        res.status(400).send("Sua idpersonagemEscolhido está undefined!");
    } else if (idUsuariosLog == undefined) {
        res.status(400).send("Sua idUsuariosLog está undefined!");
    }else {       
        usuarioModel.salvarPersona(nomePersonagem, historiaPersonagem, idpersonagemEscolhido, idUsuariosLog)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}
function cadastrarSistema(req, res) {
    var sistemaPrimeira = req.body.sistemaPrimeiraServer;
    var sistemaSegunda = req.body.sistemaSegundaServer;
    var fkUsuario = req.body.fkUsuarioServer;
    if (sistemaPrimeira == undefined) {
        res.status(400).send("Seu sistemaPrimeira está undefined!");
    } else if (sistemaSegunda == undefined) {
        res.status(400).send("Seu sistemaSegunda está undefined!");
    }  else if (fkUsuario == undefined) {
        res.status(400).send("Seu fkUsuario está undefined!");
    }else {        
        usuarioModel.cadastrarSistema(sistemaPrimeira, sistemaSegunda, fkUsuario)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}
function listarUsuario(req, res) {
    usuarioModel.listarUsuario()
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
function listarPersonas(req, res) {
    var fkUsuarioPersona = req.params.fkUsuarioPersonaVar;
    usuarioModel.listarPersonas(fkUsuarioPersona)
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
function persona(req, res) {
    var personagemVar = req.params.personagem;
    var classeVar = req.params.classe;
    usuarioModel.persona(personagemVar, classeVar)
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
function metricas(req, res) {
    usuarioModel.metricas()
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
function deletar(req, res) {
    var deletPersona = req.params.deletPersonaVar;

    usuarioModel.deletar(deletPersona)
        .then(
            function (resultado) {
                res.json(resultado);
            }
        )
        .catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao deletar o post: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}
module.exports = {
    entrar,
    cadastrar,
    salvarPersona,
    cadastrarSistema,
    listar,
    listarUsuario,
    listarPersonas,
    metricas,
    persona,
    deletar,
    testar
}