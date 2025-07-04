import type { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface InputProps extends ComponentProps<"input"> {

    children?: string,

}

export function Input ( props: InputProps ) {

    return (

        <div>
            <input {...props} className={twMerge("p-1 px-2 w-60 rounded-md border-[0.125rem] border-solid outline-none focus:ring-1", props.className)}/>
        </div>

    )

}