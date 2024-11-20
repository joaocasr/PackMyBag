<template>
	<div class="AddPartView">
		<NavBarComponent class="NavBar"></NavBarComponent>
		<div class="inventario">
			<h1>Add Set</h1>
			<form @submit.prevent="addItem" class="add-part-form">
				<div class="form-group">
					<label for="codigo">Código do Set:</label>
					<input type="text" id="codigo" v-model="form.codigo" required />
				</div>

				<div class="form-group">
					<label for="designacao">Designação:</label>
					<input type="text" id="designacao" v-model="form.designacao" required />
				</div>

				<div class="form-group">
					<label for="preco">Preço:</label>
					<input type="number" id="preco" v-model="form.preco" min="0" step="0.01" required />
				</div>

				<div class="form-group">
					<label for="estilo">Estilo:</label>
					<input type="text" id="estilo" v-model="form.estilo" required />
				</div>

				<div class="form-group">
					<label for="tamanho">Tamanho:</label>
					<select id="tamanho" v-model="form.tamanho" required>
						<option value="" disabled>Selecione o tamanho</option>
						<option value="XS">XS</option>
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
					</select>
				</div>

				<div class="form-group">
					<label for="tipo">Tipo:</label>
					<input type="text" id="tipo" v-model="form.tipo" required />
				</div>

				<div class="form-group">
					<label for="disponibilidade">Disponibilidade:</label>
					<select id="disponibilidade" v-model="form.disponibilidade" required>
						<option value="" disabled>Selecione</option>
						<option value="Disponível">Disponível</option>
						<option value="Indisponível">Indisponível</option>
					</select>
				</div>

				<div class="form-group">
					<label for="imagem">Imagem:</label>
					<input type="url" id="imagem" v-model="form.imagem" required />
				
                </div> 
				<div class="form-group">
					<label>Códigos das Peças:</label>
					<div v-for="(peca, index) in form.codigoPecas" :key="index" class="peca-group">
						<label :for="'codigoPeca' + index">Peça {{ index + 1 }}:</label>
						<input type="text" :id="'codigoPeca' + index" v-model="form.codigoPecas[index]" required />
					</div>
					<button type="button" @click="addPeca" class="btn-add-peca">Adicionar Peça</button>
					<!-- Display error message if no peças are added -->
					<div v-if="pecaError" class="error-message">
						Por favor, adicione pelo menos uma peça.
					</div>
				</div>

				<button type="submit" class="btn-continuar">CONTINUAR</button>
				<button type="button" class="btn-voltar" @click="goToMenu">VOLTAR AO MENU</button>
			</form>
		</div>
		<FooterComponent></FooterComponent>
	</div>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
	components: {
		NavBarComponent,
		FooterComponent
	},
    created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.idloja=token.idloja; 
		}
	},
	data() {
		return {
            idloja:Number,
            token:null,
			form: {
				codigo: '',
				designacao: '',
				preco: 0,
				estilo: '',
				tamanho: '',
				tipo: '',
				disponibilidade: '',
				imagem: '',
				idLoja: this.idloja,
				codigoPecas: []
			},
			pecaError: false 
		};
	},
	methods: {
		addPeca() {
			this.form.codigoPecas.push(''); 
		},
		addItem() {

			this.pecaError = false;

			const trimmedPecas = this.form.codigoPecas
				.map(peca => peca.trim())
				.filter(peca => peca !== '');
			if (trimmedPecas.length === 0) {
				this.pecaError = true;
				return;
			}

			const uniquePecas = [...new Set(trimmedPecas)];

			const payload = { 
				codigo: this.form.codigo,
				designacao: this.form.designacao,
				preco: this.form.preco,
				estilo: this.form.estilo,
				tamanho: this.form.tamanho,
				tipo: this.form.tipo,
				disponibilidade: this.form.disponibilidade,
				imagem: this.form.imagem,
				idLoja: this.form.idLoja,
				codigoPecas: uniquePecas
			};

			//console.log('Payload enviado para o backend:', payload);

			axios.post('http://localhost:8888/api/catalogoService/addItem/Set', payload)
				.then(response => {
					console.log('Set adicionada com sucesso:', response.data);
					alert('Set adicionada com sucesso.');
					this.resetForm();
				})
				.catch(error => {
					console.error('Erro ao adicionar o set:', error.response ? error.response.data : error.message);
					alert('Erro ao adicionar o set.' + ( error.response.data.error.message));
				});
		},
		resetForm() {
			this.form = {
				codigo: '',
				designacao: '',
				preco: 0,
				estilo: '',
				tamanho: '',
				tipo: '',
				disponibilidade: '',
				imagem: '',
				idLoja: this.idloja,
				codigoPecas: [] 
			};
			this.pecaError = false;
		},
		goToMenu() {
			this.$router.push('home/shop'); // Ajuste o caminho conforme necessário
		}
	}
};
</script>
<style lang="css" scoped>
@import '../assets/AddSetView.css';
</style>
