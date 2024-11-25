<template>
	<main>
		<NavBarComponent></NavBarComponent>
	<div class="cataloguepage">

    		<div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
				<div v-if="items.length>0" class="dividx">{{ current_page + 1}}</div>
      			<div class="rectangle-parent">
						<img v-if="items.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
      			</div>
			</div>
			<div class="my-overlap-group">
                <div class="item-row">
					<!--v-for com itens vindos da api-->	
					<div class="noitemsclass" v-if="items.length==0">No items for the specified parameters.</div>
					<div v-for="item in items">
						<ItemCatalogueView
							:imgSrc="item.imagem"
							:descricao="item.designacao"
							:preco="item.preco"
							:cor="item.cor"
							:codigo="item.codigo"
							:iditem="item.iditem"
							:role="this.role"
							:itemID="item.iditem"
							@item_delete="removeItem"
					></ItemCatalogueView>
					</div>
				</div>
			</div>

			<div class="cataloguepage-child">
    		</div>
    		<div class="genderfilter">
      			
			
					<div class="text">Gender</div>
					<VueSelect
    v-model="selectedOption"
    :options="typeOptions" placeholder="Select a type"/>
    		</div>
    		<div class="layoutcollablse-heading-parent">
      			<div class="layoutcollablse-heading">
					<div class="text1">Price range</div>
					
      			</div>
      			<div class="formrange-parent">
					<div class="sliderclass"><Slider :max="1000" v-model="value" /></div>
					

        				<div class="formlabelinput">
          					<div class="formbaselabel-title">
            						<div class="label1">Min</div>
          					</div>
        				</div>
        				<div class="formlabelinput1">
          					<div class="formbaselabel-title">
            						<div class="label2">Max</div>
          					</div>
        				</div>
        				<div class="buttonbtn-basic">
            						<button @click="searchItems()" class="buttonbasetext-md">Apply</button>
        				</div>
      			</div>
    		</div>
  	</div>
	<FooterComponent></FooterComponent>
	</main>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import ItemCatalogueView from '@/components/ItemCatalogueView.vue';
import Slider from '@vueform/slider';
import VueSelect from "vue3-select-component";
import axios from 'axios';
import { useRoute } from 'vue-router';
import authService from '@/services/auth-service';
import authHeader from '@/services/auth-header';

export default {
	data(){
		return{
			value: [0,1000],
			selectedOption:'',
			typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' },{ label: 'Child', value: 'Child' }],
			items: [],
			filtered: [],
			current_page:0,
			isquery:false,
			queryname:"",
			idloja:null,
			role: null,
			code: null,
		}
	},
	components: {
		NavBarComponent,
		FooterComponent,
		ItemCatalogueView,
		Slider,
		VueSelect
	},
	created(){
		let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.username = token.username;
			this.role = token.role;
			this.idloja = token.idloja;
		}

		const route = useRoute()

		if(route.query.q!==undefined){
			this.isquery = true;
			this.queryname = route.query.q;
			this.getQueryResult(this.queryname);
		}
		else this.getCatalogueItems()
	},
	methods: {
		getCatalogueItems(){
			console.log("fecth items normally")
			axios.get('http://localhost:8888/api/catalogoService/lojas/' + this.idloja + '?page='+this.current_page+"&number=12")
			.then(resp=>{
				this.items = resp.data;
				console.log(this.items);
			}).catch(err=>{
				console.log(err)
			})
		},
		getQueryResult(name){
			axios.get('http://localhost:8888/api/catalogoService/allitems?designacao='+name+'&page='+this.current_page+"&number=12")
			.then(resp=>{
				this.items = resp.data;
			}).catch(err=>{
				console.log(err)
			})
		},
		handlePage(action){
			if(action=='next') this.current_page+=1;
			else this.current_page-=1;
			this.getItemsperTypeAndPrice(this.selectedOption,this.value[0],this.value[1])
		},
		getItemsperTypeAndPrice(tipo,min,max){
			if(tipo!='' && this.isquery===false){
				axios.get('http://localhost:8888/api/catalogoService/type/'+tipo+'/price?min='+min+'&max='+max+'&page='+this.current_page+"&number=12")
				.then(resp=>{
					this.items = resp.data;
					console.log(this.items)
				}).catch(err=>{
					console.log(err);
				})
				return;
			}
			if(tipo!='' && this.isquery===true){
				axios.get('http://localhost:8888/api/catalogoService/type/'+tipo+'/price/'+ this.queryname +'?min='+min+'&max='+max+'&page='+this.current_page+"&number=12")
				.then(resp=>{
					this.items = resp.data;
					console.log(this.items)
				}).catch(err=>{
					console.log(err);
				})
				return;
			}
			if(this.isquery===true){
				console.log("entrou bem")
				axios.get('http://localhost:8888/api/catalogoService/price/'+this.queryname+'?min='+min+'&max='+max+'&page='+this.current_page+"&number=12")
				.then(resp=>{
					this.items = resp.data;
				}).catch(err=>{
					console.log(err);
				})
				return;
			}
			if(this.isquery===false){
				axios.get('http://localhost:8888/api/catalogoService/price?min='+min+'&max='+max+'&page='+this.current_page+"&number=12")
				.then(resp=>{
					this.items = resp.data;
				}).catch(err=>{
					console.log(err);
				})
				return;
			}
		},
		searchItems(){
			this.current_page = 0;
			this.getItemsperTypeAndPrice(this.selectedOption,this.value[0],this.value[1])
		},
		async removeItem(id){
			console.log(id);
            const result = await this.$swal.fire({
				title: "Do you want to remove the item from your store?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Remove"
			});
			if (result.isConfirmed) {
				let r = await this.deleteItem(id);
				if (r && r.status == 200) {
					this.$swal.fire("Removed! The item was removed from your store.", "", "success");
                    this.$emit('itemRemoved',this.index)
				} else {
					let msg="";
					if(r.response) msg = r.response.data.message;
					this.$swal.fire("Something went wrong! "+msg, "", "error");
				}
			}
        },
		async deleteItem(id) {
			try {
				let data = {};
				const header = authHeader();
				header['Content-Type'] = 'application/json';

				const resp = await axios.delete('http://localhost:8888/api/catalogoService/deleteItem/'+id,
				{ data, headers: header }
				);
				this.items = this.items.filter((i) => i.iditem !== id);
				return resp;
			} catch (err) {
				console.log(err);
				return err;
			}
		}

	},
	watch:{
		selectedOption: function(newvalue,oldvalue) {
			this.current_page = 0;
			if(newvalue==undefined) this.selectedOption='';
			this.getItemsperTypeAndPrice(this.selectedOption,this.value[0],this.value[1]);
		},
		'$route.query.q':function(newvalue,oldvalue){
			console.log(newvalue);
			this.getQueryResult(newvalue);
		}
	}
}
</script>

<style scoped src="../assets/CatalogueView.css"></style>
<style src="@vueform/slider/themes/default.css"></style>