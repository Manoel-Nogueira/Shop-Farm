import { Button } from "../components/button";
import { Label } from "../components/label";
import { Background } from "../components/background";
import Api from "../services/api";
import { useForm } from "react-hook-form"
import { Input } from "../components/input";
import { useNavigate } from "react-router-dom";

type formData = {

    email: string,
    password: string

}

export function Login () {

    const { register , handleSubmit} = useForm<formData>()
    const navigate = useNavigate()

    const submitLogin = async (data: formData) => {

        const login = {

            "email": data.email,
            "password": data.password,

        }

        console.log(login)

        await Api.post("/users/auth", login)
        .then(function (response) {

            console.log("data -->> ", response.data)
            localStorage.setItem("token", response.data.token)
            navigate("/")

        })
        .catch(function(error) {

            console.error(error)

        })

    }

    return (

        <div className="h-screen w-full flex justify-center">

            <div>
                <Background opacity="opacity-85"></Background>
            </div>

            <div className="relative z-10">

                <header className="h-[6rem] w-screen bg-[#5474B8] flex items-center justify-center z-20 shadow-md shadow-slate-600">

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

                <main className="flex justify-center items-center m-[13rem]">

                    <form onSubmit={handleSubmit(submitLogin)} className="bg-slate-100 rounded-3xl bg-opacity-80">

                        <div className="py-5 px-12 grid grid-cols-1 gap-y-6">

                            <div className="pt-4">
                                <h1 className="text-3xl font-poppins font-bold text-center text-slate-600">Entrar</h1>
                            </div>

                            <div className="gap-y-0">

                                <div>
                                    <Label color="text-slate-800" fontSize="text-[1rem]" className="font-poppins font-normal">e-mail</Label>
                                </div>

                                <div>
                                    <Input {...register("email")} type="email" placeholder="athams@gmail.com" className="font-poppins border-slate-500 invalid:text-red-700 invalid:border-red-700 focus:invalid:border-red-700 focus:invalid:ring-red-700  focus:border-sky-500 focus:ring-sky-500"/>
                                </div>

                            </div>

                            <div className="gap-y-0">

                                <div>
                                    <Label color="text-slate-800" fontSize="text-[1rem]" className="font-poppins font-normal">senha</Label>
                                </div>

                                <div>
                                    <Input {...register("password")} required type="password" placeholder="••••••••••••••••••" className="border-slate-500 focus:border-sky-500 focus:ring-sky-500 font-poppins"/>
                                </div>

                            </div>

                            <div className="flex justify-center pt-4">
                                <Button type="submit" className="bg-[#16824A] hover:bg-[#157245] active:bg-[#16824A] text-slate-100 items-center font-poppins uppercase">Entrar</Button>
                            </div>

                            <div className="flex justify-center">
                                <Label color="text-slate-800" fontSize="text-[0.8rem]" className="font-poppins font-normal text-center"><div>não tem uma  conta clique <a href="/register" className="text-sky-500 font-bold">aqui</a></div></Label>
                            </div>

                        </div>

                    </form>

                </main>

            </div>

            
        </div>

    )

}