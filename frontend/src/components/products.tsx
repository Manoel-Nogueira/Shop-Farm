import { useEffect, useState, type ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { Label } from "../components/label";
import { Button } from "../components/button";
import { FaCartPlus } from "react-icons/fa6";
import { TiStarFullOutline } from "react-icons/ti";
import Tooltip from '@mui/material/Tooltip';
import Api from "../services/api";
import { Link } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { Zoom } from "@mui/material";

interface ProductsProps extends ComponentProps<"div"> {

    filter: string,
    setItemCart: (value: boolean) => void,

}

type productsType = {

    productResponseDTO: {
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
    }
    images: [{
        id: number, 
        url: string, 
    }]

}

type userType = {

    id: number,
    sub: string,
    name: string,
    role: string,
    exp: number,
    iat: number

}

export function Products ( props: ProductsProps) {

    const token = localStorage.getItem("token")
    const [user, setUser] = useState<userType>()
    const [products, setProducts] = useState<productsType[]>([])
    const filteredProducts = props.filter ? products.filter(product => product.productResponseDTO.category.name == props.filter) : products

    async function addProductCart (product: productsType) {

        const productCart = {

            "quantity": 1,
	        "product_id": product.productResponseDTO.id,
	        "user_id": user?.id

        }

        if(user?.id) {

            await Api.post("/cart_items", productCart)
            .then(function (response) {
    
                console.log(response.data)
    
            })
            .catch(function (error) {
    
                console.error(error)
    
            })

            props.setItemCart(true)

        }

    } 

    useEffect(() => {

        if(token) {

            setUser(jwtDecode(token))
        
        }

        const getProducts = async () => {

            await Api.get("/products/list_all")
            .then(function(response) {

                console.log(response)
                setProducts(response.data)

            })
            .catch(function (error) {

                console.error(error)

            })

        }

        getProducts()

    }, [])

    if(products)
    return (

        <div className="relative z-10 grid grid-cols-4 gap-6 pb-24">

            {filteredProducts.map(product => (

                <div key={product.productResponseDTO.id} className="relative">

                    <Link to={`/product/${product.productResponseDTO.id}`} className="z-0">

                        <div {...props} className={twMerge("h-[28rem] w-[18rem] bg-[#FFFFFF] flex justify-center rounded-xl p-2 shadow-md hover:shadow-lg hover:shadow-slate-600 shadow-slate-600 hover:scale-[1.01]", props.className)}>
                    
                            <div className="w-full">
                                
                                <div className="flex justify-center opacity-85 mt-4">
                                    <img src={product?.images?.[0]?.url} alt={product.productResponseDTO.name} className="h-[15rem] w-[15rem] rounded-[1rem]"/>
                                </div>
                                
                                <div className="flex w-full text-left px-2 my-6">

                                    <Tooltip title={product.productResponseDTO.name} arrow slots={{transition: Zoom}} slotProps={{popper: {sx: {"& .MuiTooltip-tooltip": {fontSize: "0.9rem"}}}}}>
                                        <Label color="" fontSize="text-[1.15rem]" className="pl-0 font-poppins font-medium truncate">{product.productResponseDTO.name}</Label>
                                    </Tooltip>

                                </div>

                                <div className="text-left px-2 mb-4 ">
                                    <Label color="" fontSize="text-3xl" className="text-[#16824A] pl-0 font-lalezar">{product.productResponseDTO.price.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</Label>
                                </div>

                                <div className="px-2  flex items-center justify-between ">

                                    <div className="flex items-center">

                                        <div>
                                            <TiStarFullOutline className="text-4xl text-[#F5B80D] "/>
                                        </div>

                                        <div>
                                            <Label color="text-[#F5B80D] " fontSize="text-xl" className="font-semibold">{`${Math.round(product.productResponseDTO.rating)}/5`}</Label>
                                        </div>

                                    </div>


                                </div>

                            </div>

                        </div>
                        
                    </Link>
                
                    <div className="absolute right-4 bottom-4 z-10">
                        <Button onClick={() => addProductCart(product)} type="submit" className="bg-[#16824A] hover:bg-[#157245] active:bg-[#16824A] h-10 w-14 rounded-xl text-white "><FaCartPlus className="text-3xl flex justify-center items-center"/></Button>
                    </div>

                </div>

            ))}

        </div>

    )

}