import { useEffect, useState, type ComponentProps } from "react";
import { Label } from "./label";
import { twMerge } from "tailwind-merge";
import { FaCartShopping } from "react-icons/fa6";
import { IoIosCloseCircle } from "react-icons/io";
import { IconButton } from "@mui/material";
import { motion } from "motion/react"
import { CartProducts } from "./cart_products";
import { Button } from "./button";
import Api from "../services/api";
import { useNavigate } from "react-router-dom";

type cartItemsType = {

    cartItemResponseDTO: {
        id: number,
        quantity: number,
        subtotal: number,
        product: {
            id: number,
            name: string,
            description: string,
            price: number,
            rating: number,
            stock: number,
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
            category: {
                id: number,
                name: string
            },
            brand: {
                id: number,
                name: string
            }
        },
        cart: {
            id: number,
            user: {
                id: number,
                name: string,
                email: string,
                role: string,
                created_at: string,
                updated_at: string
            }
        }
    },
    images: [{
        id: number, 
        url: string, 
    }]

}

type cartItemUpdatedType = {

    id: number,
    quantity: number,

}

interface CartProps extends ComponentProps<"div"> {

    openCart: (value: boolean) => void,
    userId: number,
    token: string,
    setItemCart: (value: boolean) => void,

}

export function Cart (props: CartProps) {

    const [cartItems, setCartItems] = useState<cartItemsType[]>([])
    const cartId = cartItems[0]?.cartItemResponseDTO.cart.id
    const [totalPrice, setTotalPrice] = useState<number>(0)
    const [overCart, setOverCart] = useState<boolean>(false)
    const [deleteItemCart, setDeleteItemCart] = useState<boolean>(false)
    const [cartUpdateItem, setCartUpdateItem] = useState<cartItemUpdatedType>()
    const [itemUpdated, setItemUpdated] = useState<boolean>(false)
    
    const navigate = useNavigate()

    const ClickAway = () => {

        if(!overCart) {

            props.openCart(false)

        }

    }
    
    useEffect(() => {

        const updateCartItem = async () => {

            Api.put("/cart_items/update", cartUpdateItem)
            .then(function (response) {

                console.log(response)

            })
            .catch(function (error) {

                console.error(error)

            })

            setItemUpdated(false)

        }
        
        const getCartItems = async () => {
    
            Api.get(`/cart_items/list_all/${props.userId}`)
            .then(function (response) {
    
                console.log(response)
                setCartItems(response.data)
                setDeleteItemCart(false)
    
            })
            .catch(function (error) {
    
                console.error(error)
    
            })
    
        }
    
        const getTotalPrice = async () => {
    
            Api.get(`/cart_items/total_price/${cartId}`)
            .then(function (response) {
    
                console.log(response)
                setTotalPrice(response.data)
    
            })
            .catch(function (error) {
    
                console.error(error)
    
            })
    
        }
        
        if(props.userId) {

            getCartItems()

        }

        if(cartId) {

            getTotalPrice()

        }

        if(itemUpdated) {

            updateCartItem()

        }

    }, [props.userId, props.token, cartId, deleteItemCart, itemUpdated, cartUpdateItem])

    return (

        <div className="flex justify-end h-full w-full fixed z-40 top-0 bottom-0 left-0 right-0 bg-[#000000]/50" onClick={ClickAway}>
 
            <motion.div className="z-50 w-[40rem] h-screen bg-[#F8FAFC]" onMouseOver={() => setOverCart(true)} onMouseOut={() => setOverCart(false)} style={{ originX: 1 }} initial={{ scaleX: 0 }} animate={{ scaleX: 1, transition: { duration: 0.2, type:"tween" , ease: "easeIn"} }}>

                <div className="flex items-center justify-between p-2 bg-[#5474B8] ">

                    <div className="flex items-center justify-center m-2 gap-1">

                        <div>
                            <FaCartShopping className="text-[#FFFFFF] text-[2rem]"/>
                        </div>

                        <div className="">
                            <Label color="text-[#F5B80D]" fontSize="text-[2rem]" className={twMerge("font-semibold font-poppins", props.className)}>carrinho</Label>
                        </div>

                    </div>

                    <div>

                        <IconButton onClick={() => props.openCart(false)}>

                            <div>
                                <IoIosCloseCircle className="text-[#FFFFFF] text-[2rem] hover:text-[#A0A0A0]"/>
                            </div>

                        </IconButton>

                    </div>

                </div>

                <div className="flex flex-col h-full">

                    <div className="h-[45rem] bg-[#789456] overflow-y-auto">
                        <CartProducts cartItems={cartItems} setDeleteItemCart={setDeleteItemCart} setItemCart={props.setItemCart} setItemUpdated={setItemUpdated} itemUpdated={setCartUpdateItem} className="mx-5 my-5"></CartProducts>
                    </div>

                    <div className="grow p-8 bg-[#E5E5E5] shadow-top">

                        <div className="flex items-center justify-between">

                            <div>
                                <Label color="text-slate-800" fontSize="text-[1.6rem]" className="pl-0 font-poppins font-semibold">total</Label>
                            </div>

                            <div>
                                <Label color="text-[#16824A]" fontSize="text-[1.6rem]" className="pl-0 pt-2 font-lalezar">{totalPrice.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                            </div>

                        </div>

                        <div className="flex justify-center mt-4"> 
                            <Button onClick={() => navigate("/payment", {state: {props: {cartItems: cartItems, userId: props.userId, totalPrice: totalPrice, token: props.token }}})} type="button" disabled={(cartItems.length <= 0)} className="grow bg-[#16824A] hover:bg-[#157245] active:bg-[#16824A] text-slate-100 items-center font-poppins uppercase disabled:bg-[#16824A] disabled:opacity-50 disabled:cursor-not-allowed">Comprar</Button>
                        </div>

                    </div>
                    
                </div>

            </motion.div>

        </div>

    )

}