<template>
    <div class="payment">
        <div class="payment-c01-asdx">PAYMENT: {{codigo}} TOTAL: {{ total }} Eur</div>
        <div class="status-pending">
        <span>Status: </span>
        <span class="pending">{{ estado }}</span>
        </div>
        <div class="generated-13112024-11h17">Generated: {{ dataGeracao }}</div>
        <div class="deliver-braga">Deliver: {{ localEntrega }}</div>
        <div class="arrival-20112024">Arrival: {{ inicioAluguer }}</div>
        <div class="departure-25112024">Departure: {{ fimAluguer }}</div>
        <button class="finish-wrapper">FINISH</button>
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
    }
}
</script>

<style lang="css" scoped>
@import '../assets/paymentComponent.css';
</style>