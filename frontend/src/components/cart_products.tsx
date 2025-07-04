import { type ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { Label } from "./label";
import { IconButton, Tooltip } from "@mui/material";
import { IoMdTrash } from "react-icons/io";
import Api from "../services/api";
import { NumberInput } from "./number_input";
import { Link } from "react-router-dom";

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

interface PropsCartProduct extends ComponentProps<"div"> {

    cartItems: cartItemsType[],
    itemUpdated: (value: cartItemUpdatedType) => void,
    setDeleteItemCart: (value: boolean) => void,
    setItemCart: (value: boolean) => void,
    setItemUpdated: (value: boolean) => void,

}

export function CartProducts (props: PropsCartProduct) {

    async function deletCartProduct (cartProduct: cartItemsType) {

        await Api.delete(`/cart_items/delete/${cartProduct.cartItemResponseDTO.id}`)
        .then(function(response) {

            console.log(response.data)
            props.setDeleteItemCart(true)
            props.setItemCart(true)

        })
        .catch(function(error) {

            console.error(error)

        })

    }

    const itemUpdate = (id: number, quantity: number) => {

        const item = {

            "id": id,
            "quantity": quantity

        }

        props.itemUpdated(item)

    }

    if(props.cartItems)
    return (

        props.cartItems.map(cartItem => (

            <div key={cartItem.cartItemResponseDTO.id} className="relative">

                <Link to={`/product/${cartItem.cartItemResponseDTO.product.id}`} className="z-0">

                    <div {...props} className={twMerge("bg-[#FFFFFF] rounded-xl", props.className)}>

                        <div className="flex p-4">

                            <div className="flex justify-center opacity-85">
                                <img src={cartItem?.images?.[0]?.url} alt={cartItem.cartItemResponseDTO.product.name} className="h-[7rem] w-[7rem] rounded-lg" />
                            </div>

                            <div className="ml-4 mt-1 grow">

                                <div>

                                    <Tooltip title={cartItem.cartItemResponseDTO.product.name}>
                                        <div className="truncate max-w-[25rem] ">
                                            <Label color="text-slate-700" fontSize="text-[1rem]" className="pl-0 font-poppins font-medium">{cartItem.cartItemResponseDTO.product.name}</Label>
                                        </div>
                                    </Tooltip>

                                </div>

                                <div className="mt-2">
                                    <Label color="text-[#16824A]" fontSize="text-[1.2rem]" className=" pl-0 font-lalezar">{cartItem.cartItemResponseDTO.product.price.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                                </div>

                                <div className="mt-1 flex justify-end">
                                    <Label color="text-[#16824A]" fontSize="text-[1.5rem]" className="pl-0 font-lalezar">{cartItem.cartItemResponseDTO.subtotal.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                                </div>      

                            </div>

                        </div>
                        
                    </div>

                </Link>

                <div className="absolute z-10 left-[10.2rem] bottom-6">
                    <NumberInput itemUpdated={props.setItemUpdated}  defaultValue={cartItem.cartItemResponseDTO.quantity} onValueChange={(quantity: number) => itemUpdate(cartItem.cartItemResponseDTO.id, quantity)}></NumberInput>
                </div>

                <div className="absolute z-10 right-12 top-4">

                    <IconButton sx={{ padding: 0 }} onClick={() => deletCartProduct(cartItem)}>
                        <div>
                            <IoMdTrash className="mb-0 p-0 text-[1.9rem] text-[#DC2626] hover:text-[#8F1818] active:text-[#DC2626]"/>
                        </div>
                    </IconButton>

                </div>

            </div>


        

        ))

    )

}