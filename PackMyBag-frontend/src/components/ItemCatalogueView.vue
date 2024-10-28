<template>
            <div class="itemContainer">
                <div class="itemImg">
        				<img class="imageIcon" alt="" :src="imgSrc">
      			</div>
                <div class="container-descricao">
                    <div class="container-descricao2">
                            <a :href="`/items/${this.iditem}`" class="cutwork-poplin-dress">{{descricao}}</a>
                            <p class="rs995000">Eur(€){{preco}}</p>
                    </div>
                    <div class="b4-parentDetails">
        				<div v-for="color in getColors" class="b4Details">
          					<div :style="{backgroundColor:color}" class="b4-childDetails">
          					</div>
        				</div>

      		    	</div>
    		    </div>
        </div>

</template>
<script>
export default {
	props: {
		imgSrc: String,
		descricao: String,
		preco: Number,
		cor: String,
		iditem:Number
  	},
	data(){
		return{
			colorMap : {}
		}
	},
	computed:{
		getColors(){
			let cores = this.cor.split("/");
			console.log(cores)
			let colors= cores.map((x) => colorMap[x.toLowerCase()]);

			return colors;
		}
	}
}

const colorMap={
			"castanho":"#563c1c",
			"branco":"white",
			"cinzento": "gray",
			"preto":"black",
			"amarelo":"yellow",
			"cinzento-vigoré":"#A9A9A9",
			"branco-marfim":"#FFFFF0",
			"azul-celeste":"#87CEEB",
			"antracite-escuro":"#36454F",
			"cimento":"#BFBFBF",
			"lilás":"#C8A2C8",
			"laranja":"#f23916",
			"azul-elétrico":"#0070FF",
			"manteiga": "#FFF4B1", 
			"linho": "#FAF0E6",  
			"vermelho": "#FF0000",
			"mostarda": "#FFDB58", 
			"azul":"#0000FF",
			"púrpura":"#800080",
			"torrado":"#8B4513",
			"vinho":"#722F37",
			"cru":"#FAF0E6",
			"bege": "#F5F5DC",        
			"azul-celeste": "#87CEEB",  
			"azul-marinho": "#000080",  
			"cinzento-antracito": "#2F2A29",
			"magenta": "#FF00FF",       
			"malva": "#B784A7", 
			"viscose": "#D6D5E0",  
			"lavanda": "#E6E6FA",  
			"azul-lavado": "#A3C1DA",    
			"verde": "#008000",           
			"verde-claro": "#90EE90",    
			"sea":"#C2DFFF",
			"borgonha": "#800020",
			"azul-marinho-escuro": "#001F3F",  
			"verde-garrafa": "#005B5B",
			"verde-água" : "#20B2AA",   
			"caqui": "#C3B091",         
			"grená": "#7E1F3D",          
			"castanho-claro":"#D2B48C",
			"telha":"#B65C36",
			"gelo":"#E0F7FA","vison": "#A99C8D",     
			"chumbo": "#7E7B7A",      
			"rosa-pálido": "#FFB6C1", 
			"carvão": "#333333",     
			"areia": "#D1B29D",       
			"rosa": "#FF69B4",       
			"leopardo": "#D4B94B",   
			"listrado": "#22395c",    
			"cava": "#E6B24E" ,
			"framboesa": "#E30B5C",
			"cinzento-pérola": "#C0C0C0",
			"turquesa": "#40E0D0",
			"baunilha": "#F3E5AB",
			"camel-escuro": "#C19A6B",
			"azul-médio": "#007BFF"
}
</script>
<style scoped src="../assets/itemcatalogue.css"></style>
