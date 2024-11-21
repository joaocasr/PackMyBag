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
    </div>
</template>
<script>
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
        }
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