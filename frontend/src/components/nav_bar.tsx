import { useEffect, useState, type ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { Label } from "../components/label";
import { Button } from "../components/button";
import { FaSearch } from "react-icons/fa";
import { FaCartShopping, FaCircleUser } from "react-icons/fa6";
import { BsQuestionCircleFill } from "react-icons/bs";
import { Input } from "./input";
import { Badge, IconButton} from "@mui/material";
import { Cart } from "./cart";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode"
import Api from "../services/api";

interface NavbarProps extends ComponentProps<"div"> {
    
    color: string,
    addCartItem?: boolean,
    setItemCart?: (value: boolean) => void,
    
}

type userType = {

    id: number,
    sub: string,
    name: string,
    role: string,
    exp: number,
    iat: number

}

export function Navbar ( props: NavbarProps ) {

    const token = localStorage.getItem("token")

    const [search, setSearch] = useState("");
    const [cart, setCart] = useState<boolean>(false)
    const [user, setUser] = useState<userType>()
    const [itemsInCart, setItemsInCart] = useState<number>(0)

    const [logged, setLogged] = useState<boolean>()
    const navigate = useNavigate()

    const clickUser = () => {

        if(logged) {

            navigate("/shopping")

        } else {

            navigate("/login")

        }

    }

    console.log(search)

    const getNumberItemsCart = async () => {

        Api.get(`/cart_items/quantity/${user?.id}`)
        .then(function (response) {

            console.log(response)
            setItemsInCart(response.data)

        })
        .catch(function (error) {
            
            console.error(error)

        })

    }

    if(props.addCartItem) {

        if(user?.id) {
            
            getNumberItemsCart()
            props.setItemCart?.(false)

        }

    }

    useEffect (() => {

        if(!token) {

            setLogged(false)

        } else {

            setUser(jwtDecode(token))
            setLogged(true)

        }

        if(user?.id) {
            
            getNumberItemsCart()

        }

        
    }, [token, user?.id, props.addCartItem])

    console.log("T -->>", token)

    return (

        <div {...props} className={twMerge("flex h-[6rem] w-full px-28 relative z-10 items-center justify-between", props.className, props.color,)}>

            <div className="flex w-full items-center justify-between">

                <div>

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

                </div>

                <div className="flex items-center">

                    <div>
                        <Input type="search" placeholder="Digite aqui o que procura..." onInput={(e) => setSearch(e.currentTarget.value)} className="w-[40rem] h-12 px-4 font-poppins font-normal border-[0.1rem] border-slate-500 rounded-r-none border-r-0 focus:border-sky-500 focus:border-r-0 focus:ring-0"/>
                    </div>

                    <div>
                        <Button type="submit" className="bg-[#16824A] h-12 w-12 rounded-l-none"><FaSearch className="text-white text-2xl text-center hover:text-[#F5B80D] duration-200 ease-out"/></Button>
                    </div>

                </div>

                <div className="flex justify-center gap-x-10">

                    <div>

                        <IconButton onClick={() => setCart(true)} >

                            <Badge badgeContent={itemsInCart} color="warning" showZero={true} className="hover:scale-[1.15] duration-200 ease-out" sx={{ "& .MuiBadge-badge": {minWidth: "1.6rem", height: "1.5rem", borderRadius: "0.75rem", border: "0.125rem solid #5474B8", padding: "0.25rem", fontFamily: "revert" , fontSize: "1rem", fontWeight: "500"} }}>
                                <div>
                                    <FaCartShopping className="text-white text-[2.65rem] hover:scale-110 hover:text-[#F5B80D] duration-200 ease-out"/>
                                </div>
                            </Badge>
                        
                        </IconButton>

                    </div>

                    <div className="">

                        <IconButton onClick={clickUser}>

                            <div>
                                <FaCircleUser className="text-white text-[2.65rem] hover:scale-125 hover:text-[#F5B80D] duration-200 ease-out"/>
                            </div>

                        </IconButton>

                    </div>

                    <div>
                        <BsQuestionCircleFill className="text-white text-[2.65rem] hover:scale-125 hover:text-[#F5B80D] duration-200 ease-out"/>
                    </div>

                </div>

            </div>

            { cart && user?.id && token &&

                <div>
                    <Cart openCart={setCart} userId={user?.id} token={token} setItemCart={props.setItemCart!}/>
                </div>

            }

        </div>


    )

}
