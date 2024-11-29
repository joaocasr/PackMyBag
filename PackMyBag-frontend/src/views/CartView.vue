<script setup>
const minDate = new Date();
minDate.setDate(minDate.getDate() + 5); // 5 dias de antecedencia
</script>

<template>
    <NavBarComponent></NavBarComponent>
  	<div class="encomendaspage">
    		<div class="shopping-cart">Shopping Cart</div>
    		<img class="encomendaspage-child" alt="" src="/CartIMG/Line 17.png">
    		

            <div v-if="itemsEncomenda.length!=0" class="shop-cart-frame">
                <div v-bind:class="{ hide: !preCheck }" class="cart-main-section">
                    <div class="product-parent">
                        <div class="product">Product</div>
                        <div class="price">Price</div>
                        <div class="price">Quantity</div>
                        <div class="price">Subtotal</div>
                    </div>
                </div>
            </div>
            <div class="infoEmpty" v-if="itemsEncomenda.length==0">Your cart is empty. Go rent some clothes!</div>
            <div v-bind:class="{ hide: !preCheck }" v-for="(item, key) in itemsEncomenda">
                <CartItemComponent
                    :codigo = "item.codigo"
                    :nome = "item.designacao"
                    :preco = "item.price"
                    :image = "item.imagem"
                    :quantidade = "item.nraquisicoes"
                    :idloja = "item.idloja"
                    :username = "username"
                    :index = key
                    @newQuantity="changeQuantity($event,key)"
                    @itemRemoved="removeItem($event,key)">
                </CartItemComponent>
            </div>

            <div v-if="itemsEncomenda.length!=0" class="form-section" v-bind:class="{ hide: preCheck }">
                <button class="button2" @click="changePrevious()">BACK</button>
                <input v-model="addressInput" class="searchAddress" placeholder="Please indicate the address of your staying..." />
                <button @click="getLocation()" class="addressbtn">GO</button>


                <l-map ref="map" :use-global-leaflet="false" v-model:zoom="zoom" style="left:50px; top: 50px; width: 500px;height: 300px;z-index:0;" :center="centermap">
                    <l-tile-layer
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        layer-type="base"
                        name="OpenStreetMap"
                    ></l-tile-layer>

                    <l-marker :lat-lng="markCoords" ></l-marker>
                    
                </l-map>

                <!--<div class="map" id="map" style="left:50px; top: 50px; width: 500px;height: 300px;" ></div>-->
                <div class="deliverylocation">
                   <h4> DELIVERY LOCATION </h4>
                </div>
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

            <div v-if="checkedAddress.length!=0 && itemsEncomenda.length!=0" class="payment-container">
                <h4>PAYMENT</h4>
                <div class="payment-options">
                <div @click="paymentMode('paypal')" class="payment-option" v-bind:style="{'border-color':paymentColor1,'border-width': paymentBorder1}">
                    <p>PayPal</p>
                    <img width="20px" height="20px" src="/CartIMG/paypal.png" alt="PayPal" />
                </div>
                <div @click="paymentMode('credit')" class="payment-option" v-bind:style="{'border-color':paymentColor2,'border-width': paymentBorder2}">
                    <p>Credit Card</p>
                    <img width="20px" height="20px" src="/CartIMG/creditcard.png" alt="Cartão de Crédito" />
                </div>
                </div>
            </div>

            <div v-if="itemsEncomenda.length!=0" class="summary">
                <div class="summary-title">Summary</div> 
                <div v-for="(i, idx) in itemsEncomenda">
                    <p class="summary-details">x{{ i.nraquisicoes }}&emsp;&emsp;{{ i.designacao }}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ getSubTotal(idx) }}€</p>                 
                </div>
                <br>
                <p class="summary-details">{{itemsEncomenda.length}} items</p>
                <p class="summary-details">{{ taxaDias }}</p>
                <p class="summary-details">Total:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{ getTotal }}€</p>
            </div>
    		<div v-if="itemsEncomenda.length!=0" class="button-parent">      			
        		<button v-if="preCheck==false" @click="handlePayment()" class="button1">Checkout</button>
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
//import { Loader } from '@googlemaps/js-api-loader';
import authService from '@/services/auth-service';
import authHeader from '@/services/auth-header';
import axios from 'axios';
import "leaflet/dist/leaflet.css";
import { LMap, LMarker, LTileLayer } from "@vue-leaflet/vue-leaflet";


