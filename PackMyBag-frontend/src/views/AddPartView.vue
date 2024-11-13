<template>
	<div class="AddPartView">
		<NavBarComponent class="NavBar"></NavBarComponent>
		<div class="inventario">
		<h1>Adicionar Nova Peça</h1>
		<form @submit.prevent="addItem" class="add-part-form">
			<div class="form-group">
			<label for="codigo">Código da Peça:</label>
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
				<label>Cores:</label>
					<div class="colors-container">
						<label v-for="color in availableColors" :key="color" class="color-label">
							<input type="checkbox" :value="color" v-model="form.cores" />
							{{ color }}
						</label>
						<label class="color-label">
							<input type="checkbox" value="Other" v-model="form.cores" />
							Outro
						</label>
					</div>
					<div v-if="form.cores.includes('Other')" class="other-color">
						<input type="text" v-model="otherColor" placeholder="Escreva a cor" />
					</div>
					<div v-if="colorError" class="error-message">
						Por favor, selecione pelo menos uma cor.
					</div>
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
			<label for="imagem">Imagem:</label>
			<input type="url" id="imagem" v-model="form.imagem" required />
			</div>
<!-- 	
			<div class="form-group">
			<label for="idLoja">Código da Loja:</label>
			<input type="text" id="idLoja" v-model="form.idLoja" required />
			</div> -->
	
			<div class="form-group">
			<label for="nrdisponiveis">Número Disponíveis:</label>
			<input type="number" id="nrdisponiveis" v-model="form.nrdisponiveis" min="0" required />
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
import axios from 'axios';
export default {
	components:{
		NavBarComponent,
		FooterComponent
	},
	data() {
	  return {
		availableColors: ['Azul', 'Preto', 'Vermelho', 'Verde', 'Amarelo', 'Branco', 'Cinza'],
		otherColor: '',
		colorError: false, 
		form: {
		  codigo: '',
		  designacao: '',
		  preco: 0,
		  estilo: '',
		  cores: [],
		  tamanho: '',
		  imagem: '',
		  idLoja: 1,
		  nrdisponiveis: 0,
		  tipo: '',
		  disponibilidade: ''
		}
	  };
	},
	methods: {
	  addItem() {
		this.colorError = false;

		if (this.form.cores.length === 0) {
			this.colorError = true;
			return;
		}

		let cores = [...this.form.cores];
		if (this.form.cores.includes('Other') && this.otherColor.trim() !== '') {
			cores = cores.filter(color => color !== 'Other');
			cores.push(this.otherColor.trim());
		}
		const payload = { ...this.form, cores };
		axios.post('http://localhost:8888/api/catalogoService/addItem/Peca', payload)
		.then(response => {
			console.log('Peça adicionada com sucesso:', response.data);
			alert('Peça adicionada com sucesso.');
			this.resetForm();
		})
		.catch(error => {
			console.error('Erro ao adicionar a peça:', error);
			alert('Erro ao adicionar a peça.');
		});
	  },
	  resetForm() {
		this.form = {
		  codigo: '',
		  designacao: '',
		  preco: 0,
		  estilo: '',
		  cores: [],
		  tamanho: '',
		  imagem: '',
		  idLoja: '',
		  nrdisponiveis: 0,
		  tipo: '',
		  disponibilidade: ''
		};
		this.otherColor = '';
		this.colorError = false;
	  },
	  goToMenu() {
		this.$router.push('/menu'); 
	  }
	}
  };
</script>

<style lang="css" scoped>
@import '../assets/AddPartView.css';
</style>
