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
                @finishPayment="completePayment"
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
import authHeader from '@/services/auth-header';
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
            nome:'',
            email:'',
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
			this.nome=token.nome;
			this.email=token.email;
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
        },
        async completePayment(payment){
            console.log(payment);
            let paymentType = payment.codigo.substring(0,3);
            let ptype = "CART";
            if(paymentType==="FOR") ptype = "FORM";
            const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
            console.log("going to pay...")
			try{
				const r = await axios.post("http://localhost:8888/api/cartService/pay",
					{
						"codigo":payment.codigo,
						"total":payment.total,
                        "nome":this.nome,
                        "username":this.username,
                        "email":this.email,
                        "ptype":ptype,
						"status":"PAYED",
					},
					config
				)
				return r;
			}catch(err){
				console.log(err);
				return err;
			}
   
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/payments.css';
</style>