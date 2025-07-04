import { Label } from "../components/label";
import { Button } from "../components/button";

export function Page404 () {

    return (

        <main className="grid min-h-full place-items-center bg-white px-6 py-24 sm:py-32 lg:px-8">

            <div className="flex flex-col text-center justify-center items-center">

                <div>
                    <Label color="text-[#16824A]" fontSize="text-[3rem]" className="font-potta font-semibold">Erro 404</Label>
                </div>

                <div className="">
                    <Label color="text-[#16824A]" fontSize="text-[3rem]" className="font-potta tracking-tight text-balance font-semibold">Página não encontrada</Label>
                </div>
                
                <div className="w-[25rem] h-[25rem] object-cover">
                    <img src="../../public/404.svg" alt="404"/>
                </div>

                <div className="">
                    <Label color="text-gray-500" fontSize="text-[1.125rem]" className="font-potta tracking-tight text-pretty font-semibold">Desculpe, não conseguimos encontrar a página que você está procurando.</Label>
                </div>

                <div className="mt-10">

                    <a href="/">
                        <Button type="button" className="bg-[#16824A] hover:bg-[#157245] h-12 w-25 rounded-xl text-white ">Página Inicial</Button>
                    </a>

                </div>
                
            </div>
            
        </main>

    )

}