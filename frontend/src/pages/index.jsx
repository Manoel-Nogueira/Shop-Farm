//export function Pages(){} chama do named export, pode mudar o nome na importação
//ou
//export default function Pages(){} não pode mudar o nome na importação

import { Component } from "react";
import Home from "./home";
import Compras from "./compras";

const Pages = [

    {
        path: "/",
        component: < Home />
    },
    {
        path: "/compras",
        component: < Compras />

    }

];

export default Pages;