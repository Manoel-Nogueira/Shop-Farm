import type { ComponentProps, ReactNode } from "react";
import { twMerge } from "tailwind-merge";

interface LabelProps extends ComponentProps<"label"> {

    children: ReactNode,
    color: string,
    fontSize: string
    
}

export function Label (props: LabelProps) {

    return (

        <label {...props} className={twMerge("pl-1", props.className, props.color, props.fontSize)}>{props.children}</label>

    )

}