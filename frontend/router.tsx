import { Route, Routes } from "react-router-dom"
import { Pages }  from "./src/index"

export function Router () {

    return (

        <Routes>
            <Route path="/" element={<Pages.Home/>}/>
            <Route path="/login" element={<Pages.Login/>}/>
            <Route path="/register" element={<Pages.Register/>}/>
            <Route path="/product/:id" element={<Pages.Product/>}/>
            <Route path="/*" element={<Pages.Page404/>}/>
        </Routes>

    )

}