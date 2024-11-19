<template>
    <div class="encomenda">
      <div class="encomenda-inner">
        <div class="encomenda-inner">
          <div class="encomenda-main-section">
            <div class="pedido-efetuado-parent">
              <div class="pedido-efetuado">Pedido Efetuado </div>
              <div class="preo">Preço</div>
              <div class="preo">Status</div>
              <div class="preo">Número da Encomenda</div>
            </div>
            <div class="div">{{ deliveryDate }}</div>
            <div class="div1">€ {{ orderPrice }}</div>
            <div class="entregue">{{ status }}</div>
            <div class="apt091231312">{{ orderCode }}</div>
          </div>
          <div class="cart-section">
            <div class="order-item-images">
              <div 
                v-for="(item, index) in itens" 
                :key="index" 
        
                class="mini-image" 
                alt=""
              >{{ item.codigo }}</div>
            </div>
            <div class="conjunto-verao-"> {{ conjuntoDescricao }}</div> 
            <div class="button-wrapper">
              <button class="button" @click="goToOrderDetails(orderCode)">Ver detalhes da encomenda</button>
            </div>
            <div class="numero-de-artigos">Número de artigos: {{ this.itens.length }} </div>
          </div>
        </div>
      </div>
      <!--<img class="image-18-icon1" alt="" src="">-->
    </div>
  </template>

<script>
import axios from 'axios';

export default {
  props: {
    orderCode: String,
    deliveryDate: String,
    returnDate: String,
    deliveryLocation: String,
    status: String,
    customerId: String,
    storeId: Number,
    orderPrice: Number,
    deliveryFee: Number,
    itens: Array
    },
    computed: {
      conjuntoDescricao() {
      if (Array.isArray(this.itens)) {
        return `Conjunto com ${this.itens.length} peças`;
      } else {
        return 'Conjunto de 0 peças';
      }
    },
    },
    methods: {
      goToOrderDetails() {
        this.$router.push({ name: 'ordersdetails', params: { orderCode: this.orderCode } });
      }
    },
    
    mounted() {
    // console.log('Props Recebidos:', {
    //   orderCode: this.orderCode,
    //   deliveryDate: this.deliveryDate,
    //   returnDate: this.returnDate,
    //   deliveryLocation: this.deliveryLocation,
    //   status: this.status,
    //   customerId: this.customerId,
    //   storeId: this.storeId,
    //   orderPrice: this.orderPrice,
    //   itens: this.itens
    // });
  }
};
</script>

<style lang="css" scoped>
@import '../assets/OrderComponentView.css';
</style>