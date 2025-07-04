import { useState, type ComponentProps, type ReactNode } from "react";
import { twMerge } from "tailwind-merge";
import { MdOutlineKeyboardArrowUp } from "react-icons/md";

interface CollapseProps extends ComponentProps<"button"> {

    header: ReactNode,
    body: ReactNode,
    iconColor: string,
    iconSize?: string,

}

export function Collapse (props: CollapseProps) {

    const [collapsed, setCollapsed] = useState<boolean>(false)

    return (

        <div className={twMerge("", props.className)}>

            <div className="flex items-center cursor-pointer" onClick={() => setCollapsed(!collapsed)}>

                <div>
                    {props.header}
                </div>

                <div className="pb-1">
                    <MdOutlineKeyboardArrowUp className={twMerge("text-4xl", `${collapsed ? "-rotate-180" : ""}`, props.iconSize , props.iconColor)}/>
                </div>

            </div>

            <div className={`"flex bg-red-500"  ${collapsed ? "" : "h-0" }`}>
                {collapsed && props.body}
            </div>

        </div>

    )

}