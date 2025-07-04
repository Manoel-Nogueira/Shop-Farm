import { FaMinus, FaPlus, FaShoppingBag } from "react-icons/fa";
import { Background } from "../components/background";
import { Label } from "../components/label";
import { Navbar } from "../components/nav_bar";
import { Collapse } from "../components/collapse";
import Api from "../services/api";
import { Badge} from "@mui/material";
import { DateTime } from "luxon";
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import { Button } from "../components/button";
import { Controller, useForm } from "react-hook-form";

type userType = {

    id: number,
    sub: string,
    name: string,
    role: string,
    exp: number,
    iat: number,

}

type orderType = {

    orderResponseDTO: {
        id: number,
        totalPrice: number,
        orderStatus: string,
        trackingCode: string,
        create_at: string,
        update_at: string,
        user: {
            id: number,
            name: string,
            email: string,
            role: string,
            created_at: string,
            updated_at: string
        },
        payment: {
            id: number,
            paymentMethod: string,
            amount: number,
            paymentStatus: string,
            user: {
                id: number,
                name: string,
                email: string,
                role: string,
                created_at: string,
                updated_at: string
            },
            create_at: string,
            update_at: string
        }
    },
    orderItemImageResponseDTO: [{

        orderItemResponseDTO: {
					id: number,
					product: {
						id: number,
						name: string,
						description: string,
						price: number,
						rating: number,
						stock: number,
						create_at: string,
						update_at: string,
						category: {
							id: number,
							name: string
						},
						brand: {
							id: number,
							name: string
						}
					},
					quantity: number,
					subtotal: number
				},
        images: [{
            id: number, 
            url: string, 
        }]

    }]

}

type formData = {

    [key: `rating_${number}`]: number,
    [key: `comment_${number}`]: string,

}

