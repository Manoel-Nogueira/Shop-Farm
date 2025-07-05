import { useEffect, useState } from "react";
import { Background } from "../components/background";
import { Label } from "../components/label";
import { Navbar } from "../components/nav_bar";
import { Swiper, SwiperSlide } from "swiper/react";
import { Button } from "../components/button";
import { FaCartPlus } from "react-icons/fa";
import { TiStarFullOutline } from "react-icons/ti";
import { Collapse } from "../components/collapse"
import { useParams } from "react-router-dom";
import Api from "../services/api";
import { Input } from "../components/input";
import { jwtDecode } from "jwt-decode";

type productType = {

    productResponseDTO: {
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
    }
    images: [{
        id: number, 
        url: string, 
    }]

}

type reviewsType = {

    id: number,
    rating: number,
    comment: string,
    product_id: {
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
            name: string,
        },
        brand_id: {
            id: number,
            name: string,
        }
    },
    user_id: {
        id: number,
        name: string,
        email: string,
        role: string,
        created_at: string,
        updated_at: string
    }

}

type userType = {

    id: number,
    sub: string,
    name: string,
    role: string,
    exp: number,
    iat: number

}

export function Product () {

    const token = localStorage.getItem("token")
    const [user, setUser] = useState<userType>()
    
    const {id} = useParams()
    
    const [product, setProduct] = useState<productType>()
    const [reviews, setReviews] = useState<reviewsType[]>([])
    const [quantity, setQuantity] = useState<number>(1);

    async function addCartItem (item: productType, productQuantity: number) {

        console.log("Aquiiiii")

        const product = {

            "quantity": productQuantity,
	        "product_id": item.productResponseDTO.id,
	        "user_id": user?.id

        }

        await Api.post("/cart_items", product)
        .then(function (response) {

            console.log(response.data)

        })
        .catch(function (error) {

            console.error(error)

        })

    } 
    
    useEffect(() => {

        if(token) {
        
            setUser(jwtDecode(token))
                
        }

        const getProducts = async () => {

            await Api.get(`/products/show/${id}`)
            .then(function (response) {

                console.log(response)
                setProduct(response.data)

            })
            .catch(function (error) {

                console.error(error)

            })

        }

        const getReviews = async () => {

            await Api.get(`/reviews/list_all/${id}`)
            .then(function (response) {

                console.log(response)
                setReviews(response.data)

            })
            .catch(function (error) {

                console.error(error)

            })

        }

        getProducts()
        getReviews()

    }, [id])

    if(product && reviews)
    return (

        <div>
            
            <div>
                <Background opacity="opacity-80" className="fixed"></Background>
            </div>

            <div className="relative z-10">

                <header className="shadow-md shadow-slate-600">
                    <Navbar color="bg-[#5474B8]" className="px-20"/>
                </header>

                <main className="m-20 p-10 bg-[#F8FAFC] rounded-xl">

                    <div className="flex">

                        <div className="h-[30rem] w-[30rem]">

                            <Swiper slidesPerView={1} navigation={true} pagination={true} className="rounded-lg border-[0.188rem] border-slate-300">
                                
                                {product.images.map(image => (

                                    <div key={image.id}>

                                        <SwiperSlide>
                                            <img src={image.url} alt={product.productResponseDTO.name} className="object-fill h-[30rem] w-[30rem]"/>
                                        </SwiperSlide>

                                    </div>
                                    
                                ))}

                            </Swiper>

                        </div>
                        
                        <div className="pl-10">

                            <div className="flex text-left mb-2">
                                <Label color="text-slate-950" fontSize="text-[1.7rem]" className="pl-0 font-poppins font-medium">{product.productResponseDTO.name}</Label>
                            </div>

                            <div className="flex text-left mb-4">
                                <Label color="text-slate-500" fontSize="text-[1.2rem]" className="pl-0 font-poppins font-medium">{`Marca: ${product.productResponseDTO.brand.name}`}</Label>
                            </div>

                            <div className="flex items-center mb-4">
                            
                                <div>
                                    <TiStarFullOutline className="text-[2.6rem] text-[#F5B80D] "/>
                                </div>
    
                                <div className="pt-1">
                                    <Label color="text-[#F5B80D] " fontSize="text-[1.5rem]" className="font-medium font-poppins">{`${Math.round(product.productResponseDTO.rating)}/5`}</Label>
                                </div>
                            
                            </div>

                            <div className="text-left">
                                <Label color="" fontSize="text-[2.5rem]" className="text-[#16824A] pl-0 font-lalezar font-medium">{product.productResponseDTO.price.toLocaleString("pt-BR", {style: "currency", currency: "BRL"})}</Label>
                            </div>

                            <div className="text-left mt-4">
                                <Label color="text-slate-950" fontSize="text-[1.2rem]" className="pl-0 font-poppins">{`Estoque: ${product.productResponseDTO.stock}`}</Label>
                            </div>
                            
                            <div className="text-left mb-8">

                                <Input  
                                    type="number" required defaultValue={1} min={1} max={100} step={1} 
                                    onInput={(e) => {

                                        const value = Number(e.currentTarget.value)

                                        if(value < 1) {

                                            e.currentTarget.value = "1"
                                            setQuantity(1)

                                        } else if(value > 99) {

                                            e.currentTarget.value = "99"
                                            setQuantity(99)

                                        } else { 

                                            setQuantity(value)
                                        }

                                    }}
                                    className="w-[4rem] h-[3rem] font-poppins font-normal border-[0.1rem] border-slate-500 focus:border-sky-500 focus:ring-0"
                                />
                                
                            </div>

                            <div>

                                <Button type="button" onClick={() => addCartItem(product, quantity)} className="bg-[#16824A] hover:bg-[#157245] rounded-md flex justify-start">
                                    
                                    <div className="flex">
                                    
                                        <div>
                                            <FaCartPlus className="text-3xl flex justify-center items-center text-[#FFFFFF]"/>
                                        </div>

                                        <div>
                                            <Label color="text-[#FFFFFF]" fontSize="text-[1.2rem]" className="font-poppins">Adicionar ao Carrinho</Label>
                                        </div>

                                    </div>

                                </Button>

                            </div>

                        </div>

                    </div>

                    <div>

                        <Collapse className="mt-6 divide-y-[0.156rem] divide-slate-300"
                            
                            header={
                                
                                <div className="flex text-left mb-1">
                                    <Label color="text-slate-700" fontSize="text-[1.4rem]" className="pl-0 font-poppins font-medium">Descrição do produto</Label>
                                </div>

                            }

                            iconColor="text-slate-700" 

                            body={

                                <div className="flex text-left">
                                    <pre className="mt-2 pl-4 text-[1.1rem] text-slate-700 font-poppins font-medium text-wrap">{product.productResponseDTO.description}</pre>
                                </div>
                                
                            }
                        />
                            
                    </div>

                    <div>

                        <Collapse className="mt-6 divide-y-[0.156rem] divide-slate-300"
                            
                            header={
                                
                                <div className="flex text-left">
                                    <Label color="text-slate-700" fontSize="text-[1.4rem]" className="pl-0 font-poppins font-medium">Avaliações</Label>
                                </div>

                            }

                            iconColor="text-slate-700" 

                            body={

                                <div className="pl-4">
                                
                                    {reviews.map(review => (

                                        <div key={review.id} className="bg-[#FFFFFF] my-4 p-4 text-left border-2 border-slate-400 rounded-lg">

                                            <div className="flex items-center">
                                        
                                                <div>
                                                    <TiStarFullOutline className="text-2xl text-[#F5B80D] "/>
                                                </div>
                    
                                                <div className="pt-1">
                                                    <Label color="text-[#F5B80D] " fontSize="text-[1.1rem]" className="font-medium font-poppins">{`${Math.round(product.productResponseDTO.rating)}/5`}</Label>
                                                </div>
                                        
                                            </div>
                                            
                                            <div className="mt-1">
                                                <Label color="text-slate-700" fontSize="text-[1rem]" className="pl-0 font-poppins font-medium items-baseline">{review.user_id.name}</Label>
                                            </div>

                                            <div className="flex mt-2">
                                                <pre className="text-[0.9rem] text-slate-600 font-poppins font-medium text-wrap">{review.comment}</pre>
                                            </div>

                                        </div>

                                    ))}

                                </div>

                            }
                        />
                            
                    </div>

                    <div>

                        {/* <Collapse className="mt-6 divide-y-[0.156rem] divide-slate-300"

                            header={
                            
                                <div className="flex text-left">
                                    <Label color="text-slate-700" fontSize="text-[1.4rem]" className="pl-0 font-poppins font-medium">Informações do Vendedor</Label>
                                </div>

                            }

                            iconColor="text-slate-700" 

                            body={

                                <div className="pl-4">

                                    <div className="flex text-left">
                                        <Label color="text-slate-700" fontSize="text-[1.1rem]" className="pl-0 mt-2 font-poppins font-medium">{`Nome do vendedor: ${product.productResponseDTO.user.name}`}</Label>
                                    </div>

                                    <div className="flex text-left">
                                        <Label color="text-slate-700" fontSize="text-[1.1rem]" className="pl-0 font-poppins font-medium">{`E-mail: ${product.productResponseDTO.user.email}`}</Label>
                                    </div>
                                
                                </div>

                            }
                        /> */}

                    </div>
                
                </main>
                
            </div>

        </div>

    )

}