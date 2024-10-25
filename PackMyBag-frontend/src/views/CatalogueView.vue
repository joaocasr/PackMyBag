<template>
	<main>
		<NavBarComponent></NavBarComponent>
	<div class="cataloguepage">

    		<div class="parent">
      			<div class="div">1</div>
      			<div class="div1">2</div>
      			<div class="div2">3</div>
      			<div class="div3">.</div>
      			<div class="div4">.</div>
      			<div class="div5">.</div>
      			<div class="div6">6</div>
      			<div class="rectangle-parent">
						<img class="group-item" alt="" src="/CatalogueIMG/nextbtn.svg">
      			</div>
			</div>
			<div class="my-overlap-group">
                <div class="item-row">
					<!--v-for com itens vindos da api-->	
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
					<ItemCatalogueView></ItemCatalogueView>
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
            						<button class="buttonbasetext-md">Apply</button>
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
export default {
	data(){
		return{
			value: [0,1000],
			selectedOption:'',
			typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' },{ label: 'Child', value: 'Child' }]
			
		}
	},
	components: {
		NavBarComponent,
		FooterComponent,
		ItemCatalogueView,
		Slider,
		VueSelect
	}
}
</script>

<style scoped src="../assets/CatalogueView.css"></style>
<style src="@vueform/slider/themes/default.css"></style>