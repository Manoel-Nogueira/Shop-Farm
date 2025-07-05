import { useEffect, useState, type ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { Label } from "../components/label";
import Api from "../services/api";

interface SidebarProps extends ComponentProps<"div"> {

    color: string,
    getSelection: Function

}

type categoriesType = {

    id: number,  
    name:  string

}

export function Sidebar ( props: SidebarProps) {

    const [categories, setCategories] = useState<categoriesType[]>([])
    
    useEffect(() => {

        const getCategories = async () => {

            const response = await Api.get("/categories/list_all")

            console.log(response)
            setCategories(response.data)

        }

        getCategories()

    }, [])
    
    return (

        <div {...props} className={twMerge("w-56 rounded-xl p-2 divide-slate-200 divide-y-2", props.className, props.color)}>
            
            <div className="p-2">
                <Label color="text-slate-800" fontSize="text-[1.1rem]" className="pl-0 font-poppins font-medium uppercase">Categorias</Label>
            </div>

            <div className="pt-2">

                {categories.map(category => (

                    <div key={category.id}>
                        <ul className="hover:bg-[#56AC35] rounded-xl">
                            <a href="" onClick={(e) => {e.preventDefault(); props.getSelection(category.name)}}>
                                <li className="p-2 text-slate-800 text-[1rem] font-poppins font-normal hover:text-[#FFFFFF]">{category.name}</li>
                            </a>
                        </ul>
                    </div>

                ))}

            </div>


        </div>

    )

}