export function Shopping () {

    const token = localStorage.getItem("token")
    const [user, setUser] = useState<userType>()
    const [order, setOrder] = useState<orderType[]>([])
    const {register, control, handleSubmit} = useForm<formData>()

    const submitReview = async (data: {rating: number, comment:string}, productId: number) => {

        const review = {

            "rating": data.rating,
            "comment": data.comment,
            "user_id": user?.id,
            "product_id": productId,

        }

        console.log(review)

        await Api.post("/reviews", review)
        .then(function(response) {
            
            console.log(response)

        })
        .catch(function(error) {

            console.error(error)

        })

    }

    useEffect(() => {

        if(token) {

            setUser(jwtDecode(token))
                
        }
        
        const getOrders = async () => {
    
            await Api.get(`/orders/list_all/${user?.id}`)
            .then(function (response) {
                
                console.log(response)
                setOrder(response.data)
    
            })
            .catch(function (error) {
    
                console.error(error)
    
            })
    
        }

        if(user) {

            getOrders()

        }

    }, [user?.id])

    return (

        <div>

            <div>
                <Background opacity="opacity-80" className="fixed"></Background>
            </div>

            <div className="relative">

                <header className="shadow-md shadow-slate-600 z-40">
                    <Navbar color="bg-[#5474B8]" className="px-20"/>
                </header>

                <main className="flex justify-center items-center z-10 p-[10rem] font-poppins">

                    <div className="w-[50rem] bg-[#F8FAFC] p-[1rem] rounded-xl divide-y-[0.156rem] divide-slate-400">

                        <div className="flex items-center">

                            <div className="pb-2">
                                <FaShoppingBag className="text-[#5474B8] text-[2rem]"/>
                            </div>

                            <div>
                                <Label color="text-[#F5B80D]" fontSize="text-[2rem]" className="font-semibold">minhas compras</Label>
                            </div>

                        </div>

                        <div >
                            {order.map(orde => (

                                <div key={orde.orderResponseDTO.id} className="bg-[#E9E9E9] mt-4 p-[1rem] rounded-xl shadow-md shadow-slate-500">

                                    <div className="flex text-left mb-2">
                                        <Label color="text-slate-700" fontSize="text-[1.5rem]" className="pl-0 font-pppins font-medium">{orde.orderResponseDTO.id}</Label>
                                    </div>

                                    <div className="flex items-center justify-between">

                                        <div className="flex items-center">

                                            <div className="flex text-left mr-2 pb-[0.3rem]">
                                                <Label color="text-slate-700" fontSize="text-[1.3rem]" className="pl-0 font-medium">Total: </Label>
                                            </div>

                                            <div className="text-left">
                                                <Label color="text-[#16824A]" fontSize="text-[1.6rem]" className="pl-0 font-medium font-lalezar">{orde.orderResponseDTO.totalPrice.toLocaleString("pt-BR", {style: "currency", currency: "BRL"})}</Label>
                                            </div>

                                        </div>

                                        <div className="text-left">
                                            <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium">{DateTime.fromISO(orde.orderResponseDTO.create_at, {zone: "utc"}).toFormat("dd/MM/yyyy HH:mm") }</Label>
                                        </div>

                                    </div>

                                    <div className="flex items-center justify-between mt-2">
                                    
                                        <div className="flex items-center">

                                            <div className="flex text-left mr-2">
                                                <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium">Pagamento: </Label>
                                            </div>

                                            <div className="text-left">
                                                <Label color="" fontSize="text-[1.2rem]" className="text-[#16824A] pl-0 font-medium lowercase">{orde.orderResponseDTO.payment.paymentStatus}</Label>
                                            </div>

                                        </div>

                                        <div className="flex items-center">

                                            <div className="flex text-left mr-2">
                                                <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium">Situação: </Label>
                                            </div>

                                            <div className="text-left">
                                                <Label color="" fontSize="text-[1.2rem]" className="text-[#16824A] pl-0 font-medium lowercase">{orde.orderResponseDTO.orderStatus}</Label>
                                            </div>

                                        </div>

                                    </div>

                                    <div className="flex items-center mt-4">

                                        <div className="flex text-left pb-1 mr-2">
                                            <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium">Código de rastreamento: </Label>
                                        </div>

                                        <div className="flex text-left pb-1 mr-2">
                                            <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium uppercase">{orde.orderResponseDTO.trackingCode}</Label>
                                        </div>

                                    </div>

                                    <div>
                                    
                                        <Collapse className="mt-6 divide-y-[0.156rem] divide-slate-300"
                                            
                                            header={
                                                
                                                <div className="flex text-left mb-1">
                                                    <Label color="text-slate-700" fontSize="text-[1.4rem]" className="pl-0 font-medium">Produtos</Label>
                                                </div>
                
                                            }
                
                                            iconColor="text-slate-700" 
                
                                            body={

                                                <div>

                                                    {orde.orderItemImageResponseDTO.map(item => {

                                                        const productId = item.orderItemResponseDTO.product.id
                                                        const rating = `rating_${productId}` as `rating_${number}`
                                                        const comment = `comment_${productId}` as `comment_${number}`
                                                        
                                                        return(

                                                            <Badge key={item.orderItemResponseDTO.id} badgeContent={item.orderItemResponseDTO.quantity} color="primary" className="w-full mt-4" sx={{ "& .MuiBadge-badge": {minWidth: "1.6rem", height: "1.5rem", borderRadius: "0.75rem", padding: "0.25rem", fontFamily: "revert" , fontSize: "1rem", fontWeight: "500"} }}>

                                                                <div className="w-full bg-[#F1F1F1] p-[1rem] rounded-xl shadow-md shadow-slate-600">
            
                                                                    <div className="flex">

                                                                        <div className="flex justify-between opacity-85">
                                                                            <img src={item?.images?.[0]?.url} alt={item.orderItemResponseDTO.product.name} className="h-[7rem] w-[7rem] rounded-lg" />
                                                                        </div>

                                                                        <div className="ml-4 w-[38rem]">

                                                                            <div className="flex items-center justify-between">

                                                                                <div className="flex text-left">
                                                                                    <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0  font-medium">{item.orderItemResponseDTO.product.name}</Label>
                                                                                </div>

                                                                                <div>
                                                                                    <Label color="text-[#16824A]" fontSize="text-[1.5rem]" className="pl-0 font-lalezar">{item.orderItemResponseDTO.subtotal.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                                                                                </div>      

                                                                            </div>

                                                                            <div className="flex text-left">
                                                                                <Label color="text-slate-700" fontSize="text-[0.9rem]" className="pl-0 font-medium">{item.orderItemResponseDTO.product.brand.name}</Label>
                                                                            </div>

                                                                            <div className="mt-2">
                                                                                <Label color="text-[#16824A]" fontSize="text-[1.2rem]" className=" pl-0 font-lalezar">{item.orderItemResponseDTO.product.price.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                                                                            </div>

                                                                        </div>

                                                                    </div>

                                                                    <div>

                                                                        <Collapse className="mt-2 divide-y-[0.156rem] divide-slate-300"

                                                                            header={
                                                
                                                                                <div className="flex text-left mb-1">
                                                                                    <Label color="text-slate-700" fontSize="text-[1.2rem]" className="pl-0 font-medium">Avaliar</Label>
                                                                                </div>
                                                
                                                                            }

                                                                            iconColor="text-slate-700"
                                                                            iconSize="text-3xl" 

                                                                            body={

                                                                                <form onSubmit={handleSubmit((formData) => submitReview({rating: formData[rating], comment: formData[comment]}, productId))} className="mt-4">

                                                                                    <div className="flex gap-2">

                                                                                        <div className="mt-[0.4rem]">
                                                                                            <Label color="text-slate-600" fontSize="text-base" className="font-poppins font-medium">opinião:</Label>
                                                                                        </div>

                                                                                        <div>
                                                                                            <textarea {...register(comment)} required={true} cols={75} rows={5} placeholder="Produto muito bom." className="resize-none p-1 rounded-md font-poppins border-[0.125rem] border-solid outline-none focus:ring-1 placeholder-shown:invalid:border-slate-500 invalid:border-red-700 focus:invalid:border-red-700 focus:invalid:ring-red-700 valid:border-sky-500 focus:border-sky-500 focus:ring-sky-500"></textarea>
                                                                                        </div>

                                                                                    </div>

                                                                                    <div className="flex items-center justify-between mt-4">

                                                                                        <div className="flex items-center gap-[0.6rem] pl-[1.30rem]">

                                                                                            <div>
                                                                                                <Label color="text-slate-600" fontSize="text-base" className="font-poppins font-medium">nota:</Label>
                                                                                            </div>

                                                                                            <Controller control={control} name={rating} defaultValue={0} render={({ field }) => (

                                                                                                <div className="flex items-center">

                                                                                                    <div>
                                                                                                        <Button type="button" onClick={() => field.onChange(Math.max(field.value - 1 , 0))} disabled={field.value <= 0} className="h-[2.25rem] w-[2.25rem] bg-[#C1C5CB] flex items-center justify-center hover:bg-[#D5D7D5] rounded-full disabled:opacity-50 disabled:cursor-not-allowed"><FaMinus className="text-[#475569] text-[1rem]"/></Button>
                                                                                                    </div>

                                                                                                    <div className="h-[2.25rem] w-[2.25rem] text-center">
                                                                                                        <Label {...register(rating)} color="text-[#475569]" fontSize="text-[1.6rem]" className="pl-0 pt-2 font-lalezar">{field.value}</Label>
                                                                                                    </div>

                                                                                                    <div>
                                                                                                        <Button type="button" onClick={() => field.onChange(Math.min(field.value + 1 , 5))} disabled={field.value >= 5} className="h-[2.25rem] w-[2.25rem] bg-[#C1C5CB] flex items-center justify-center hover:bg-[#D5D7D5] rounded-full disabled:opacity-50 disabled:cursor-not-allowed"><FaPlus className="text-[#475569] text-[1rem]"/></Button>
                                                                                                    </div>

                                                                                                </div>

                                                                                            )}/>


                                                                                        </div>

                                                                                        <div className="flex justify-end">
                                                                                            <Button type="submit" className="bg-[#F5B80D] hover:bg-[#D9A509] active:bg-[#F5B80D] text-slate-50 font-poppins uppercase">Publicar avaliação</Button>
                                                                                        </div>
                                                                                    </div>

                                                                                </form>

                                                                            }

                                                                        />
                                                                    </div>
            
                                                                </div>

                                                            </Badge>
        
                                                        )
        
                                                    })}

                                                </div>

                                            }

                                        />
                                            
                                    </div>

                                </div>

                            ))}

                        </div>

                    </div>

                </main>

            </div>

        </div>

    )

}