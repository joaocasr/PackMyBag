<template>
    <div class="payment">
        <div class="payment-c01-asdx">PAYMENT: {{codigo}} TOTAL: {{ total }} Eur</div>
        <div class="status-pending">
        <span>Status: </span>
        <span v-bind:style="{color:getColor}">{{ estado }}</span>
        </div>
        <div class="generated-13112024-11h17">Generated: {{ dataGeracao }}</div>
        <div v-if="localEntrega!==''" class="deliver-braga">Deliver: {{ localEntrega }}</div>
        <div v-if="inicioAluguer!==''" class="arrival-20112024">Arrival: {{ inicioAluguer }}</div>
        <div v-if="fimAluguer!==''" class="departure-25112024">Departure: {{ fimAluguer }}</div>
        <button v-if="estado==='PENDING'" @click="finishPayment" class="finish-wrapper">FINISH</button>
        <button v-if="estado==='PENDING' && modoPagamento==='PAYPAL'" @click="handlePayment" style="margin-top: 55px;" class="finish-wrapper">FINISH PAYPAL</button>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    props:{
        codigo:String,
        total:Number,
        localEntrega:String,
        inicioAluguer:String,
        fimAluguer:String,
        modoPagamento:String,
        dataGeracao:String,
        estado:String
    },
    data(){
    return{
            colorStatus: ''
        }
    },
    methods:{
        finishPayment(){
            this.$emit('finishPayment',{"codigo":this.codigo, "total": this.total });
        },
        async handlePayment(){
            if(this.begindate === '' || this.enddate === '' || this.modoPagamento === ''){
                this.$swal.fire("Complete all the fields!", "", "error");
                return;
            } 
            this.$swal.fire({
                title: "Are you sure you want to pay with paypal?",
                showDenyButton: false,
                showCancelButton: true,
                confirmButtonText: "Yes"
            }).then(async (result) => {
                if (result.isConfirmed) {
                    if(this.modoPagamento === 'PAYPAL'){
                        try{
                            // Enviar solicitação de pagamento PayPal
                            const paymentData = {
                                method: "paypal",
                                currency: "EUR",
                                description: "Compra na Pack My Bag",
                                amount: this.total
                            };
                            let response = await axios.post(this.$apiGatewayUrl+'/api/cartService/paypal/create', paymentData);
                            if (response.data) {
                                window.location.href = response.data;
                                this.$emit('finishPayment',{"codigo":this.codigo, "total": this.total });

                            } else {
                                this.$swal.fire(response.data.error || "Falha ao obter URL de aprovação do PayPal.", "", "error");
                            }
                        } catch(err){
                            this.$swal.fire("Something went wrong!", "", "error");
                            return;
                        }
                    }
                }
            });
        },
    },
    computed:{
        getColor(){
            if(this.estado==="PENDING") this.colorStatus="#ba8e23";
            if(this.estado==="PAYED") this.colorStatus="green";
            return this.colorStatus;
        }
    }
}
</script>

<style lang="css" scoped>
@import '../assets/paymentComponent.css';
</style>