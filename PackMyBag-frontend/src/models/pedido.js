export default class Pedido{
    constructor(idPedido,idCliente,estilos,cores,nrOutfits,orcamento,pecasExcluidas,fabricsPrefered,ocasioes){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.estilos = estilos;
        this.cores = cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.pecasExcluidas = pecasExcluidas;
        this.fabricsPrefered = fabricsPrefered;
        this.ocasioes = ocasioes;
    }
}