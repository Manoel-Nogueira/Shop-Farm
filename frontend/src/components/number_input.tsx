import { useState, type ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { FaMinus } from "react-icons/fa";
import { FaPlus } from "react-icons/fa";
import { Button } from "./button";
import { Label } from "./label";


interface numberInputProps extends ComponentProps<"div"> {

    itemUpdated?: (value: boolean) => void,
    onValueChange: (value: number) => void
    defaultValue: number,

}

export function NumberInput (props: numberInputProps) {

    const [value, setValue] = useState<number>(props.defaultValue)

    const increment = () => {

        if(value < 99) {

            setValue(value => value + 1)
            props.itemUpdated!(true)
            props.onValueChange(value + 1)
            
        }

    }

    const decrement = () => {

        if(value > 1) {

            setValue(value => value - 1)
            props.itemUpdated!(true)
            props.onValueChange(value - 1)

        }

    }
    
    return (

        <div {...props} className={twMerge("flex items-center gap-2", props.className)}>

            <div>
                <Button type="button" onClick={decrement} disabled={value <= 1} className="h-[2.25rem] w-[2.25rem] bg-[#E5E7EB] flex items-center justify-center hover:bg-[#D1D5DB] rounded-md disabled:opacity-50 disabled:cursor-not-allowed"><FaMinus className="text-[#475569] text-[1rem]"/></Button>
            </div>

            <div className="h-[2.25rem] w-[2.25rem] text-center">
                <Label color="text-[#475569]" fontSize="text-[1.6rem]" className="pl-0 pt-2 font-lalezar">{props.defaultValue}</Label>
            </div>

            <div>
                <Button type="button" onClick={increment} disabled={value >= 99} className="h-[2.25rem] w-[2.25rem] bg-[#E5E7EB] flex items-center justify-center hover:bg-[#D1D5DB] rounded-d disabled:opacity-50 disabled:cursor-not-allowed"><FaPlus className="text-[#475569] text-[1rem]"/></Button>
            </div>

        </div>

    )

}