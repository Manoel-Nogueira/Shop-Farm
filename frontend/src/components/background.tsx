import type { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface BackgroundProps extends ComponentProps<"div"> {

    opacity: string,

}

export function Background (props: BackgroundProps) {

    return (

        <div className={twMerge("bg-[url(../../public/background.svg)] h-full w-full absolute z-0 inset-0 bg-no-repeat bg-cover bg-center", props.className, props.opacity)}>
        </div>

    )

}
