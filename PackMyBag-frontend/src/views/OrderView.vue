<template>
	<main>
    	<div class="encomendapage">
    		<NavBarComponent></NavBarComponent>
        		<div class="encomendas">
          			<p class="encomendas1">Encomendas</p>
        		</div>
        		<img class="encomendapage-child" alt="" src="">
        		<!-- Iterate over encomendas and pass each as a prop -->
				<!--  <OrderComponentView class="order-component-view"></OrderComponentView> -->
				<div v-for="encomenda in encomendas" :key="encomenda.codigoEncomenda" class="order-item">
					<OrderComponentView
					class="order-component-view"
					:orderCode="encomenda.codigoEncomenda"
					:deliveryDate="encomenda.dataEntrega"
					:returnDate="encomenda.dataDevolucao"
					:deliveryLocation="encomenda.localEntrega"
					:status="encomenda.status"
					:customerId="encomenda.clienteUsername"
					:storeId="encomenda.lojaId"
					:itens="encomenda.itens"
					:orderPrice="totalItensPreco(encomenda.itens,encomenda.taxaEntrega).toFixed(2)" 
					:deliveryFee="encomenda.taxaEntrega"
					></OrderComponentView><!-- podia passar tambem o encomenda.preco-->
				</div>
      	</div>
    	<FooterComponent></FooterComponent>
	</main>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import OrderComponentView from '@/components/OrderComponentView.vue';
import axios from 'axios';
import authService from '@/services/auth-service'; 

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		OrderComponentView
	},
	data() {
		return {
			username: '',
			token: null,
			lojaid: 0,
			encomendas: [] 

		};
	},
	created() {
		this.getUsername(); 
		this.fetchEncomendas(); 
	},
	methods: {
		getUsername() {
			let token = authService.getToken();
			if (token != null) {
				this.token = token;
				this.username = token.username;
				this.lojaid = token.idloja;
				this.role = token.role;
				this.nomeLoja = token.nomeloja;
			}
		},
		fetchEncomendas() {
			let url = this.$apiGatewayUrl+'/api/encomendaService/cliente/username/' + this.username;
			if (this.token.role === 'Tecnico') {
				url = this.$apiGatewayUrl+'/api/encomendaService/loja/nome/'+this.nomeLoja; 
			}
			axios.get(url)
				.then(response => {
					this.encomendas = response.data;
				})
				.catch(error => {
					console.error('Erro ao buscar encomendas:', error);
				});
		},
		totalItensPreco(itens, taxaEntrega) {
			if (!itens || !Array.isArray(itens)) return 0;
			const totalItens = itens.reduce((total, item) => {
			const preco = parseFloat(item.preco);
			return total + (isNaN(preco) ? 0 : preco);
			}, 0);
			const taxa = parseFloat(taxaEntrega);
			return totalItens + (isNaN(taxa) ? 0 : taxa);
		}
	}
};
</script>

<style lang="css" scoped>
@import '../assets/OrderView.css';
</style>