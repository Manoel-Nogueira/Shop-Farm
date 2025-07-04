import { Label } from "../components/label";
import { Background } from "../components/background";
import { Button } from "../components/button";
import Api from "../services/api";
import { Input } from "../components/input";
import { useForm } from "react-hook-form";

type formData = {

    name: string,
    email: string,
    password: string

}

export function Register () {

    const { register , handleSubmit} = useForm<formData>()
    
    const submitRegister = async (data: formData) => {
    
        const register = {

            "name": data.name,
            "email": data.email,
            "password": data.password,
            "role": "BUYER"

        }

        console.log(register)

        await Api.post("/users/register", register)
        .then(function (response) {

            console.log(response)
            window.location.href = "/login"

        })
        .catch(function (error) {

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

                <main className="flex justify-center items-center m-[12rem]">

                    <form onSubmit={handleSubmit(submitRegister)} className="bg-slate-100 rounded-3xl bg-opacity-85">

                        <div className="py-5 px-12 grid grid-cols-1 gap-y-6">

                            <div className="pt-4">
                                <h1 className="text-3xl font-poppins font-bold text-center text-slate-600">Cadastre-se</h1>
                            </div>

                            <div className="gap-y-0">

                                <div>
                                    <Label color="text-slate-800" fontSize="text-base" className="font-poppins">nome</Label>
                                </div>

                                <div>
                                    <Input {...register("name")} required type="text" placeholder="Athams" className="font-poppins placeholder-shown:invalid:border-slate-500 invalid:border-red-700 focus:invalid:border-red-700 focus:invalid:ring-red-700 valid:border-sky-500 focus:border-sky-500 focus:ring-sky-500"/>
                                </div>
                            
                            </div>

                            <div className="gap-y-0">

                                <div>
                                    <Label color="text-slate-800" fontSize="text-base" className="font-poppins">e-mail</Label>
                                </div>

                                <div>
                                    <Input {...register("email")} required type="email" placeholder="athams@gmail.com" className="font-poppins placeholder-shown:empty:border-slate-500 invalid:text-red-700 invalid:border-red-700 focus:invalid:border-red-700 focus:invalid:ring-red-700 valid:border-sky-500 focus:border-sky-500 focus:ring-sky-500" />
                                </div>

                            </div>

                            <div className="gap-y-0">
                                
                                <div>
                                    <Label color="text-slate-800" fontSize="text-base" className="font-poppins">senha</Label>
                                </div>

                                <div>
                                    <Input {...register("password")} required type="password" placeholder="••••••••••••••••••" className="font-poppins placeholder-shown:invalid:border-slate-500 invalid:border-red-700 focus:invalid:border-red-700 focus:invalid:ring-red-700 valid:border-sky-500 focus:border-sky-500 focus:ring-sky-500"/>
                                </div>

                            </div>

                            <div className="flex justify-center pt-4">
                                <Button className="bg-[#16824A] hover:bg-[#157245] active:bg-[#16824A] text-slate-100 font-poppins uppercase">Criar Conta</Button>
                            </div>

                            <div className="flex justify-center">
                                <Label color="text-slate-800" fontSize="text-[0.8rem]" className="font-poppins font-normal text-center"><div>já tem uma  conta clique <a href="/login" className="text-sky-500 font-bold">aqui</a></div></Label>
                            </div>

                        </div>

                    </form>

                </main>

            </div>

        </div>

    )

}