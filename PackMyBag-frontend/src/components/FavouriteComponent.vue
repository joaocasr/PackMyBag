<template>
  	<div class="favouritecomponent">
    		<div class="divclass">€ {{ preco }}</div>
    		<img class="image-18-icon" alt="" :src="imagem">
    		
    		<div class="cutwork-poplin-dress"><a :href="`/items/${idItem}`">{{nome}}</a></div>
    		<img @click="handleRemoveFavourite" class="icon-cancel" alt="" src="/FavouritesIMG/icon-cancel.svg">
    		
    		<div class="in-stock">{{disponibilidade}}</div>
  	</div>
</template>

<script>
import axios from 'axios';
export default {
    props:{
        preco:Number,
        imagem:String,
        nome:String,
        disponibilidade:String,
        idItem:Number,
        username:String,
        codigo:String,
		idloja:Number
    },
    methods:{
        async handleRemoveFavourite() {
            console.log(this.username);
            console.log(this.codigo);
            console.log(this.idloja);
			const result = await this.$swal.fire({
				title: "Do you want to remove the item '" + this.nome.toLowerCase() + "' from your list of favourites?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Remove"
			});
			if (result.isConfirmed) {
				let r = await this.removeFromFavourites();
				if (r && r.status == 200) {
					this.$swal.fire("Sucess! The item was removed from your favourites.", "", "success");
                    this.$emit('removedFavourite');
				} else {
					this.$swal.fire("Something went wrong! The item does not belong to your favourites.", "", "error");
				}
			} else if (result.isDenied) {
				this.$swal.fire("Changes are not saved", "", "info");
			}
		},
		async removeFromFavourites() {
			try {
				let data = {username: this.username,itemCode: this.codigo,idLoja: this.idloja} 
				let headers = {
					headers: {
						'Content-Type': 'application/json;charset=UTF-8',
					}
				}
				const resp = await axios.delete('http://localhost:8888/api/favoritosService/removeItem',
				{
					data,
					headers
				}
				);
				console.log(resp);
				return resp;
			} catch (err) {
				console.log(err);
				return err;
			}
		}
    }
}
</script>

<style lang="css" scoped>
@import '../assets/favouritecomponent.css';
</style>