export default {
    components:{
        NavBarComponent,
        FooterComponent,
        CartItemComponent,
        Calendar,
        LMap,
        LTileLayer,
        LMarker
    },
    data(){
        return {
            preCheck:true,
            zoom:5,
			token:null,
			username:'',
            color:"red",
            begindate:'',
            enddate:'',
            checkedAddress:'',
            addressInput:'',
            taxaDias:'',
            totalPagamento:0,
            paymentBorder1:'thin',
            paymentColor1: '',
            paymentBorder2:'thin',
            paymentColor2: '',
            modoPagamento:'',
            adicional:0,
            itemsEncomenda : [],
            markCoords:[0,0],
            centermap:[39.3999, -8.2245]
        }
    },
    computed:{
        getTotal(){
            let t = 0;
            for(let i=0; i<this.itemsEncomenda.length;i+=1){
                t += this.itemsEncomenda[i].nraquisicoes * this.itemsEncomenda[i].price;
            }
            t+=this.adicional;
            this.totalPagamento = t;
            return t;
        }
        
    },
    async created(){
        let token = authService.getToken();
        if(token!=null){
			this.token = token;
			this.username=token.username;
            this.getCartItems();
		}
        
        //this.loadMap();
    },
    watch:{
        begindate:function(newv,oldv){
            if(this.enddate!==''){
                let date1 = new Date(newv);
                let date2 = new Date(this.enddate);

                let Difference_In_Time =
                    date2.getTime() - date1.getTime();

                let nrDays =Math.round(Difference_In_Time / (1000 * 3600 * 24));
                            
            
                this.adicional =  0.5 * nrDays;
                this.totalPagamento = this.adicional + this.totalPagamento; //já não me lembro qual era a formula
                this.taxaDias = ' + '+ this.adicional + '€ (0.5 per day) -  Rental Time' 
            }
        },
        enddate:function(newv,oldv){
            if(this.begindate!==''){
                let date1 = new Date(this.begindate);
                let date2 = new Date(newv);

                let Difference_In_Time =
                    date2.getTime() - date1.getTime();

                let nrDays =
                    Math.round
                        (Difference_In_Time / (1000 * 3600 * 24));
                            
            
                this.adicional =  0.5 * nrDays;
                this.totalPagamento = this.adicional + this.totalPagamento; //já não me lembro qual era a formula
                this.taxaDias = ' + '+this.adicional + '€ (0.5 per day) -  Rental Time' 
            }
        }
    },
    methods:{
        changeQuantity(event,key){
            this.itemsEncomenda[key].nraquisicoes = event;
        },
        getSubTotal(idx){
            let t = 0;
            t = this.itemsEncomenda[idx].nraquisicoes * this.itemsEncomenda[idx].price;
            return t;
        },
        changeNext(){
            this.preCheck = false;
        },
        changePrevious(){
            this.preCheck=true;
        },
        paymentMode(mode){
            if(mode==='paypal'){
                this.paymentColor2='';
                this.paymentBorder2='thin'
                this.paymentColor1='blue';
                this.paymentBorder1='thick';
                this.modoPagamento = 'PAYPAL';
            }
            if(mode==='credit'){
                this.paymentColor1='';
                this.paymentBorder1='thin'
                this.paymentColor2='blue';
                this.paymentBorder2='thick';
                this.modoPagamento = 'CREDIT';
            }
        },
        getCartItems(){
            axios.get(this.$apiGatewayUrl+'/api/cartService/'+this.username).then(items=>{
                this.itemsEncomenda = items.data;
            }).catch(err=>{
                console.log(err)
            })
        },
        getLocation(){
            axios.get('https://nominatim.openstreetmap.org/search?q='+this.addressInput+'&format=json')
            .then(resp=>{
                this.checkedAddress = resp.data[0].display_name;
                let latitude = resp.data[0].lat;
                let longitude = resp.data[0].lon;
                this.centermap=[latitude,longitude];
                this.zoom = 14;
                this.markCoords = [latitude,longitude];
            }).catch(err=>{
                console.log(err);
            })
        },
        removeItem(event,idx){
            this.itemsEncomenda = this.itemsEncomenda.filter((item,index) => index !== idx);
        },
        async handlePayment(){
            if(this.begindate==='' || this.enddate==='' || this.modoPagamento===''){
                this.$swal.fire("Complete all the fields!", "", "error");
                return;
            } 
            const result = await this.$swal.fire({
				title: "Are you sure you want to procede and generate the payment?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if (result.isConfirmed) {
                try{
                    let r = await this.generatePayment();
                    if (r && r.status == 200) {
                            this.$swal.fire({
                                title: "Success! A new payment was generated for your order.",
                                type: "success",
                                showConfirmButton: true,
                                confirmButtonText:"Payments",
                                showCancelButton:true
                                }).then((result) => {
                            if (result.isConfirmed) {
                                this.$router.push({path:'/payments'});
                            }
                            });
                        this.itemsEncomenda=[];
                    }
                    
                }catch(err){
                    this.$swal.fire("Something went wrong!", "", "error");
                    return;
                }
			}
        },
        async generatePayment(){
            const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
            
            let itens = [];
            this.itemsEncomenda.forEach((x) =>
                itens.push({"codigo":x.codigo,"idloja":x.idloja,"quantidade":x.nraquisicoes})
            )
            let itensObj = {
                "itens" : itens
            }
            let dataGeracao = new Date();
            try{
                let r = await axios.post(this.$apiGatewayUrl+'/api/cartService/order',
                    {
                        "username":this.username,
                        "itensObj":itensObj,
                        "localEntrega":this.checkedAddress,
                        "inicioAluguer":this.begindate,
                        "fimAluguer":this.enddate,
                        "modoPagamento":this.modoPagamento,
                        "dataGeracao": dataGeracao.toISOString(),
                        "total":this.totalPagamento
                    },
                    config
                );
                return r;
            }catch(err){
                let msg="";
                if(err.response) msg = err.response.data.error.message;
                this.$swal.fire("Something went wrong! "+msg, "", "error");
            }
        }
    }
}
</script>
<style lang="css" scoped>
@import '../assets/cart.css';
</style>