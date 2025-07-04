import { Route, Routes } from "react-router-dom"
import { Pages }  from "./src/index"

export function Router () {

    return (

        <Routes>
            <Route path="/register" element={<Pages.Register/>}/>
        </Routes>

    )

}