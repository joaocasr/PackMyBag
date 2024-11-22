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
        <div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
				<div v-if="payments.length>0" class="dividx">{{ current_page + 1}}</div>
      			<div class="rectangle-parent">
						<img v-if="payments.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
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
            current_page:0,
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
            axios.get('http://localhost:8888/api/cartService/transactions/'+this.username+"?page="+this.current_page+"&number=5").then(payments=>{
                this.payments = payments.data;
                console.log(this.payments);
            }).catch(err=>{
                console.log(err);
            })
        },
        async completePayment(payment){
            console.log(payment);
            let paymentType = payment.codigo.substring(0,4);
            let ptype = "CART";
            if(paymentType==="FORM") ptype = "FORM";
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
                console.log(r);

                if(r.status==200){
                    payment.status="PAYED";
                    let msg = "Your order was received. See you soon!";
                    let msgtitle =  "Your cart order was payed!";
                    if(ptype==="FORM"){
                        msg = "Your form was sent to the stylist!";
                        msgtitle =  "Your form was payed!";
                    } 
                    this.$swal({
                        icon: "success",
                        title: msgtitle,
                        text: msg
                    });
                    let indx = this.payments.findIndex(p=>p.codigo===payment.codigo);
                    if(indx!==-1){
                        this.payments[indx].status = "PAYED";
                    }
                }
				//return r;
			}catch(err){
				console.log(err);
				return err;
			}
   
        },
        handlePage(action){
			if(action=='next') this.current_page+=1;
			else this.current_page-=1;
			this.getPayments();
		}
    }
}
</script>


<style lang="css" scoped>
@import '../assets/payments.css';
</style>