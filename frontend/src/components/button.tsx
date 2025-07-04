import type { ComponentProps, ReactNode } from "react";
import { twMerge } from "tailwind-merge";

interface ButtonProps extends ComponentProps<"button"> {

    children: string | ReactNode,

}

export function Button (props: ButtonProps) {

    return (

        <button {...props} className={twMerge("rounded-md p-2 font-medium flex items-center justify-center", props.className)}>{props.children}
        </button>

        
    )

}