import { useState } from "react";
import { Background } from "../components/background";
import { Navbar } from "../components/nav_bar";
import { Products } from "../components/products";
import { Sidebar } from "../components/side_bar";

/* Colors:
 *
 *  #16824A
 *  #157245
 *  #789456
 *  #FFFFFF
 *  #F8FAFC
 *  #E5E7EB
 *  #D1D5DB
 *  #B1B1B1
 *  #A0A0A0
 *  #475569
 *  #475569
 *  #F5B80D
 *  #D9A509
 *  #5474B8
 *  #0ea5e9
 *  #7dd3fc
 *  #DC2626
 *  #8F1818
 * 
 * 
 * 
 *  #487E9D
 *  #5696BB
 *  #5455B8
 *  #554499
 * 
 * 
*/

export function Home () {

    const [category, setCategory] = useState<string>("")
    const [itemCart, setItemCart] = useState<boolean>(false)

    return (

        <div className="h-screen w-full flex">

            <div>
                <Background opacity="opacity-80" className="fixed"></Background>
            </div>

            <div className="flex">

                <header className="z-20 shadow-md shadow-slate-600 fixed w-full">
                    <Navbar color="bg-[#5474B8]" setItemCart={setItemCart} addCartItem={itemCart}/>
                </header>

                <main className="flex mx-28 my-20 mt-[12rem]">

                    <div className="relative z-10 mr-6">
                        <Sidebar color="bg-[#F8FAFC]" getSelection={setCategory}></Sidebar>
                    </div>

                    <div>
                        <Products filter={category} setItemCart={setItemCart}></Products>
                    </div>

                </main>

            </div>

        </div>

    )

}