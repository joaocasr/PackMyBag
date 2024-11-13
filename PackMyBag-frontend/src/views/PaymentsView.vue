<template>
    <NavBarComponent></NavBarComponent>
    <div class="paymentspage">
        <div class="payments">Payments</div>
        <img class="paymentspage-child" alt="" src="/PaymentsIMG/Line 17.png">
        <div class="payment-container">
            <div v-for="p in payments">
                <PaymentComponent
                :codigo="p.codigo"
                :total="p.total"
                :localEntrega="p.localEntrega"
                :inicioAluguer="p.inicioAluguer"
                :fimAluguer="p.fimAluguer"
                :modoPagamento="p.modoPagamento"
                :dataGeracao="p.dataGeracao"
                :estado="p.status"
                ></PaymentComponent>
            </div>            
        </div>
    </div>

    <FooterComponent></FooterComponent>
</template>
<script>
import FooterComponent from '@/components/FooterComponent.vue';
import NavBarComponent from '@/components/NavBarComponent.vue';
import PaymentComponent from '@/components/PaymentComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
    components:{
        FooterComponent,
        NavBarComponent,
        PaymentComponent
    },
    data(){
        return {
            username:'',
            token:null,
            payments:[]
        }
    },
    created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username=token.username;
            this.getPayments();
		}
        else this.$router.push({path:'/login'})
	},
    methods:{
        getPayments(){
            axios.get('http://localhost:8888/api/cartService/transactions/'+this.username).then(payments=>{
                this.payments = payments.data;
                console.log(this.payments);
            }).catch(err=>{
                console.log(err);
            })
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/payments.css';
</style>