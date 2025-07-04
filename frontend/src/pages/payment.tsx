import { CardPayment, initMercadoPago } from '@mercadopago/sdk-react'
import { Background } from '../components/background';
import { Label } from '../components/label';
import Api from '../services/api';
import { useLocation } from 'react-router-dom';

export function Payment () {

    // Enter the mercado pago API public key here
    initMercadoPago("Enter the mercado pago API public key here", {locale: "pt-BR"})

    const location = useLocation()
    const {props} = location.state

    console.log("P -->>", props)

    const totalPrice = props.totalPrice
    const description = "Api Mercado Pago"

    const onSubmit = async (formData) => {

        console.log("F -->>", formData)

        try {
            
            const payment = {
    
                "token": formData.token,
                "issuerId": formData.issuer_id,
                "amount": formData.transaction_amount,
                "CPF": formData.payer.identification.number,
                "description": description,
                "installments": formData.installments,
                "paymentMethodId": formData.payment_method_id, 
                "email": formData.payer.email, 
    
            }

            console.log("F -->>", formData)
            console.log("P -->>", payment)
    
            const response = await Api.post("/payments/pay", payment)
            const data = await response.data
    
            console.log("R -->>", response)
    
            if(data.status == "approved") {

                console.log("PT -->>", data.paymentTypeId)

                //setPaymentType(data.paymentTypeId)

                const teste = data.paymentTypeId == "credit_card" ? "CREDIT" : "DEBIT"

                console.log("TE -->>", teste)

                const paymentDTO = {

                    "paymentMethod": teste,
                    "user_id": props.userId

                }

                console.log("PDTO -->>", paymentDTO)

                const createPayment = await Api.post("/payments", paymentDTO)
                const paymentData = await createPayment.data

                console.log("PD -->>", paymentData)

                const order = {

                    "user_id": props.userId,
                    "payment_id": paymentData.id

                }

                console.log("O -->>", order)

                await Api.post("/orders", order)
    
            }

        } catch (error) {

            console.log(error)
            
        }

    }

    return (

        <div className="h-screen w-full">

            <div>
                <Background opacity="opacity-85" className="fixed"></Background>
            </div>

            <div className="flex flex-col items-center relative z-20">

                <header className="h-[6rem] w-full bg-[#5474B8] flex items-center justify-center shadow-md shadow-slate-600">
                
                    <a href="/">

                        <div className="flex items-center">

                            <div>
                                <img src="../../public/icons/logo.svg" alt="logo" className="w-20 h-20 rounded-full" />
                            </div>

                            <div className="pl-2 pb-1">
                                <Label color="text-[#F5B80D]" fontSize="text-[2.5rem]" className="font-potta cursor-pointer">Shop Farm</Label>
                            </div>

                        </div>

                    </a>

                </header>

                <main className="p-[7rem] w-max-[50rem]">
                    <CardPayment 
                        initialization={{ amount: totalPrice}}
                        onSubmit={onSubmit}
                    />
                </main>

            </div>

        </div>

    )
}