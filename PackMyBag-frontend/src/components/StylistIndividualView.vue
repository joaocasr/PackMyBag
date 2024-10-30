<template>
           
  	<div class="stylistcomponent">
    		<div class="stylistcomponent-child">
    		</div>
    		<div class="image-8-wrapper">
      			<img class="image-8-icon" alt="" src="/StylistsIMG/image 8.png">
      			
    		</div>
    		<div class="stylistcomponent-item">
    		</div>
    		<div class="gonalo-vale">Gonçalo Vale</div>
    		<div class="stylistcomponent-inner">
      			<div class="frame-parent">
        			<Rating id="avrgestrelas" :modelValue="stylistrate" @update:modelValue="stylistrate = $event" readonly/>	
      			</div>
    		</div>
    		<div class="an-expert-in-container">
      			<p class="an-expert-in">An expert in streetwear, I bring a personalized touch to every wardrobe. With a sharp eye for </p>
      			<p class="an-expert-in">trends and a passion for curating looks that reflect individual style, I help clients feel confident and effortlessly chic. Whether it’s for personal</p>
      			<p class="an-expert-in"> shopping, special events, or editorial work, my goal is to make fashion both fun and accessible.</p>
    		</div>
			<button class="vector-icon" @click="goToForms()"></button>
    		
  	</div>


</template>

<script>
import Rating from 'primevue/rating';
export default {
	components:{
		Rating
	},
	data(){
		return{
			stylistrate:3
		}
	},
	methods:{
		goToForms(){
			this.$router.push({ path: '/stylists/forms' });
		}
	}
}
</script>


<style scoped src="../assets/stylistIndividual.css"></style>
