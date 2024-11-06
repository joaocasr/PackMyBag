<script setup>
const minDate = new Date();
minDate.setDate(minDate.getDate() + 5); // 5 dias de antecedencia
</script>

<template>
    <NavBarComponent></NavBarComponent>
  	<div class="encomendaspage">
    		<div class="shopping-cart">Shopping Cart</div>
    		<img class="encomendaspage-child" alt="" src="/CartIMG/Line 17.png">
    		

            <div class="shop-cart-frame">
                <div v-bind:class="{ hide: !preCheck }" class="cart-main-section">
                    <div class="product-parent">
                        <div class="product">Product</div>
                        <div class="price">Price</div>
                        <div class="price">Quantity</div>
                        <div class="price">Subtotal</div>
                    </div>
                </div>
            </div>
            <div v-bind:class="{ hide: !preCheck }" v-for="(item, key) in itemsEncomenda">
                <CartItemComponent
                :codigo="item.codigo"
                :nome="item.nome"
                :preco="item.preco"
                @newQuantity="changeQuantity($event,key)"
                ></CartItemComponent>
            </div>

            <div class="form-section" v-bind:class="{ hide: preCheck }">
                <button class="button2" @click="changePrevious()">BACK</button>
                <input v-model="address" class="searchAddress" placeholder="Please indicate the address of your staying..." />
                <button @click="getLocation()" class="addressbtn">GO</button>
                <div class="map" id="map" style="left:50px; top: 50px; width: 500px;height: 300px;" ></div>
                <div v-if="checkedAddress.length!=0" class="deliveryClass">
                    <img src="/CartIMG/validated.jpg" width="40px" height="40px"/>
                    <div class="period-title2">Delivery Location</div>
                    <p>Address: {{ checkedAddress }}</p>
                    <strong class="period-title">Please indicate how long you will be traveling:</strong> 
                    <p>ARRIVAL</p>
                    <Calendar v-model="begindate" dateFormat="dd/mm/yy" :minDate="minDate" showIcon :showOnFocus="false" />
                    <br>
                    <p>DEPARTURE</p>
                    <Calendar v-model="enddate" dateFormat="dd/mm/yy" :minDate="minDate" showIcon :showOnFocus="false" />
                </div>
            </div>


            <div class="summary">
                <div class="summary-title">Summary</div> 
                <div v-for="(i, idx) in itemsEncomenda">
                    <p class="summary-details">x{{ i.quantity }}&emsp;&emsp;{{ i.nome }}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ getSubTotal(idx) }}€</p>                 
                </div>
                <br>
                <p class="summary-details">{{itemsEncomenda.length}} items</p>
                <p class="summary-details">Total:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ getTotal }}€</p>
            </div>
    		<div class="button-parent">      			
        		<button v-if="preCheck==false" class="button1">Checkout</button>
        		<button v-if="preCheck==true" class="button1" @click="changeNext()">Next</button>
    		</div>
  	</div>
    <FooterComponent></FooterComponent>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import CartItemComponent from '@/components/CartItemComponent.vue';
import Calendar from 'primevue/calendar';
import { Loader } from '@googlemaps/js-api-loader';

let map;
let key = `${import.meta.env.VITE_API_KEY}`;

const options = {
    apiKey:key,
    version: "weekly",
    libraries: ["places"]
}
const loader = new Loader(options);

export default {
    components:{
        NavBarComponent,
        FooterComponent,
        CartItemComponent,
        Calendar
    },
    data(){
        return {
            preCheck:true,
            mapOptions : {
                center: 
                    { lat: 41.553, lng: -8.425 
                },
                zoom: 20
            },
            begindate:'',
            enddate:'',
            checkedAddress:'',
            itemsEncomenda : [{"nome":"Cutwork Poplin Dress","preco":23.2,"codigo":"P1","idloja":1, "quantity":1},{"nome":"Cutwork Poplin Dress","preco":23.2,"codigo":"P2","idloja":1,"quantity":1}]
        }
    },
    computed:{
        getTotal(){
            let t = 0;
            for(let i=0; i<this.itemsEncomenda.length;i+=1){
                t += this.itemsEncomenda[i].quantity * this.itemsEncomenda[i].preco;
            }
            return t;
        }
        
    },
    async created(){
        this.loadMap();
    },
    methods:{
        changeQuantity(event,key){
            this.itemsEncomenda[key].quantity = event;
        },
        getSubTotal(idx){
            console.log(idx);
            let t = 0;
            t = this.itemsEncomenda[idx].quantity * this.itemsEncomenda[idx].preco;
            return t;
        },
        changeNext(){
            this.preCheck = false;
        },
        changePrevious(){
            this.preCheck=true;

        },
        async getLocation(){
            loader.load().then(() => {

                const geocoder = new google.maps.Geocoder();
                const address = this.address;


                geocoder.geocode({ address: address }, (results, status) => {
                    if (status === "OK") {

                        console.log(results[0]);
                    

                        const location = results[0].geometry.location;
                        map.setCenter(location);
                        this.checkedAddress=results[0].formatted_address;

                    } else {
                        console.error("error", status);
                    }
                });
                }).catch(e => {
                    console.error(e);
                });
        },
        async loadMap(){
            loader.loadCallback(async e => {
                if (e) {
                    console.log(e);
                } else {
                    map = new google.maps.Map(document.getElementById("map"), this.mapOptions);
                }
            });
        }
    }
}
</script>
<style lang="css" scoped>
@import '../assets/cart.css';
</style>