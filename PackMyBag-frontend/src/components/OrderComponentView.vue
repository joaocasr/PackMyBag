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
            <div v-if="this.token.role === 'Tecnico'" >
              <div class="status-dropdown">
                <select v-model="newStatus">
                  <option v-for="status in statusOptions" :key="status" :value="status">{{ status }}</option>
                </select>
              </div>
              <div class="button-wrapper1">
                <button class="button5" @click="updateStatus()">Alterar Status</button>
              </div>
            </div>
            <div class="apt091231312">{{ orderCode }}</div>
          </div>
          <div class="cart-section">
            <div class="order-item-images">
              <img 
                v-for="(item, index) in itens" 
                :key="index" 
                :src="item.imagemUrl" 
                class="mini-image" 
                alt="Item Image"
              />
            </div>
            <div class="conjunto-verao-"> {{ conjuntoDescricao }}</div> 
            <div class="button-wrapper">
              <button class="button" @click="goToOrderDetails(orderCode)">Ver detalhes da encomenda</button>
            </div>
            <div class="numero-de-artigos">Número de artigos: {{ this.itens.length }} </div>
          </div>
        </div>
      </div>
      <img class="image-18-icon1" alt="" src="">
    </div>
  </template>

<script>
import axios from 'axios';
import authService from '@/services/auth-service';

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
    created() {
      this.getUsername();
    },
    data() {
      return {
        newStatus: null,
        statusOptions: ["Pago", "Processamento", "Enviado", "Entregue", "Devolvido", "Cancelado"],
        role: null,
        token: null
      };
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
      getUsername() {
        let token = authService.getToken();
        console.log(token);
        if (token != null) {
          this.token = token;
          this.role = token.role;
        }
        console.log('Role:', this.role);
      },
      goToOrderDetails() {
        this.$router.push({ name: 'ordersdetails', params: { orderCode: this.orderCode } });
      },
      updateStatus() {
        axios.put(this.$apiGatewayUrl+ `/api/encomendaService/status/${this.orderCode}/${this.newStatus}`)
          .then(response => {
            console.log('Status atualizado:', response.data);
            window.location.reload(); // Refresh the page
          })
          .catch(error => {
            console.error('Erro ao atualizar status:', error);
          });
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