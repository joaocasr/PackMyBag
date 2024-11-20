<template>
	<div class="order-details-view">
		<NavBarComponent></NavBarComponent>
		<div class="encomenda-id">Encomenda {{ myordercode }}</div>

		<div class="button" @click="voltarHistorico()">
			<div class="button1">Voltar ao Histórico de Encomendas</div>
		</div>

		<OrderItemView
				v-for="(item, idx) in encomenda.itens"
				:key="idx"
				:imagemUrl="item.imagemUrl"
				:nomeArtigo="item.designacao"
				:preco="item.preco"
				:quantidade="item.nraquisicoes"
				:tamanho="item.tamanho"
				:cor="item.cor"
				:codigoArtigo="item.codigo"
				class = "item"
			/>
		<div class="group-parent">
				<div class="nmero-de-encomenda-parent">
					<div class="nmero-de-encomenda">Número de encomenda: </div>
					<div class="apt08087">{{ encomenda.codigoEncomenda }}</div>
					<div class="div"> {{ formatDate(encomenda.dataEntrega) }}</div>
					<div class="data-de-entrega">Data de entrega: </div>
					<div class="devolvido"> {{ encomenda.status }}</div>
					<div class="status">Status:</div>
					<div class="div1"> {{ formatDate(encomenda.dataDevolucao) }}</div>
					<div class="data-de-devoluo">Data de devolução:</div>
					<div class="detalhes-da-encomenda">DETALHES DA ENCOMENDA</div>
				</div>
				<div class="artigos-parent">
					<div class="detalhes-da-encomenda">TOTAL</div>
					<div class="artigos">{{ encomenda.itens.length }} artigos</div>
					<div class="div2">€ {{totalItensPreco().toFixed(2)}}</div>
					<div v-for="item in encomenda.itens" :key="item.codigo" class="item-container">
						<div class="item-nome">{{ item.designacao }}</div>
						<div class="item-preco">€ {{ item.preco.toFixed(2) }}</div>
					</div>
					
					<div class="resumo">
				
						<div class="entrega">Entrega</div>
						<div class="preco-entrega">€ {{encomenda.taxaEntrega.toFixed(2)}}</div>
			
						<div class="total">TOTAL</div>
						<div class="total-final">€ {{ (totalItensPreco() + encomenda.taxaEntrega).toFixed(2) }}</div>
						
						</div>
				</div>
		</div>
	</div>
    <FooterComponent></FooterComponent>
  	
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import OrderItemView from '@/components/OrderItemView.vue';
import axios from 'axios';
import authService from '@/services/auth-service'; 

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		OrderItemView
	},
	props: {
		orderCode: {
		type: String,
		required: true
		}
  	},
	data() {
		return {
			username: '',
			token: null,
			myordercode:'',
			encomenda: null,
			role: null
		};
	},
	created() {
		//this.myordercode = this.$route.params.orderCode;
		console.log(this.myordercode);
		this.getUsername(); 
		this.fetchEncomendas(); 
	},
	methods: {
		getUsername() {
			let token = authService.getToken();
			console.log(token);
			if (token != null) {
				this.token = token;
				this.username = token.username;
				this.role = token.role;
			}
		},
		fetchEncomendas() {
			let url = `http://localhost:8888/api/encomendaService/cliente/username/${this.username}/codigoEncomenda/${this.orderCode}`;
			if (this.role === 'Tecnico') {
				url = `http://localhost:8888/api/encomendaService/codigo/${this.orderCode}`;
			}
			axios.get(url)
				.then(response => {
					console.log('Dados recebidos do Axios:', response.data);
					this.encomenda = response.data;
				})
				.catch(error => {
					console.error('Erro ao buscar encomendas:', error);
				});
		},
		formatDate(data) {
			if (data.toLowerCase() === 'não') return 'Não';
			const date = new Date(data);
			const dia = String(date.getDate()).padStart(2, '0');
			const mes = String(date.getMonth() + 1).padStart(2, '0');
			const ano = date.getFullYear();
			return `${dia}/${mes}/${ano}`;
			},
		totalItensPreco() {
			if (!this.encomenda || !Array.isArray(this.encomenda.itens)) return 0;
			return this.encomenda.itens.reduce((total, item) => {
				const preco = parseFloat(item.preco);
				return total + (isNaN(preco) ? 0 : preco);
			}, 0);
		},
		voltarHistorico() {
				this.$router.push('/orders'); // Substitua pelo caminho da sua rota de histórico
}
	}
};
</script>

<style lang="css" scoped>
@import '../assets/OrderDetailsView.css';
</style>

